package org.trams.hello.bean;

public class CounselorStatistics {
	private Integer id;
	
	private String title;
	
	private Integer totalRegister;
	
	private Integer totalRequestFreelacer;
	
	private Integer totalRequestCenter;
	
	private Integer totalAcceptFreelacer;
	
	private Integer totalAcceptCenter;
	
	private Integer totalWithdrawlFreelacer;
	
	private Integer totalWithdrawlCenter;
	
	public CounselorStatistics(){
		this.totalRequestFreelacer = 0;
		this.totalRequestCenter = 0;
		
		this.totalAcceptFreelacer = 0;
		this.totalAcceptCenter = 0;
		
		this.totalWithdrawlFreelacer = 0;
		this.totalWithdrawlCenter = 0;
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

	public Integer getTotalRequestFreelacer() {
		return totalRequestFreelacer;
	}

	public void setTotalRequestFreelacer(Integer totalRequestFreelacer) {
		this.totalRequestFreelacer = totalRequestFreelacer;
	}

	public Integer getTotalRequestCenter() {
		return totalRequestCenter;
	}

	public void setTotalRequestCenter(Integer totalRequestCenter) {
		this.totalRequestCenter = totalRequestCenter;
	}

	public Integer getTotalAcceptFreelacer() {
		return totalAcceptFreelacer;
	}

	public void setTotalAcceptFreelacer(Integer totalAcceptFreelacer) {
		this.totalAcceptFreelacer = totalAcceptFreelacer;
	}

	public Integer getTotalAcceptCenter() {
		return totalAcceptCenter;
	}

	public void setTotalAcceptCenter(Integer totalAcceptCenter) {
		this.totalAcceptCenter = totalAcceptCenter;
	}

	public Integer getTotalWithdrawlFreelacer() {
		return totalWithdrawlFreelacer;
	}

	public void setTotalWithdrawlFreelacer(Integer totalWithdrawlFreelacer) {
		this.totalWithdrawlFreelacer = totalWithdrawlFreelacer;
	}

	public Integer getTotalWithdrawlCenter() {
		return totalWithdrawlCenter;
	}

	public void setTotalWithdrawlCenter(Integer totalWithdrawlCenter) {
		this.totalWithdrawlCenter = totalWithdrawlCenter;
	}
	

}
