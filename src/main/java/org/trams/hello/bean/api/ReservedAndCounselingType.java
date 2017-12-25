package org.trams.hello.bean.api;

public class ReservedAndCounselingType {
	private Float typeCounseling;
	private Float toDate;

	public Float getTypeCounseling() {
		return typeCounseling;
	}

	public void setTypeCounseling(Float typeCounseling) {
		this.typeCounseling = typeCounseling;
	}

	public Float getToDate() {
		return toDate;
	}

	public void setToDate(Float toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "ReservedAndCounselingType [typeCounseling=" + typeCounseling + ", toDate=" + toDate + "]";
	}

	public ReservedAndCounselingType(Float typeCounseling, Float toDate) {
		super();
		this.typeCounseling = typeCounseling;
		this.toDate = toDate;
	}

	public ReservedAndCounselingType() {
		super();
	}

}
