package cn.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.node.LongNode;

import cn.pojo.Role;
import cn.pojo.Role_resource;
import cn.service.RoleService;
import cn.service.Role_resourceService;

@Transactional(rollbackFor={Exception.class, RuntimeException.class})
@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleservice;

	@Autowired
	private Role_resourceService role_resourceService;

	@RequiresPermissions("role:view")
	@RequestMapping("showRole")
	public String showRole(Model model) {
		List<Role> roleWithResourceList = roleservice.findAllRoleWithResource();
		model.addAttribute("roleWithResourceList", roleWithResourceList);
		return "role/showRole";
	}

	@RequiresPermissions("role:create")
	@RequestMapping("toAddRole")
	public String toAddRole(Model model) {
		List<Role> roleWithResourceList = roleservice.findAllRoleWithResource();
		model.addAttribute("roleWithResourceList", roleWithResourceList);
		return "role/addRole";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @param resourceIdStrings
	 * @return
	 */
	@RequestMapping("doAddRole")
	public String doAddRole(Role role,
			@RequestParam(value = "resourceIdStrings", required = false) String resourceIdStrings) {
		// addRole()这个方法会自动给role设置上新增的主键，详细见roleMapper.xml
		roleservice.addRole(role);
		String resourceIdString[] = resourceIdStrings.split("/");
		Role_resource record = new Role_resource();
		for (String string : resourceIdString) {
			if (string.length() > 0) {
				record.setResourceId(new Long(string));
				record.setRoleId(role.getrId());
				role_resourceService.insert(record);
			}
		}
		// 调用showRole()
		return "redirect:showRole";
	}

	@RequiresPermissions("role:update")
	@RequestMapping("toEditRole/{rId}")
	public String toEditRole(@PathVariable(value = "rId") Long rId, Model model) {
		Role role = roleservice.findByRid(rId);
		String resourceList = JSONArray.toJSONString(role.getResourceList());
		model.addAttribute("role", role);
		model.addAttribute("resourceList", resourceList);
		return "role/editRole";
	}

	@RequiresPermissions("role:update")
	@RequestMapping("doEditRole")
	public String doEditRole(Role role,
			@RequestParam(value = "resourceIdStrings", required = false) String resourceIdStrings) {
		roleservice.updateRole(role);
		// 修改role权限之前，删除role之前的所有权限
		role_resourceService.deleteByRid(role.getrId());
		String resourceIdString[] = resourceIdStrings.split("/");
		Role_resource record = new Role_resource();
		for (String string : resourceIdString) {
			if (string.length() > 0) {
				record.setResourceId(new Long(string));
				record.setRoleId(role.getrId());
				role_resourceService.insert(record);
			}
		}
		// 调用showRole()
		return "redirect:showRole";
	}

	@RequiresPermissions("role:delete")
	@RequestMapping("deleteRole/{rId}")
	public String doRoleDelete(@PathVariable(value = "rId") Long rId,Model model) {
		roleservice.deleteRole(rId);
		role_resourceService.deleteByRid(rId);
		// 调用showRole()
		List<Role> roleWithResourceList = roleservice.findAllRoleWithResource();
		model.addAttribute("roleWithResourceList", roleWithResourceList);
		return "role/showRole";
	}

}
