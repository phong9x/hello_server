package org.trams.hello.bean.web.company;

import java.util.ArrayList;
import java.util.List;

public class VoucherFormBusinessData {
	private List<VoucherFormBusiness> voucherForms;

	public VoucherFormBusinessData() {
		voucherForms = new ArrayList<VoucherFormBusiness>();
	}
	
	public List<VoucherFormBusiness> getFormData(List<VoucherInfo> list){
		for (VoucherInfo v : list) {
			VoucherFormBusiness songForm = makeSongBusinessForm(v);
			voucherForms.add(songForm);
		}
		return voucherForms;
	}

	private VoucherFormBusiness makeSongBusinessForm(VoucherInfo voucherInfo){
		VoucherFormBusiness voucherForms = new VoucherFormBusiness();
		voucherForms.setUserId(voucherInfo.getId());
		voucherForms.setFullname(voucherInfo.getFullname());
		voucherForms.setDayOfBirth(voucherInfo.getDayOfBirth());
		voucherForms.setManageYear(voucherInfo.getManageYear());
		voucherForms.setBusinessSubName(voucherInfo.getBusinessSubName());
		voucherForms.setUseVoucherDate(voucherInfo.getUseVoucherDate());
		voucherForms.setCountVoucherUsed(voucherInfo.getCountVoucherUsed());
		voucherForms.setFee(voucherInfo.getFee());
		
		return voucherForms;
	}
}
