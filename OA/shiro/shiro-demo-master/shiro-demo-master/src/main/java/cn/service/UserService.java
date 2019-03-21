package cn.service;

import cn.pojo.Resource;
import cn.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	User findByUsername(String username);

	User findById(Long id);

	List<Resource> findResourceByUsername(String username);

	List<User> findAllUser();

	Set<String> findPermissionsByUsername(String username);

	Integer updateUser(User user);

	Integer addUser(User user);

	Integer deleteUserById(Long id);
}
