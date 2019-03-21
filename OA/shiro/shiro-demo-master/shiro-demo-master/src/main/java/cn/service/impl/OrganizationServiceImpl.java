package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.OrganizationMapper;
import cn.pojo.Organization;
import cn.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationMapper organizatonMapper;

	@Override
	public List<Organization> getAllOrgannization() {
		// TODO Auto-generated method stub
		return organizatonMapper.getAllOrgannization();
	}

	@Override
	public List<Organization> getByParentid(Integer pid) {
		// TODO Auto-generated method stub
		if (pid == null) {
			return organizatonMapper.getByParentid(0);
		}
		return organizatonMapper.getByParentid(pid);
	}

	@Override
	public Organization getOrganizationById(Integer id) {
		// TODO Auto-generated method stub
		return organizatonMapper.getOrganizationById(id);
	}

	@Override
	public Integer updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return organizatonMapper.updateOrganization(organization);
	}

	@Override
	public Integer deleteById(Integer id) {
		// 获取目标部门的所有一代子部门
		List<Organization> listOrganization = organizatonMapper.findByParentid(id);
		// 循环更新这些一代子部门，修改它们 父节点 为 目标部门 的父节点
		if (listOrganization.size() > 0) {
			for (Organization organization : listOrganization) {
				Long parentid = organizatonMapper.getOrganizationById(id).getParentid();
				organization.setParentid(parentid);
				organizatonMapper.updateOrganization(organization);
			}
		}
		// 删除目标部门
		return organizatonMapper.deleteById(id);
	}

	@Override
	public List<Organization> findByParentid(Integer parentid) {
		// TODO Auto-generated method stub
		return organizatonMapper.findByParentid(parentid);
	}

	@Override
	public Integer addOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return organizatonMapper.addOrganization(organization);
	}

}
