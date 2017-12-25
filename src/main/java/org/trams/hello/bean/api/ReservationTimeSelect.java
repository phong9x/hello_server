package org.trams.hello.bean.api;

import java.util.List;

public class ReservationTimeSelect {
	private Integer hour;
	private List<Integer> minutes;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public List<Integer> getMinutes() {
		return minutes;
	}

	public void setMinutes(List<Integer> minutes) {
		this.minutes = minutes;
	}

}
