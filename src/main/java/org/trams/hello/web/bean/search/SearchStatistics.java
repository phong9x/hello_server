package org.trams.hello.web.bean.search;

import java.util.Calendar;

import org.trams.hello.web.common.utils.DataUtils;

public class SearchStatistics {
	
	private SearchCommon common ;
	
	private String type;
	
	private boolean ios;
	
	private boolean android;
	
	private String startDate;
	
	private String endDate;
	
	private Integer yearDaily;
	
	private Integer fromWeek;
	
	private Integer toWeek;
	
	private Integer yearWeekly;
	
	private Integer fromMonth;
	
	private Integer toMonth;
	
	private Integer yearMonthly;
	
	private String version;
	
	private Integer centerId;
	
	public SearchStatistics(){
		common = new SearchCommon();
		Calendar c = Calendar.getInstance(); 
		common.type ="deviceId";
		
		type = "daily";
		
		ios = false;
		android = false;
		
		startDate = DataUtils.parseStringFromDate(c.getTime(), "yyyy/MM/dd");
		endDate = DataUtils.parseStringFromDate(c.getTime(), "yyyy/MM/dd");
		yearDaily = null;
		
		fromWeek = c.get(Calendar.WEEK_OF_YEAR);
		toWeek = c.get(Calendar.WEEK_OF_YEAR);
		yearWeekly = null;
		
		fromMonth = null;
		toMonth = null;
		yearMonthly = null;
		
	}
	
	
	
	public Integer getCenterId() {
		return centerId;
	}



	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}



	public SearchCommon getCommon() {
		return common;
	}



	public void setCommon(SearchCommon common) {
		this.common = common;
	}



	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public Integer getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Integer getToMonth() {
		return toMonth;
	}

	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
	}

	public Integer getYearMonthly() {
		return yearMonthly;
	}

	public void setYearMonthly(Integer yearMonthly) {
		this.yearMonthly = yearMonthly;
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

	public Integer getYearDaily() {
		return yearDaily;
	}

	public void setYearDaily(Integer yearDaily) {
		this.yearDaily = yearDaily;
	}

	public Integer getFromWeek() {
		return fromWeek;
	}

	public void setFromWeek(Integer fromWeek) {
		this.fromWeek = fromWeek;
	}

	public Integer getToWeek() {
		return toWeek;
	}

	public void setToWeek(Integer toWeek) {
		this.toWeek = toWeek;
	}

	public Integer getYearWeekly() {
		return yearWeekly;
	}

	public void setYearWeekly(Integer yearWeekly) {
		this.yearWeekly = yearWeekly;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIos() {
		return ios;
	}

	public void setIos(boolean ios) {
		this.ios = ios;
	}

	public boolean isAndroid() {
		return android;
	}

	public void setAndroid(boolean android) {
		this.android = android;
	}
	
	
}
