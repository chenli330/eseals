package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.ResourceMapper;
import cn.pojo.Resource;
import cn.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Resource> findResourcesByRoleId(Long id) {
		// TODO Auto-generated method stub
		return resourceMapper.findResourcesByRoleId(id);
	}

	@Override
	public List<Resource> findAllResource() {
		// TODO Auto-generated method stub
		return resourceMapper.findAllResource();
	}

}
