package org.trams.hello.bean.web.company;

public class StatisticsVoucher {
	private Integer totalVoucher;
	private Integer totalFee;
	private Integer totalVoucherRemain;

	public Integer getTotalVoucherRemain() {
		return totalVoucherRemain;
	}

	public void setTotalVoucherRemain(Integer totalVoucherRemain) {
		this.totalVoucherRemain = totalVoucherRemain;
	}

	public Integer getTotalVoucher() {
		return totalVoucher;
	}

	public void setTotalVoucher(Integer totalVoucher) {
		this.totalVoucher = totalVoucher;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

}
