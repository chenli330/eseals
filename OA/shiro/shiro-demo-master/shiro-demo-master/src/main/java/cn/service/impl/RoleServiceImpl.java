package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.RoleMapper;
import cn.pojo.Role;
import cn.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return roleMapper.findRoleByUsername(username);
	}

	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return roleMapper.findAllRole();
	}

	@Override
	public List<Role> findAllRoleWithResource() {
		// TODO Auto-generated method stub
		return roleMapper.findAllRoleWithResource();
	}

	@Override
	public Integer addRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRole(role);
	}

	@Override
	public Role findByRid(Long rId) {
		// TODO Auto-generated method stub
		return roleMapper.findByRid(rId);
	}

	@Override
	public Integer updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.updateRole(role);
	}

	@Override
	public Integer deleteRole(Long rId) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRole(rId);
	}

}
