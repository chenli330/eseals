package cn.pojo;

import java.util.List;

public class Role {
	private Long rId;

	private String role;

	private String description;

	private String resourceIds;

	private Boolean available;

	private List<Resource> resourceList;

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	public Role(Long id, String role, String description, String resourceIds, Boolean available) {
		this.rId = id;
		this.role = role;
		this.description = description;
		this.resourceIds = resourceIds;
		this.available = available;
	}

	public Role() {
		super();
	}

	public Long getrId() {
		return rId;
	}

	public void setrId(Long rId) {
		this.rId = rId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds == null ? null : resourceIds.trim();
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}