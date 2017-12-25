/*
 * Created on 23 thg 11 2016 ( Time 13:38:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.UserRole;
import org.trams.hello.bean.jpa.UserRoleEntity;

/**
 * Business Service Interface for entity UserRole.
 */
public interface UserRoleService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	UserRole findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<UserRole> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserRoleEntity> findAll(Integer page);

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
	UserRole save(UserRole entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserRole update(UserRole entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserRole create(UserRole entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserRoleEntity> listPaging(Integer page,Integer size);
	


}