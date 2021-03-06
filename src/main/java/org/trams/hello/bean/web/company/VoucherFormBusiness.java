package org.trams.hello.bean.web.company;

import java.util.Date;

public class VoucherFormBusiness {
	private Integer userId;
	private String fullname;
	private Date dayOfBirth;
	private Integer manageYear;
	private String businessSubName;
	private Date useVoucherDate;
	private Integer countVoucherUsed;
	private Integer fee;

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(userId);
		sb.append("|");
		sb.append(fullname);
		sb.append("|");
		sb.append(dayOfBirth);
		sb.append("|");
		sb.append(manageYear);
		sb.append("|");
		sb.append(businessSubName);
		sb.append("|");
		sb.append(useVoucherDate);
		sb.append("|");
		sb.append(countVoucherUsed);
		sb.append("|");
		sb.append(fee * countVoucherUsed);
		return sb.toString();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
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

	public Date getUseVoucherDate() {
		return useVoucherDate;
	}

	public void setUseVoucherDate(Date useVoucherDate) {
		this.useVoucherDate = useVoucherDate;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Integer getCountVoucherUsed() {
		return countVoucherUsed;
	}

	public void setCountVoucherUsed(Integer countVoucherUsed) {
		this.countVoucherUsed = countVoucherUsed;
	}

}
