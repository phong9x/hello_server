package org.trams.hello.bean.web.counselingCenter;

import java.util.Date;

public class CounselorInfo {
	private Integer id;
	private String thumb;
	private String name;
	private Date dayOfBirth;
	private String phone;
	private Date createDate;
	private Date lastLoggedDate;
	private Float points;
	private String status;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastLoggedDate() {
		return lastLoggedDate;
	}

	public void setLastLoggedDate(Date lastLoggedDate) {
		this.lastLoggedDate = lastLoggedDate;
	}

	public Float getPoints() {
		return points;
	}

	public void setPoints(Float points) {
		this.points = points;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
