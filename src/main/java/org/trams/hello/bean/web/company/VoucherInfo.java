package org.trams.hello.bean.web.company;

import java.util.Date;

public class VoucherInfo {
	private Integer id;
	private String username;
	private String fullname;
	private Date dayOfBirth;
	private Integer manageYear;
	private String businessSubName;
	private Date useVoucherDate;
	private Integer fee;
	private Integer countVoucherUsed;
	private Integer countTotalVoucher;
	private Integer countRemainVoucher;
	private Date createDate;
	

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getCountTotalVoucher() {
		return countTotalVoucher;
	}

	public void setCountTotalVoucher(Integer countTotalVoucher) {
		this.countTotalVoucher = countTotalVoucher;
	}

	public Integer getCountRemainVoucher() {
		return countRemainVoucher;
	}

	public void setCountRemainVoucher(Integer countRemainVoucher) {
		this.countRemainVoucher = countRemainVoucher;
	}

}
