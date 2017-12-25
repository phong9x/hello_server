package org.trams.hello.web.bean.search;

import java.util.Calendar;

public class SearchProfitCenter {
private Integer centerId;
private Integer month;
private Integer year;
private String orderBy;
private Integer yearNow;

/**
 * 
 */
public SearchProfitCenter() {
	Calendar now = Calendar.getInstance();
	now.add(Calendar.MONTH, -1);
	month = now.get(Calendar.MONTH)+1;
	year = now.get(Calendar.YEAR);
	yearNow = now.get(Calendar.YEAR);
	orderBy="startTime";
}

public Integer getYearNow() {
	return yearNow;
}

public void setYearNow(Integer yearNow) {
	this.yearNow = yearNow;
}

public Integer getCenterId() {
	return centerId;
}
public void setCenterId(Integer centerId) {
	this.centerId = centerId;
}
public Integer getMonth() {
	return month;
}
public void setMonth(Integer month) {
	this.month = month;
}
public Integer getYear() {
	return year;
}
public void setYear(Integer year) {
	this.year = year;
}
public String getOrderBy() {
	return orderBy;
}
public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
}


}
