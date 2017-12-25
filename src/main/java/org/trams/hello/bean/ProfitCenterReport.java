package org.trams.hello.bean;

import java.util.Date;

public class ProfitCenterReport {
	private Long id;
	private String counselorName;
	private String userName;
	private Date createDate;
	private Date startTime;
	private String typeCoin;
	private Integer fee;
	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCounselorName() {
		return counselorName;
	}
	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTypeCoin() {
		return typeCoin;
	}
	public void setTypeCoin(String typeCoin) {
		this.typeCoin = typeCoin;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	
	
}
