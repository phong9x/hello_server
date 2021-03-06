/*
 * Created on 22 May 2017 ( Time 09:42:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.PushNotification;
import org.trams.hello.bean.jpa.PushNotificationEntity;

/**
 * Business Service Interface for entity PushNotification.
 */
public interface PushNotificationService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	PushNotification findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<PushNotification> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<PushNotificationEntity> findAll(Integer page);

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
	PushNotification save(PushNotification entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	PushNotification update(PushNotification entity);
	
	PushNotification update(PushNotificationEntity pushNotificationEntity);
	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	PushNotification create(PushNotification entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<PushNotificationEntity> listPaging(Integer page,Integer size);
	
	Page<PushNotificationEntity> findByTitle(String title, Integer page, Integer size);

	Page<PushNotificationEntity> filterAdmin(String startDate, String endDate, String type, String key, Integer page, Integer size);

	void updateStatusByIds(Short status, Integer[] id);
	
	List<PushNotificationEntity> findByDateAndStatus(String date, short status);
	
}
