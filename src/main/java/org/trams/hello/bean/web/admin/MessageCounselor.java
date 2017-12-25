package org.trams.hello.bean.web.admin;

import java.util.Date;

public class MessageCounselor {
	private Integer id;
	private String adminName;
	private Date createDate;
	private String content;
	private String userReceiveMessageId;
	private String userReceiveFullName;
	private Integer firstUserReceiveMessageId;
	private String firstUserRecName;
	private Integer totalUserRec;

	public String getFirstUserRecName() {
		return firstUserRecName;
	}

	public void setFirstUserRecName(String firstUserRecName) {
		this.firstUserRecName = firstUserRecName;
	}

	public Integer getTotalUserRec() {
		return totalUserRec;
	}

	public void setTotalUserRec(Integer totalUserRec) {
		this.totalUserRec = totalUserRec;
	}

	public Integer getFirstUserReceiveMessageId() {
		return firstUserReceiveMessageId;
	}

	public void setFirstUserReceiveMessageId(Integer firstUserReceiveMessageId) {
		this.firstUserReceiveMessageId = firstUserReceiveMessageId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserReceiveMessageId() {
		return userReceiveMessageId;
	}

	public void setUserReceiveMessageId(String userReceiveMessageId) {
		this.userReceiveMessageId = userReceiveMessageId;
	}

	public String getUserReceiveFullName() {
		return userReceiveFullName;
	}

	public void setUserReceiveFullName(String userReceiveFullName) {
		this.userReceiveFullName = userReceiveFullName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
