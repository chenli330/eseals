package cn.pojo;

public class Organization {

	private Long oId;

	private String name;

	private Long parentid;

	private String parentids;

	private Boolean available;

	public Organization(Long id, String name, Long parentId, String parentIds, Boolean available) {
		this.oId = id;
		this.name = name;
		this.parentid = parentId;
		this.parentids = parentIds;
		this.available = available;
	}

	public Long getoId() {
		return oId;
	}

	public void setoId(Long oId) {
		this.oId = oId;
	}

	public Organization() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getParentids() {
		return parentids;
	}

	public void setParentids(String parentids) {
		this.parentids = parentids;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}