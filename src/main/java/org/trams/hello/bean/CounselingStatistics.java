package org.trams.hello.bean;

public class CounselingStatistics {
	private Integer id;
	
	private String title;
	
	private Integer totalReservation;
	
	private Integer totalCounseling;
	
	public CounselingStatistics(){
		totalReservation = 0;
		totalCounseling = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getTotalReservation() {
		return totalReservation;
	}

	public void setTotalReservation(Integer totalReservation) {
		this.totalReservation = totalReservation;
	}

	public Integer getTotalCounseling() {
		return totalCounseling;
	}

	public void setTotalCounseling(Integer totalCounseling) {
		this.totalCounseling = totalCounseling;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

}
