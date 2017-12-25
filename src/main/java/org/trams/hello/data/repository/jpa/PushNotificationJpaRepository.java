package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PushNotificationEntity;
/**
 * Repository : PushNotificatin.
 */
public interface PushNotificationJpaRepository extends PagingAndSortingRepository<PushNotificationEntity, Integer>,
		JpaSpecificationExecutor<PushNotificationEntity> {
	@Query(
			"Select u From PushNotificationEntity u "
			)
	Page<PushNotificationEntity> listPaging(Pageable pageable);


	@Query(
			"Select u From PushNotificationEntity u Where u.title like ?1 "
			)
	Page<PushNotificationEntity> findByTitle(String title, Pageable pageable);
	
	@Query(
			"SELECT u "
			+ "FROM PushNotificationEntity u "
			+ "WHERE DATE_FORMAT(u.startPushDate, '%Y-%m-%d %H:%i:00') =?1 "
			+ "AND u.status = ?2"
			)
	List<PushNotificationEntity> findByDateAndStatus(String date, short status);
	
	@Modifying
	@Query(
			value= "update push_notification set status = ?1 where id in ?2", nativeQuery =true
			)
	void updateStatusByIds(Short status, Integer[] id);
	
	@Query(
			"Select u From PushNotificationEntity u Where u.title like ?1 "
			)
	List<PushNotificationEntity> findByMinutes(Date start_push_date);
}
