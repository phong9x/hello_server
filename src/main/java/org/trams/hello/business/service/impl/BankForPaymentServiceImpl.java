/*
 * Created on 5 Apr 2017 ( Time 11:50:21 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.BankForPayment;
import org.trams.hello.bean.jpa.BankForPaymentEntity;
import org.trams.hello.business.service.BankForPaymentService;
import org.trams.hello.business.service.mapping.BankForPaymentServiceMapper;
import org.trams.hello.data.repository.jpa.BankForPaymentJpaRepository;
/**
 * Implementation of BankForPaymentService
 */
@Component
@Transactional
public class BankForPaymentServiceImpl implements BankForPaymentService {

	@Resource
	private BankForPaymentJpaRepository bankForPaymentJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private BankForPaymentServiceMapper bankForPaymentServiceMapper;
	
	@Override
	public BankForPayment findById(Integer id) {
		BankForPaymentEntity bankForPaymentEntity = bankForPaymentJpaRepository.findOne(id);
		return bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntity);
	}

	@Override
	public Page<BankForPaymentEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return bankForPaymentJpaRepository.findAll(request);
	}

	@Override
	public List<BankForPayment> findAll() {
		Iterable<BankForPaymentEntity> entities = bankForPaymentJpaRepository.findAll();
		List<BankForPayment> beans = new ArrayList<BankForPayment>();
		for(BankForPaymentEntity bankForPaymentEntity : entities) {
			beans.add(bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = bankForPaymentJpaRepository.count();
		return count;
	}

	@Override
	public BankForPayment save(BankForPayment bankForPayment) {
		return update(bankForPayment) ;
	}

	@Override
	public BankForPayment create(BankForPayment bankForPayment) {
/*
		BankForPaymentEntity bankForPaymentEntity = bankForPaymentJpaRepository.findOne(bankForPayment.getId());
		if( bankForPaymentEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		bankForPaymentEntity = new BankForPaymentEntity();
		bankForPaymentServiceMapper.mapBankForPaymentToBankForPaymentEntity(bankForPayment, bankForPaymentEntity);
		BankForPaymentEntity bankForPaymentEntitySaved = bankForPaymentJpaRepository.save(bankForPaymentEntity);
		return bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntitySaved);
*/
		BankForPaymentEntity bankForPaymentEntity = new BankForPaymentEntity();
		bankForPaymentServiceMapper.mapBankForPaymentToBankForPaymentEntity(bankForPayment, bankForPaymentEntity);
		BankForPaymentEntity bankForPaymentEntitySaved = bankForPaymentJpaRepository.save(bankForPaymentEntity);
		return bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntitySaved);
	}

	@Override
	public BankForPayment update(BankForPayment bankForPayment) {
		BankForPaymentEntity bankForPaymentEntity = bankForPaymentJpaRepository.findOne(bankForPayment.getId());
		bankForPaymentServiceMapper.mapBankForPaymentToBankForPaymentEntity(bankForPayment, bankForPaymentEntity);
		BankForPaymentEntity bankForPaymentEntitySaved = bankForPaymentJpaRepository.save(bankForPaymentEntity);
		return bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		bankForPaymentJpaRepository.delete(id);
	}

	public BankForPaymentJpaRepository getBankForPaymentJpaRepository() {
		return bankForPaymentJpaRepository;
	}

	public void setBankForPaymentJpaRepository(BankForPaymentJpaRepository bankForPaymentJpaRepository) {
		this.bankForPaymentJpaRepository = bankForPaymentJpaRepository;
	}

	public BankForPaymentServiceMapper getBankForPaymentServiceMapper() {
		return bankForPaymentServiceMapper;
	}

	public void setBankForPaymentServiceMapper(BankForPaymentServiceMapper bankForPaymentServiceMapper) {
		this.bankForPaymentServiceMapper = bankForPaymentServiceMapper;
	}

	@Override
	public Page<BankForPaymentEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return bankForPaymentJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BankForPayment findByBankCode(String bankCode) {
		try {
			BankForPaymentEntity bankForPaymentEntity = bankForPaymentJpaRepository.findByBankCode(bankCode);
			return bankForPaymentServiceMapper.mapBankForPaymentEntityToBankForPayment(bankForPaymentEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	


}
