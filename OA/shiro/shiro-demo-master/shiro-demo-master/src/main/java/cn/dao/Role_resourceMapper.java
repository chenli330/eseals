package cn.dao;

import cn.pojo.Role_resource;

public interface Role_resourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role_resource record);

    int insertSelective(Role_resource record);

    Role_resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role_resource record);

    int updateByPrimaryKey(Role_resource record);
    
    int deleteByRid(Long id);
}