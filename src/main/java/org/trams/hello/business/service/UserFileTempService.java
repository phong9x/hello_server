/*
 * Created on 29 Apr 2017 ( Time 10:20:18 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.UserFileTemp;
import org.trams.hello.bean.jpa.UserFileTempEntity;

/**
 * Business Service Interface for entity UserFileTemp.
 */
public interface UserFileTempService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	UserFileTemp findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<UserFileTemp> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserFileTempEntity> findAll(Integer page);

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
	UserFileTemp save(UserFileTemp entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserFileTemp update(UserFileTemp entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserFileTemp create(UserFileTemp entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserFileTempEntity> listPaging(Integer page,Integer size);
	
	Page<UserFileTempEntity> listPagingByUserId( Integer page, Integer size, Integer userId);



}
