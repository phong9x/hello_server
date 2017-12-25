package org.trams.hello.bean.web.counselingCenter;

import java.util.Date;

public class CounselorSummary {
	private Integer id;
	private String thumb;
	private String nameCounselor;
	private String nameMember;
	private Date startTimeCounseling;
	private Date endTimeCounseling;
	private String timeCounseling;
	private String typePayment;
	private Float points;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getNameCounselor() {
		return nameCounselor;
	}

	public void setNameCounselor(String nameCounselor) {
		this.nameCounselor = nameCounselor;
	}

	public String getNameMember() {
		return nameMember;
	}

	public void setNameMember(String nameMember) {
		this.nameMember = nameMember;
	}

	public Date getStartTimeCounseling() {
		return startTimeCounseling;
	}

	public void setStartTimeCounseling(Date startTimeCounseling) {
		this.startTimeCounseling = startTimeCounseling;
	}

	public Date getEndTimeCounseling() {
		return endTimeCounseling;
	}

	public void setEndTimeCounseling(Date endTimeCounseling) {
		this.endTimeCounseling = endTimeCounseling;
	}

	public String getTimeCounseling() {
		return timeCounseling;
	}

	public void setTimeCounseling(String timeCounseling) {
		this.timeCounseling = timeCounseling;
	}

	public String getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(String typePayment) {
		this.typePayment = typePayment;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

}
