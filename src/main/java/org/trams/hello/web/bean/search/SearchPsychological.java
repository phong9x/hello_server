package org.trams.hello.web.bean.search;

public class SearchPsychological {
SearchCommon common;
	
	String startDate;
	
	String endDate;	
	
	Integer testId;
	
	boolean statusWaiting;
	
	boolean statusFinishPayment;
	
	boolean statusCancelPayment;
	
	boolean statusWaiverPayment;
	
	boolean statusCancelRequest;
	
	public SearchPsychological(){
		common = new SearchCommon();
		startDate = null;
		endDate = null;
		common.setType("memberName");
		testId = null;
		statusWaiting = true;
		statusFinishPayment = true;
		statusCancelPayment = true;
		statusWaiverPayment = true;
		statusCancelRequest = true;
	}

	public SearchCommon getCommon() {
		return common;
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

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public boolean isStatusWaiting() {
		return statusWaiting;
	}

	public void setStatusWaiting(boolean statusWaiting) {
		this.statusWaiting = statusWaiting;
	}

	public boolean isStatusFinishPayment() {
		return statusFinishPayment;
	}

	public void setStatusFinishPayment(boolean statusFinishPayment) {
		this.statusFinishPayment = statusFinishPayment;
	}

	public boolean isStatusCancelPayment() {
		return statusCancelPayment;
	}

	public void setStatusCancelPayment(boolean statusCancelPayment) {
		this.statusCancelPayment = statusCancelPayment;
	}

	public boolean isStatusWaiverPayment() {
		return statusWaiverPayment;
	}

	public void setStatusWaiverPayment(boolean statusWaiverPayment) {
		this.statusWaiverPayment = statusWaiverPayment;
	}

	public boolean isStatusCancelRequest() {
		return statusCancelRequest;
	}

	public void setStatusCancelRequest(boolean statusCancelRequest) {
		this.statusCancelRequest = statusCancelRequest;
	}

	public void setCommon(SearchCommon common) {
		this.common = common;
	}

	
}
