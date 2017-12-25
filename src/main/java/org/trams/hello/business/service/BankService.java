/*
 * Created on 14 Apr 2017 ( Time 09:58:32 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.Bank;
import org.trams.hello.bean.jpa.BankEntity;

/**
 * Business Service Interface for entity Bank.
 */
public interface BankService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Bank findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<Bank> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<BankEntity> findAll(Integer page);

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
	Bank save(Bank entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Bank update(Bank entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Bank create(Bank entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<BankEntity> listPaging(Integer page,Integer size);
	
	BankEntity findOneById(Integer id);

}
