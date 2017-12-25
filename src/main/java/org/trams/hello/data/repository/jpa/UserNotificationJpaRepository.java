package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserNotificationEntity;

/**
 * Repository : Answer.
 */
public interface UserNotificationJpaRepository extends PagingAndSortingRepository<UserNotificationEntity, Integer>,
JpaSpecificationExecutor<UserNotificationEntity> {
	@Query("Select u From UserNotificationEntity u ")
	Page<UserNotificationEntity> listPaging(Pageable pageable);

	@Query("Select u From UserNotificationEntity u Where u.user.id = ?1 ")
	Page<UserNotificationEntity> listPagingByUserId(Integer userId, Pageable pageable);

	@Query("Select u From UserNotificationEntity u Where u.user.id = ?1 and u.notification.id = ?2 ")
	UserNotificationEntity findByUserIdAndNotifiId(Integer userId, Integer notifiId);
	
	@Modifying
	@Query(value="DELETE FROM user_notification  WHERE user_id = ?2 and notification_id = ?1 ", nativeQuery=true)
	void deleteByNotificationId(Integer notificationId, Integer userId);
	
	@Modifying
	@Query(value="delete from user_notification where user_id = ?1 and notification_id in (select id from notification n where n.type_push = 2 and n.information like ?2) ", nativeQuery=true)
	void deleteByUserIdAndUserReceiveMessId(Integer userId, String infomation);
}
