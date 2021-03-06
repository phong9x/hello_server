/*
 * Created on 3 Mar 2017 ( Time 10:03:51 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.UserNotice;
import org.trams.hello.bean.api.Announcement;
import org.trams.hello.bean.api.UserNotices;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.UserNoticeEntity;

/**
 * Business Service Interface for entity UserNotice.
 */
public interface UserNoticeService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	UserNotice findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<UserNotice> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<UserNoticeEntity> findAll(Integer page);

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
	UserNotice save(UserNotice entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserNotice update(UserNotice entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	UserNotice create(UserNotice entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<UserNoticeEntity> listPaging(Integer page,Integer size);
	
	Page<UserNoticeEntity> listPagingByUserId( Integer page, Integer size, Integer userId);
	
	Page<UserNoticeEntity> listPagingByCounselorId( Integer page, Integer size, Integer counselorId, String orderBy);

	List<Announcement> listAnnouncement(Integer userId);

	Announcement getAnnouncementByUserNoticeId(Integer uNoticeId);
	
	List<UserNotices> notices(Integer userId);
	
	List<UserNotices> noticesByStatus(UserEntity user, Short type);
	
	UserNotices getUserNoticeDetail(Integer userId, Integer uNoticeId, UserNoticeService userNoticeService);
	
	UserNotices getUserNoticeDetailNotUser(Integer uNoticeId, UserNoticeService userNoticeService);
	
	List<UserNotices> noticesInApp(UserEntity user, Short type, String osType);
	
	List<UserNotices> noticesInAppNotUser(Short type, String osType);
	
	UserNotice findByNoticeIdAndUserId(Integer noticeId, Integer userId);
	
	
}
