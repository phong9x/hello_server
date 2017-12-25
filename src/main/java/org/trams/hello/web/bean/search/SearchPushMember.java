package org.trams.hello.web.bean.search;

public class SearchPushMember {
	SearchCommon common;
	
	Boolean sendAll;
	
	Integer yearFrom;

	Integer yearTo;

	Boolean man;

	Boolean women;

	Boolean paymentYes;

	Boolean paymentNo;

	Boolean counselingYes;

	Boolean counselingNo;

	Boolean testPsyYes;

	Boolean testPsyNo;

	Boolean memberNormal;

	Boolean memberBusiness;

	Boolean ios;

	Boolean android;
	
	Boolean voucherYes;
	
	Boolean voucherNo;

	String loginDateFrom;

	String loginDateTo;

	Integer[] businessSubIds;

	Integer[] versionIosIds;

	Integer[] versionAndroidIds;
	
	Integer[] deleteUserIds;

	public SearchPushMember() {
		common = new SearchCommon();
		
		sendAll = false;
		
		deleteUserIds = null;

		yearFrom = null;

		yearTo = null;

		man = false;

		women = false;

		paymentYes = false;

		paymentNo = false;

		counselingYes = false;

		counselingNo = false;

		testPsyYes = false;

		testPsyNo = false;

		memberNormal = false;

		memberBusiness = false;

		ios = false;

		android = false;

		loginDateFrom = null;

		loginDateTo = null;

		businessSubIds = null;

		versionIosIds = null;

		versionAndroidIds = null;

		voucherNo = false;
		
		voucherYes = false;
	}
	
	

	public Integer[] getDeleteUserIds() {
		return deleteUserIds;
	}



	public void setDeleteUserIds(Integer[] deleteUserIds) {
		this.deleteUserIds = deleteUserIds;
	}



	public Boolean getSendAll() {
		return sendAll;
	}



	public void setSendAll(Boolean sendAll) {
		this.sendAll = sendAll;
	}



	public Boolean getVoucherYes() {
		return voucherYes;
	}



	public void setVoucherYes(Boolean voucherYes) {
		this.voucherYes = voucherYes;
	}



	public Boolean getVoucherNo() {
		return voucherNo;
	}



	public void setVoucherNo(Boolean voucherNo) {
		this.voucherNo = voucherNo;
	}



	public SearchCommon getCommon() {
		return common;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	public Integer getYearFrom() {
		return yearFrom;
	}

	public void setYearFrom(Integer yearFrom) {
		this.yearFrom = yearFrom;
	}

	public Integer getYearTo() {
		return yearTo;
	}

	public void setYearTo(Integer yearTo) {
		this.yearTo = yearTo;
	}

	public Boolean getMan() {
		return man;
	}

	public void setMan(Boolean man) {
		this.man = man;
	}

	public Boolean getWomen() {
		return women;
	}

	public void setWomen(Boolean women) {
		this.women = women;
	}

	public Boolean getPaymentYes() {
		return paymentYes;
	}

	public void setPaymentYes(Boolean paymentYes) {
		this.paymentYes = paymentYes;
	}

	public Boolean getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(Boolean paymentNo) {
		this.paymentNo = paymentNo;
	}

	public Boolean getCounselingYes() {
		return counselingYes;
	}

	public void setCounselingYes(Boolean counselingYes) {
		this.counselingYes = counselingYes;
	}

	public Boolean getCounselingNo() {
		return counselingNo;
	}

	public void setCounselingNo(Boolean counselingNo) {
		this.counselingNo = counselingNo;
	}

	public Boolean getTestPsyYes() {
		return testPsyYes;
	}

	public void setTestPsyYes(Boolean testPsyYes) {
		this.testPsyYes = testPsyYes;
	}

	public Boolean getTestPsyNo() {
		return testPsyNo;
	}

	public void setTestPsyNo(Boolean testPsyNo) {
		this.testPsyNo = testPsyNo;
	}

	public Boolean getMemberNormal() {
		return memberNormal;
	}

	public void setMemberNormal(Boolean memberNormal) {
		this.memberNormal = memberNormal;
	}

	public Boolean getMemberBusiness() {
		return memberBusiness;
	}

	public void setMemberBusiness(Boolean memberBusiness) {
		this.memberBusiness = memberBusiness;
	}

	public Boolean getIos() {
		return ios;
	}

	public void setIos(Boolean ios) {
		this.ios = ios;
	}

	public Boolean getAndroid() {
		return android;
	}

	public void setAndroid(Boolean android) {
		this.android = android;
	}

	public String getLoginDateFrom() {
		return loginDateFrom;
	}

	public void setLoginDateFrom(String loginDateFrom) {
		this.loginDateFrom = loginDateFrom;
	}

	public String getLoginDateTo() {
		return loginDateTo;
	}

	public void setLoginDateTo(String loginDateTo) {
		this.loginDateTo = loginDateTo;
	}

	public Integer[] getBusinessSubIds() {
		return businessSubIds;
	}

	public void setBusinessSubIds(Integer[] businessSubIds) {
		this.businessSubIds = businessSubIds;
	}

	public Integer[] getVersionIosIds() {
		return versionIosIds;
	}

	public void setVersionIosIds(Integer[] versionIosIds) {
		this.versionIosIds = versionIosIds;
	}

	public Integer[] getVersionAndroidIds() {
		return versionAndroidIds;
	}

	public void setVersionAndroidIds(Integer[] versionAndroidIds) {
		this.versionAndroidIds = versionAndroidIds;
	}

}
