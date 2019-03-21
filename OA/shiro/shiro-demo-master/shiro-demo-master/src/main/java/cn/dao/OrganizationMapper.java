package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.Organization;

public interface OrganizationMapper {
	/**
	 * 获取全部的组织
	 * @return
	 */
    List<Organization> getAllOrgannization();
    
    /**
     * 根据父节点获取所有的子节点
     * @param pid
     * @return
     */
    List<Organization> getByParentid(Integer pid);
    
    Organization getOrganizationById(Integer id);
    
    /**
     * 更新organization
     * @param organization	新的organization
     * @return
     */
    Integer updateOrganization(Organization organization);
    
    Integer deleteById(Integer id);
    
    /**
     * 查找指定id部门的所有一代子部门
     * @param parentid
     * @return
     */
    List<Organization> findByParentid(Integer parentid);
    
    Integer addOrganization(Organization organization);
    
}