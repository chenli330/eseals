package cn.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.globalConfig.TheGlobalConfig;
import cn.pojo.Role;
import cn.pojo.User;
import cn.pojo.User_role;
import cn.service.RoleService;
import cn.service.UserService;
import cn.service.User_roleService;
import cn.util.Handler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor={Exception.class, RuntimeException.class})
@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;

	@Autowired
	private User_roleService user_roleService;

	@Resource
	private RoleService roleService;

	@RequiresPermissions("user:view")
	@RequestMapping(value="findAllUser")
	public String findAllUser(Model model) {
		model.addAttribute("userList", userService.findAllUser());
		return "user/showUser";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:delete")
	@RequestMapping("deleteUser/{id}")
	public String deleteUserById(@PathVariable(value = "id") Integer id, Model model) {
		userService.deleteUserById(new Long(id));
		return "redirect:/user/findAllUser";
	}

	/**
	 * 去修改密码页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:update")
	@RequestMapping("toUpdateUserPass/{id}")
	public String toUpdateUserPass(@PathVariable(value = "id") Integer id, Model model) {
		model.addAttribute("needUpdateUserId", id);
		return "user/updatePass";
	}

	@RequiresPermissions("user:update")
	@RequestMapping("doUpdateUserPass")
	public String doUpdateUserPass(HttpSession session, User user) {
		userService.updateUser(user);
		return "redirect:findAllUser";
	}

	/**
	 * 去增加用户页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:create")
	@RequestMapping("toAddUser")
	public String toAddUser(Model model) {
		List<Role> roleList = roleService.findAllRole();
		model.addAttribute("roleList", roleList);
		return "user/addUser";
	}

	@RequiresPermissions("user:create")
	@RequestMapping("doAddUser")
	public String doAddUser(User user, Model model) {
		userService.addUser(Handler.passwordHandler(user));
		// 这个user是没有id的，数据库会自动生成id，根据这个username去数据库查询这个user
		User user2 = userService.findByUsername(user.getUsername());
		user_roleService.insert(new User_role(null, user2.getId(), new Long(user2.getRoleIds())));
		return "redirect:findAllUser";
	}

	@RequiresPermissions("user:update")
	@RequestMapping("toEdit/{id}")
	public String toEdit(Model model, @PathVariable(value = "id") Long id) {
		User user = userService.findById(id);
		List<Role> roleList = roleService.findAllRole();
		model.addAttribute("roleList", roleList);
		model.addAttribute("needEditUser", user);
		return "user/edit";
	}

	@RequestMapping("doEdit")
	public String doEdit(User user) {

		userService.updateUser(user);
		return "redirect:findAllUser";
	}

	/**
	 * 验证用户名唯一
	 * @param username 待验证username
	 * @return
	 */
	@RequestMapping("queryNameUnique")
	@ResponseBody
	public String queryNameUnique(@RequestParam("username") String username) {
		Map<String, String> resultMap = new HashMap<>();
		User user = userService.findByUsername(username);
		if (user == null) {
			resultMap.put("result", "true");
			return JSONArray.toJSONString(resultMap);
		}
		resultMap.put("result", "fasle");
		return JSONArray.toJSONString(resultMap);
	}
	
}
