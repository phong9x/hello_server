package org.trams.hello.web.bean.search;

public class SearchProfitCounselorCenter {
	SearchCommon common;
	
	private String startTime;
	
	private String endTime;

	private Boolean lastWeek;
	
	private Boolean lastMonth;
	
	private Boolean useHelloCoin;
	
	private Boolean useVoucher;
	
	private Integer counselingCenterId;
	
	public SearchProfitCounselorCenter() {
		common = new SearchCommon();
		
		startTime = null;
		
		endTime = null;
		
		lastMonth = true;
		
		lastWeek = false;
		
		useHelloCoin = false;
		
		useVoucher = false;
		
		counselingCenterId = null;
		
	}

	public Integer getCounselingCenterId() {
		return counselingCenterId;
	}

	public void setCounselingCenterId(Integer counselingCenterId) {
		this.counselingCenterId = counselingCenterId;
	}

	public SearchCommon getCommon() {
		return common;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Boolean getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(Boolean lastWeek) {
		this.lastWeek = lastWeek;
	}

	public Boolean getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(Boolean lastMonth) {
		this.lastMonth = lastMonth;
	}

	public Boolean getUseHelloCoin() {
		return useHelloCoin;
	}

	public void setUseHelloCoin(Boolean useHelloCoin) {
		this.useHelloCoin = useHelloCoin;
	}

	public Boolean getUseVoucher() {
		return useVoucher;
	}

	public void setUseVoucher(Boolean useVoucher) {
		this.useVoucher = useVoucher;
	}
	
	
	
}
