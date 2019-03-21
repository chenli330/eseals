package cn.dao;

import java.util.List;

import cn.pojo.Resource;

public interface ResourceMapper {
    List<Resource> findResourcesByRoleId(Long id);
    
    List<Resource> findAllResource();
}