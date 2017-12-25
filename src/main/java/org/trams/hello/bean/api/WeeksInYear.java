package org.trams.hello.bean.api;

import java.util.ArrayList;
import java.util.List;

public class WeeksInYear {
	private Integer year;
	
	private List<Weekly> listWeek = new ArrayList<>();

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Weekly> getListWeek() {
		return listWeek;
	}

	public void setListWeek(List<Weekly> listWeek) {
		this.listWeek = listWeek;
	}
	
}
