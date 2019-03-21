package cn.pojo;

import java.util.List;

public class User {
	private Long id;

	private Long organizationId;

	private String username;

	private String password;

	private String salt;

	private String roleIds;

	private Boolean locked;

	private Role role;

	private Organization organization;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(Long id, Long organizationId, String username, String password, String salt, String roleIds,
			Boolean locked) {
		this.id = id;
		this.organizationId = organizationId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.roleIds = roleIds;
		this.locked = locked;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", organizationId=" + organizationId + ", username=" + username + ", password="
				+ password + ", salt=" + salt + ", roleIds=" + roleIds + ", locked=" + locked + "]";
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
}