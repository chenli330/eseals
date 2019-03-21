package cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.ResourceMapper;
import cn.dao.RoleMapper;
import cn.dao.UserMapper;
import cn.dao.User_roleMapper;
import cn.pojo.Resource;
import cn.pojo.Role;
import cn.pojo.User;
import cn.pojo.User_role;
import cn.service.UserService;
import cn.service.User_roleService;
import cn.util.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private User_roleMapper user_roleMapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUsername(username);
	}

	/**
	 * 根据用户名查询所有的resource
	 */
	@Override
	public List<Resource> findResourceByUsername(String username) {
		// TODO Auto-generated method stub
		List<Role> roleList = roleMapper.findRoleByUsername(username);
		List<Resource> resourceList = new ArrayList<Resource>();
		for (Role role : roleList) {
			List<Resource> list = resourceMapper.findResourcesByRoleId(role.getrId());
			if (list.size() > 0) {
				resourceList.addAll(list);
			}
		}
		return resourceList;
	}

	/**
	 * 查询所有的user
	 */
	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userMapper.findAllUser();
	}

	/**
	 * 根据用户名查找权限字符串
	 */
	@Override
	public Set<String> findPermissionsByUsername(String username) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		List<Role> roleList = roleMapper.findRoleByUsername(username);
		for (Role role : roleList) {
			List<Resource> resourceList = resourceMapper.findResourcesByRoleId(role.getrId());
			for (Resource resource : resourceList) {
				set.add(resource.getPermission());
			}
		}
		return set;
	}

	@Override
	public Integer updateUser(User user) {
		if (user.getUsername() == null) {// 这是更新密码时候调用
			// 根据id查出这个用户全部信息
			User user2 = userMapper.findById(user.getId());
			// 用户名复制，用于生成盐值
			user.setUsername(user2.getUsername());
			// 对用户密码加密
			user = Handler.passwordHandler(user);
			return userMapper.updateUser(user);
		}
		User_role user_role = new User_role();
		user_role.setUserId(user.getId());
		user_role.setRoleId(new Long(user.getRoleIds()));
		user_roleMapper.updateByUserId(user_role);
		return userMapper.updateUser(user);
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userMapper.findById(id);
	}

	@Override
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	@Override
	public Integer deleteUserById(Long id) {
		// 删除user
		Integer integer = userMapper.deleteUserById(id);
		// 删除user在user_role表中的所有数据
		user_roleMapper.deleteByPrimaryKey(id);
		return integer;
	}
}
