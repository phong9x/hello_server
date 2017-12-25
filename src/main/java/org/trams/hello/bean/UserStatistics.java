package org.trams.hello.bean;

public class UserStatistics {
	private Integer id;
	
	private String title;
	
	private Integer totalRegister;
	
	private Integer totalJoinNormal;
	
	private Integer totalJoinBusiness;
	
	private Integer totalWithdrawlNormal;
	
	private Integer totalWithdrawlBusiness;
	
	public UserStatistics(){
		this.totalRegister = 0;
		this.totalJoinNormal = 0;
		this.totalJoinBusiness = 0;
		this.totalWithdrawlNormal = 0;
		this.totalWithdrawlBusiness = 0;
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

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTotalRegister() {
		return totalRegister;
	}

	public void setTotalRegister(Integer totalRegister) {
		this.totalRegister = totalRegister;
	}

	public Integer getTotalJoinNormal() {
		return totalJoinNormal;
	}

	public void setTotalJoinNormal(Integer totalJoinNormal) {
		this.totalJoinNormal = totalJoinNormal;
	}

	public Integer getTotalJoinBusiness() {
		return totalJoinBusiness;
	}

	public void setTotalJoinBusiness(Integer totalJoinBusiness) {
		this.totalJoinBusiness = totalJoinBusiness;
	}

	public Integer getTotalWithdrawlNormal() {
		return totalWithdrawlNormal;
	}

	public void setTotalWithdrawlNormal(Integer totalWithdrawlNormal) {
		this.totalWithdrawlNormal = totalWithdrawlNormal;
	}

	public Integer getTotalWithdrawlBusiness() {
		return totalWithdrawlBusiness;
	}

	public void setTotalWithdrawlBusiness(Integer totalWithdrawlBusiness) {
		this.totalWithdrawlBusiness = totalWithdrawlBusiness;
	}
	
	
}
