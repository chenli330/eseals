package cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.Role_resourceMapper;
import cn.pojo.Role_resource;
import cn.service.Role_resourceService;

@Service
public class Role_resourceServiceImpl implements Role_resourceService {

	@Autowired
	private Role_resourceMapper role_resourceMapper;
	
	@Override
	public int insert(Role_resource record) {
		// TODO Auto-generated method stub
		return role_resourceMapper.insert(record);
	}

	@Override
	public int deleteByRid(Long id) {
		// TODO Auto-generated method stub
		return role_resourceMapper.deleteByRid(id);
	}

	
}
