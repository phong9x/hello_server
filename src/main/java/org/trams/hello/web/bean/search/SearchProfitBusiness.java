package org.trams.hello.web.bean.search;

public class SearchProfitBusiness {
	SearchCommon common;
	
	private Integer businessId;
	
	private Integer manageYear;

	private Integer businessSubId;
	
	private String fullName;
	
	public SearchProfitBusiness() {
		common = new SearchCommon();
		
		businessId = null;
		
		manageYear = null;
		
		businessSubId= null;
		
		fullName = null;
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public SearchCommon getCommon() {
		return common;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getManageYear() {
		return manageYear;
	}

	public void setManageYear(Integer manageYear) {
		this.manageYear = manageYear;
	}

	public Integer getBusinessSubId() {
		return businessSubId;
	}

	public void setBusinessSubId(Integer businessSubId) {
		this.businessSubId = businessSubId;
	}

	
	
}
