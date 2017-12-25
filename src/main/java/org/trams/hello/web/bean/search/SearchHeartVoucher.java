package org.trams.hello.web.bean.search;

public class SearchHeartVoucher {
	private SearchCommon common;
	
	private String startDate;
	
	private String endDate;
	

	/**
	 * 
	 */
	public SearchHeartVoucher() {
		common = new SearchCommon();
		
		startDate = null;
		
		endDate = null;
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

	
}
