package cn.dao;

import java.util.List;

import cn.pojo.Resource;
import cn.pojo.User;

public interface UserMapper {
	User findByUsername(String username);

	User findById(Long id);

	List<Resource> findResorecesByUsername(String username);

	List<User> findAllUser();

	Integer updateUser(User user);

	Integer addUser(User user);
	
	Integer deleteUserById(Long id);

}