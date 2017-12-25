/*
 * Created on 23 thg 11 2016 ( Time 16:49:52 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.BusinessVoucher;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.jpa.VoucherEntity;

/**
 * Business Service Interface for entity Voucher.
 */
public interface VoucherService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Voucher findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Voucher> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<VoucherEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Voucher save(Voucher entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Voucher update(Voucher entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Voucher create(Voucher entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<VoucherEntity> listPaging(Integer page,Integer size);
	
	Page<VoucherEntity> findByName(String name, Integer page, Integer size);

	VoucherEntity findOneByBusinessId(Integer businessId);
	
	Voucher findOneByBusinessIdAndCounselingTypeTime(Integer businessSubId, Short counselingTimeType);
	
	Voucher findByTypeHeart(Short type);

	Voucher findByBusinessSubId(Integer businessSubId);
	
	List<VoucherEntity> listByBusinessId(Integer businessId);
	
	List<BusinessVoucher> listBussinessVoucherByUserId(Integer userId);
	
	List<Voucher> getAvailableVouchersByBusinessSubId(Integer businessSubId);
	
	List<Voucher> getByBusinessSubId(Integer businessSubId);
}
