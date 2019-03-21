package cn.service;

import cn.pojo.Role_resource;

public interface Role_resourceService {
	int insert(Role_resource record);

	int deleteByRid(Long id);
}
