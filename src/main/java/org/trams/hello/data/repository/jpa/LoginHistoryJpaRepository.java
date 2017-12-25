package org.trams.hello.data.repository.jpa;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.LoginHistoryEntity;
/**
 * Repository : LoginHistory.
 */
public interface LoginHistoryJpaRepository extends PagingAndSortingRepository<LoginHistoryEntity, Long>,
		JpaSpecificationExecutor<LoginHistoryEntity> {
	@Query(
			"Select u From LoginHistoryEntity u "
			)
	Page<LoginHistoryEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From LoginHistoryEntity u Where u.userId = ?1 "
			)
	Page<LoginHistoryEntity> listPagingByUserId( Integer userId, Pageable pageable);
	
	@Query(
			"Select u From LoginHistoryEntity u Where u.deviceId = ?1 and u.userId = ?2 and u.createDate = DATE(?3) "
			)
	LoginHistoryEntity listPagingByDeviceIdAndUserIdAndCreateDate(String deviceId, Integer userId, Date date);

	@Query(
			value = "SELECT COUNT(*) FROM login_history WHERE device_id = ?1 and user_id = ?2 and create_date = DATE(?3) ", nativeQuery = true)
	int countByDeviceIdAndUserIdAndCreateDate(String deviceId, Integer userId, Date date );
}
