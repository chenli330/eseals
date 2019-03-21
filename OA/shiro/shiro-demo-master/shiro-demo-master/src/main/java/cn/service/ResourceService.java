package cn.service;

import java.util.List;

import cn.pojo.Resource;

public interface ResourceService {
	List<Resource> findResourcesByRoleId(Long id);

	List<Resource> findAllResource();
}
