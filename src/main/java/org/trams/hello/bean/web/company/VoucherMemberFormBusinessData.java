package org.trams.hello.bean.web.company;

import java.util.ArrayList;
import java.util.List;

public class VoucherMemberFormBusinessData {
	private List<VoucherMemberFormBusiness> voucherMemberForms;

	public VoucherMemberFormBusinessData() {
		voucherMemberForms = new ArrayList<VoucherMemberFormBusiness>();
	}
	
	public List<VoucherMemberFormBusiness> getFormData(List<VoucherInfo> list){
		for (VoucherInfo v : list) {
			VoucherMemberFormBusiness form = makeVoucherMemberBusinessForm(v);
			voucherMemberForms.add(form);
		}
		return voucherMemberForms;
	}

	private VoucherMemberFormBusiness makeVoucherMemberBusinessForm(VoucherInfo voucherInfo){
		VoucherMemberFormBusiness voucherMemberForms = new VoucherMemberFormBusiness();
		voucherMemberForms.setUserId(voucherInfo.getId());
		voucherMemberForms.setFullname(voucherInfo.getFullname());
		voucherMemberForms.setDayOfBirth(voucherInfo.getDayOfBirth());
		voucherMemberForms.setManageYear(voucherInfo.getManageYear());
		voucherMemberForms.setBusinessSubName(voucherInfo.getBusinessSubName());
		voucherMemberForms.setUseVoucherDate(voucherInfo.getUseVoucherDate());
		voucherMemberForms.setCountVoucherUsed(voucherInfo.getCountVoucherUsed());
		voucherMemberForms.setCountTotalVoucher(voucherInfo.getCountTotalVoucher());
		voucherMemberForms.setCountRemainVoucher(voucherInfo.getCountRemainVoucher());
		voucherMemberForms.setCreateDate(voucherInfo.getCreateDate());
		voucherMemberForms.setFee(voucherInfo.getFee());
		
		return voucherMemberForms;
	}
}
