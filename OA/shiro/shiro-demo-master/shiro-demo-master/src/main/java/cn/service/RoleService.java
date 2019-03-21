package cn.service;

import java.util.List;

import cn.pojo.Role;

public interface RoleService {
	List<Role> findRoleByUsername(String username);

	List<Role> findAllRole();

	List<Role> findAllRoleWithResource();

	Integer addRole(Role role);

	Role findByRid(Long rId);

	Integer updateRole(Role role);

	Integer deleteRole(Long rId);
}
