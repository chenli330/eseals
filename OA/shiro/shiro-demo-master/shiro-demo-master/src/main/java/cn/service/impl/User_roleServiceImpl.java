package cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.User_roleMapper;
import cn.pojo.User_role;
import cn.service.User_roleService;

@Service
public class User_roleServiceImpl implements User_roleService {

	@Autowired
	private User_roleMapper user_roleMapper;

	@Override
	public int insert(User_role record) {
		// TODO Auto-generated method stub
		return user_roleMapper.insert(record);
	}

}
