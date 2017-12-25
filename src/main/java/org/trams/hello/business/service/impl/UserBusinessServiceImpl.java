package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.BusinessSub;
import org.trams.hello.bean.CompanyUserVoucherHistory;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserBusiness;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.VoucherUser;
import org.trams.hello.bean.jpa.UserBusinessEntity;
import org.trams.hello.business.service.BusinessSubService;
import org.trams.hello.business.service.CompanyUserVoucherHistoryService;
import org.trams.hello.business.service.UserBusinessService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.VoucherUserService;
import org.trams.hello.business.service.mapping.UserBusinessServiceMapper;
import org.trams.hello.data.repository.jpa.UserBusinessJpaRepository;
import org.trams.hello.rest.common.Utils;
import org.trams.hello.web.common.ApplicationDefine;

/**
 * Implementation of UserBusinessService
 */
@Component
@Transactional
public class UserBusinessServiceImpl implements UserBusinessService {

	@Resource
	private UserBusinessJpaRepository userBusinessJpaRepository;

	@Resource
	private UserBusinessServiceMapper userBusinessServiceMapper;
	
	@Resource 
	private BusinessSubService businessSubService;
	
	@Resource 
	private VoucherService voucherService;
	
	@Resource 
	private VoucherUserService voucherUserService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CompanyUserVoucherHistoryService companyUserVoucherHistoryService;
	
	@Override
	public UserBusiness findById(Integer id) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(id);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntity);
	}

	@Override
	public List<UserBusiness> findAll() {
		Iterable<UserBusinessEntity> entities = userBusinessJpaRepository.findAll();
		List<UserBusiness> beans = new ArrayList<UserBusiness>();
		for(UserBusinessEntity userBusinessEntity : entities) {
			beans.add(userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntity));
		}
		return beans;
	}

	@Override
	public UserBusiness save(UserBusiness userBusiness) {
		return update(userBusiness) ;
	}

	@Override
	public UserBusiness create(UserBusiness userBusiness) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(userBusiness.getId());
		if( userBusinessEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		userBusinessEntity = new UserBusinessEntity();
		userBusinessServiceMapper.mapUserBusinessToUserBusinessEntity(userBusiness, userBusinessEntity);
		UserBusinessEntity userBusinessEntitySaved = userBusinessJpaRepository.save(userBusinessEntity);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntitySaved);
	}

	@Override
	public UserBusiness update(UserBusiness userBusiness) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findOne(userBusiness.getId());
		userBusinessServiceMapper.mapUserBusinessToUserBusinessEntity(userBusiness, userBusinessEntity);
		UserBusinessEntity userBusinessEntitySaved = userBusinessJpaRepository.save(userBusinessEntity);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userBusinessJpaRepository.delete(id);
	}

	public UserBusinessJpaRepository getUserBusinessJpaRepository() {
		return userBusinessJpaRepository;
	}

	public void setUserBusinessJpaRepository(UserBusinessJpaRepository userBusinessJpaRepository) {
		this.userBusinessJpaRepository = userBusinessJpaRepository;
	}

	public UserBusinessServiceMapper getUserBusinessServiceMapper() {
		return userBusinessServiceMapper;
	}

	public void setUserBusinessServiceMapper(UserBusinessServiceMapper userBusinessServiceMapper) {
		this.userBusinessServiceMapper = userBusinessServiceMapper;
	}

	@Override
	public List<UserBusinessEntity> findByUserIdAndIsDeleted(Integer userId, Boolean isDeleted) {
		return userBusinessJpaRepository.findByUserIdAndIsDeleted(userId, isDeleted);
	}

	@Override
	public Page<UserBusinessEntity> findByBusinessSubIdAndIsDeleted(Integer businessSubId, boolean isDeleted,
			Integer page, Integer size) {
		PageRequest request = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "id")));
		return userBusinessJpaRepository.findByBusinessSubIdAndIsDeleted(businessSubId, isDeleted, request);
	}

	@Override
	public UserBusiness findByUserIdAndBusinessSubId(Integer userId, Integer businessSubId) {
		UserBusinessEntity userBusinessEntity = userBusinessJpaRepository.findByUserIdAndBusinessSubId(userId, businessSubId);
		return userBusinessServiceMapper.mapUserBusinessEntityToUserBusiness(userBusinessEntity);
	}
	
	@Override
	public void checkBusinessMember(User user) {
		Map<String,String> response = Utils.checkMemberBusiness(user);
		if(response != null){
			String businessSubCode = response.get("businessSubCode");
			String aimmedUserId = response.get("aimmedUserId");
			if(businessSubCode != null && aimmedUserId != null){
				BusinessSub businessSub = businessSubService.findByBusinessSubNo(businessSubCode);
				if (businessSub != null) {
					if(user.getRoleId().equals(ApplicationDefine.USER_ROLE_MEMBER_BUSSINESS)){
						if(!user.getBusinessSubId().equals(businessSub.getId())){
							updateUserBusiness(user, businessSub.getId(), aimmedUserId);
						}
					}else if(user.getRoleId().equals(ApplicationDefine.USER_ROLE_USER)){
						createUserBusiness(user, businessSub.getId(), aimmedUserId);
					}
				}else{
					deleteUserBusiness(user, user.getBusinessSubId());
				}	
			}
		}
	}
	
	private void createUserBusiness(User user, Integer businessSubId, String aimmedUserId){
		UserBusiness ub = new UserBusiness();
		ub.setId(0);
		ub.setBusinessSubId(businessSubId);
		ub.setCreateDate(new Date());
		ub.setIsDeleted(false);
		ub.setUpdateDate(new Date());
		ub.setUserId(user.getId());
		ub = create(ub);
		
		user.setRoleId(ApplicationDefine.USER_ROLE_MEMBER_BUSSINESS);
		user.setBusinessSubId(businessSubId);
		userService.update(user);
		
		if(ub != null){
			if(!hasCompanyVoucher(businessSubId, aimmedUserId)){
				createVoucher(businessSubId, user.getId(), aimmedUserId);
			}
		}
	}
	
	private void deleteUserBusiness(User user, Integer businessSubId){
		user.setBusinessSubId(null);
		user.setRoleId(ApplicationDefine.USER_ROLE_USER);
		userService.update(user);
		if(businessSubId != null){
			UserBusiness userBusiness = findByUserIdAndBusinessSubId(user.getId(), businessSubId);
			
			if(userBusiness != null){
				delete(userBusiness.getId());
				deleteVoucher(userBusiness.getId(), user.getId());
			}
		}
	}
	
	private void updateUserBusiness(User user, Integer businessSubId, String aimmedUserId){
		Integer previousBusinessSubId = user.getBusinessSubId();
		Integer userId = user.getId();
		UserBusiness userBusiness = findByUserIdAndBusinessSubId(userId, previousBusinessSubId);
		userBusiness.setBusinessSubId(businessSubId);
		update(userBusiness);
		
		user.setBusinessSubId(businessSubId);
		userService.update(user);
		
		deleteVoucher(previousBusinessSubId, userId);
		if(!hasCompanyVoucher(businessSubId, aimmedUserId)){
			createVoucher(businessSubId, userId, aimmedUserId);
		}
	}
	
	private void createVoucher(Integer businessSubId, Integer userId, String aimmedUserId){
		List<Voucher> vouchers = voucherService.getAvailableVouchersByBusinessSubId(businessSubId);
		if(vouchers != null){
			for(Voucher voucher : vouchers){
				VoucherUser voucherUser = new VoucherUser();
				voucherUser.setId(0);
				voucherUser.setUserId(userId);
				voucherUser.setVoucherId(voucher.getId());
				Date fromDate = null;
				Date toDate = null;
				if(voucher.getTypeTime().equals((short) 1)){
					fromDate = voucher.getFromTime();
					toDate = voucher.getToTime();
				}else{
					Calendar calander = Calendar.getInstance();
					fromDate = calander.getTime();
					calander.add(Calendar.DATE, voucher.getDateNumber());
					toDate = calander.getTime();
				}
				voucherUser.setFromDate(fromDate);
				voucherUser.setToDate(toDate);
				voucherUser.setStatusUse((short) 0);
				voucherUser.setCreateDate(new Date());
				voucherUser.setUpdateDate(new Date());
				voucherUserService.create(voucherUser);
				
				voucher.setUsed(voucher.getUsed() + 1);
				voucherService.update(voucher);
			}
			createVoucherHistory(businessSubId, userId, aimmedUserId);
		}
	}
	
	private void deleteVoucher(Integer businessSubId, Integer userId){
		List<Voucher> vouchers = voucherService.getByBusinessSubId(businessSubId);
		if(vouchers != null){
			for(Voucher voucher : vouchers){
				VoucherUser voucherUser = voucherUserService.findByUserIdAndVoucherIdAndStatusUse(userId, voucher.getId(), (short) 0);
				if(voucherUser != null){
					voucherUserService.delete(voucherUser.getId());
					voucher.setUsed(voucher.getUsed() - 1);
					voucherService.update(voucher);
				}
			}
		}
	}
	
	private boolean hasCompanyVoucher(Integer businessSubId, String aimmedUserId){
		boolean result = false;
		Long historyCount = companyUserVoucherHistoryService.countByBusinessSubIdAndAimmedUserId(businessSubId, aimmedUserId);
		if(historyCount.longValue() > 0){
			result = true;
		}
		return result;
	}
	
	
	private void createVoucherHistory(Integer businessSubId, Integer userId, String aimmedUserId){
		CompanyUserVoucherHistory entity = new CompanyUserVoucherHistory(); 
		entity.setId(0);
		entity.setBusinessSubId(businessSubId);
		entity.setUserId(userId);
		entity.setAimmedUserId(aimmedUserId);
		entity.setCreateDate(new Date());
		companyUserVoucherHistoryService.create(entity);
	}
}
