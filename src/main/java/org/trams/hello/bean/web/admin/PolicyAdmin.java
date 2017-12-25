package org.trams.hello.bean.web.admin;

import java.util.Date;

public class PolicyAdmin {
	private Integer id;
	private Float vesion;
	private Date createDate;
	private Integer adminId;
	private String adminName;
	private Short isShow;
	private Short typePolicy;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getVesion() {
		return vesion;
	}

	public void setVesion(Float vesion) {
		this.vesion = vesion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Short getIsShow() {
		return isShow;
	}

	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}

	public Short getTypePolicy() {
		return typePolicy;
	}

	public void setTypePolicy(Short typePolicy) {
		this.typePolicy = typePolicy;
	}

}
