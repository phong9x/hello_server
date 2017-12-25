package org.trams.hello.bean.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.trams.hello.bean.PaymentProfitData;

public class CounselorProfitProcess {
	private List<PaymentProfitData> profit = new ArrayList<>();
	private MultipartFile excel;

	

	public List<PaymentProfitData> getProfit() {
		return profit;
	}

	public void setProfit(List<PaymentProfitData> profit) {
		this.profit = profit;
	}

	public MultipartFile getExcel() {
		return excel;
	}

	public void setExcel(MultipartFile excel) {
		this.excel = excel;
	}

}
