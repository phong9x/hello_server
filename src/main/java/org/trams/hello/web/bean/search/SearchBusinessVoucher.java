package org.trams.hello.web.bean.search;

public class SearchBusinessVoucher {
	private SearchCommon common;
	
	private String startDate;
	
	private String endDate;
	
	private Short businessType;
	
	private Integer managerYear;
	
	private Integer businessSubId;
	
	private String order;

	/**
	 * 
	 */
	public SearchBusinessVoucher() {
		common = new SearchCommon();
		
		startDate = null;
		
		endDate = null;
		
		businessType = null;
		
		managerYear = null;
		
		businessSubId = null;
		
		order = "createDate";
	}

	public SearchCommon getCommon() {
		return common;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Short getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Short businessType) {
		this.businessType = businessType;
	}

	public Integer getManagerYear() {
		return managerYear;
	}

	public void setManagerYear(Integer managerYear) {
		this.managerYear = managerYear;
	}

	public Integer getBusinessSubId() {
		return businessSubId;
	}

	public void setBusinessSubId(Integer businessSubId) {
		this.businessSubId = businessSubId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
	
}
