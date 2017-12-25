package org.trams.hello.bean.web.admin;

public class CounselorSortPaper {
	private String direction;
	private String property;
	private boolean ignoreCase;
	private String nullHandling;
	private boolean ascending;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public String getNullHandling() {
		return nullHandling;
	}

	public void setNullHandling(String nullHandling) {
		this.nullHandling = nullHandling;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public CounselorSortPaper(String direction, String property, boolean ignoreCase, String nullHandling,
			boolean ascending) {
		super();
		this.direction = direction;
		this.property = property;
		this.ignoreCase = ignoreCase;
		this.nullHandling = nullHandling;
		this.ascending = ascending;
	}
	

}
