package org.trams.hello.bean;

import java.util.Date;

public class BusinessVoucher {

	private Integer voucherId;
	
	private String fullName;
	
	private String businessName;
	
	private Integer manageYear;
	
	private String businessSubName;
	
	private String voucherName;
	
	private Integer voucherNumber;
	
	private Date fromDate;
	
	private Date toDate;
	
	private String reason;
	
	private String adminName;

	private Integer userId;
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getVoucherNumber() {
		return voucherNumber;
	}

	public void setVoucherNumber(Integer voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Integer getManageYear() {
		return manageYear;
	}

	public void setManageYear(Integer manageYear) {
		this.manageYear = manageYear;
	}

	public String getBusinessSubName() {
		return businessSubName;
	}

	public void setBusinessSubName(String businessSubName) {
		this.businessSubName = businessSubName;
	}
	
	
}
