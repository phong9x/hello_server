package org.trams.hello.bean.api;

import java.util.ArrayList;
import java.util.List;

public class MemberStatisticsChart {
	private List<Object> genderChart;
	
	private List<Object> ageChart;
	
	private List<Object> memberChart;
	
	private List<Object> osChart;

	public MemberStatisticsChart(){
		genderChart = new ArrayList<>();
		ageChart = new ArrayList<>();
		memberChart = new ArrayList<>();
		osChart = new ArrayList<>();
	};
	
	public List<Object> getGenderChart() {
		return genderChart;
	}

	public void setGenderChart(List<Object> genderChart) {
		this.genderChart = genderChart;
	}

	public List<Object> getAgeChart() {
		return ageChart;
	}

	public void setAgeChart(List<Object> ageChart) {
		this.ageChart = ageChart;
	}

	public List<Object> getMemberChart() {
		return memberChart;
	}

	public void setMemberChart(List<Object> memberChart) {
		this.memberChart = memberChart;
	}

	public List<Object> getOsChart() {
		return osChart;
	}

	public void setOsChart(List<Object> osChart) {
		this.osChart = osChart;
	}

	
	
}
