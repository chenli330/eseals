package cn.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.pojo.Organization;
import cn.service.OrganizationService;

@Transactional(rollbackFor={Exception.class, RuntimeException.class})
@Controller
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	/**
	 * 异步获取组织架构的数据，用于ztree展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "ztreeGetOrganization", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String ztreeGetOrganization() {
		return JSONArray.toJSONString(organizationService.getAllOrgannization());
	}

	@RequiresPermissions("organization:view")
	@RequestMapping("")
	public String toShowOrganization() {
		return "organization/show";
	}

	/**
	 * 去节点详细信息页面
	 * 
	 * @param id
	 *            节点id
	 * @param model
	 * @return
	 */
	@RequestMapping("toOrganizationDetail/{oId}")
	public String toOrganizationDetail(@PathVariable(value = "oId") Integer oId, Model model) {
		model.addAttribute("current_organization", organizationService.getOrganizationById(oId));
		return "organization/detail";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping("toOrganizationEdit/{oId}")
	public String toOrganizationEdit(@PathVariable(value = "oId") Integer oId, Model model) {
		model.addAttribute("current_organization", organizationService.getOrganizationById(oId));
		return "organization/edit";
	}

	@RequiresPermissions("organization:update")
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String update(Organization organization) {
		organizationService.updateOrganization(organization);
		return "organization/success";
	}

	@RequiresPermissions("organization:create")
	@RequestMapping(value = "toAddOrganization/{oId}", method = RequestMethod.GET)
	public String toAddOrganization(@PathVariable(value="oId") Integer oId,Model model) {
		model.addAttribute("parentid",oId);
		return "organization/addOrganization";
	}

	@RequiresPermissions("organization:create")
	@RequestMapping(value = "addOrganization", method = RequestMethod.GET)
	public String addOrganization(Organization organization) {
		organizationService.addOrganization(organization);
		return "organization/success";
	}
	
	@RequiresPermissions("organization:delete")
	@RequestMapping(value = "doDelOrganization/{oId}")
	public String doDelOrganization(@PathVariable(value="oId")Integer oId) {
		organizationService.deleteById(oId);
		return "organization/success";
	}
	
	@RequiresPermissions("organization:update")
	@RequestMapping(value = "toMoveOrganization/{oId}")
	public String toMoveOrganization(@PathVariable(value="oId")Integer oId,Model model) {
		model.addAttribute("needMoveId", oId);
		return "organization/moveOrganization";
	}
	
	@RequiresPermissions("organization:update")
	@RequestMapping(value = "doMoveOrganization")
	public String doMoveOrganization(Organization organization) {
		organizationService.updateOrganization(organization);
		return "organization/success";
	}
}
