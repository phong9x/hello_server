/*
 * Created on 5 Apr 2017 ( Time 11:50:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.BankForPayment;
import org.trams.hello.bean.jpa.BankForPaymentEntity;

/**
 * Business Service Interface for entity BankForPayment.
 */
public interface BankForPaymentService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	BankForPayment findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<BankForPayment> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<BankForPaymentEntity> findAll(Integer page);

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
	BankForPayment save(BankForPayment entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	BankForPayment update(BankForPayment entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	BankForPayment create(BankForPayment entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<BankForPaymentEntity> listPaging(Integer page,Integer size);
	
	BankForPayment findByBankCode(String bankCode);

}
