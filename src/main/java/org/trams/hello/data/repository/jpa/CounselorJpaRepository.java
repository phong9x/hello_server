package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorEntity;

/**
 * Repository : Counselor.
 */
public interface CounselorJpaRepository
		extends JpaSpecificationExecutor<CounselorEntity>, PagingAndSortingRepository<CounselorEntity, Integer> {
	@Query("Select u From CounselorEntity u Where u.user.isDelete = 0 and u.status = ?1 and u.user.statusActive = 0")
	Page<CounselorEntity> listPagingByStatus(Short status, Pageable pageable);
	
	@Query("Select u From CounselorEntity u Where u.user.isDelete = 0 and u.status != 0 and u.user.statusActive !=3")
	Page<CounselorEntity> listPagingCounselorNormal(Pageable pageable);
	
	@Query("Select u From CounselorEntity u Where u.user.isDelete = 0 and u.status in ?1 and u.actived = ?2 and u.user.statusActive = 0")
	Page<CounselorEntity> listPagingByStatusAndActived(Short[] status, Short actived, Pageable pageable);

	@Query("Select u From CounselorEntity u Where u.user.isDelete = 0 and u.status = 0 and u.user.statusActive = 0")
	Page<CounselorEntity> listPagingCounselorNotActive(Pageable pageable);

	@Query("Select u From CounselorEntity u Where u.user.isDelete = 0  and (u.user.fullname like ?1 or u.introduce like ?1) and u.status in ?2 and u.actived = ?3 and u.user.statusActive = 0")
	Page<CounselorEntity> listPagingBy_NickNameOrIntroduceAndStatus(String key, Short[] status, Short actived, Pageable pageable);

	@Modifying
	@Query(value = "UPDATE payment_profit SET voucher = 0, coin = 0, profit = 0, admin_refund = 0 WHERE month = ?1 and year = ?2", nativeQuery = true)
	void updatePaymentProfit(Integer month, Integer year);
	
	@Modifying
	@Query(value = "UPDATE counselor SET is_delete = 1 WHERE id = ?1", nativeQuery = true)
	void update_isDelete(Integer id);
	
	@Query(
			"SELECT DISTINCT C "
			+ "FROM CounselorEntity C LEFT JOIN C.listOfCounselorField CF "
			+ "WHERE CF.compositePrimaryKey.counselorFieldId IN ?1 "
			+ "AND C.actived = ?2 AND C.status IN ?3 "
			+ "AND C.user.statusActive = 0 "
			+ "AND C.user.isDelete = 0 "
			)
	Page<CounselorEntity> listPagingBy_CounlorFieldAndStatus(Integer[] counselor_field, Short actived, Short[] status, Pageable pageable);
	
	@Query(
			"SELECT DISTINCT C "
			+ "FROM CounselorEntity C LEFT JOIN C.listOfCounselorField CF "
			+ "WHERE CF.compositePrimaryKey.counselorFieldId IN ?1 "
			+ "AND (C.user.fullname like ?2 or C.introduce like ?2)"
			+ "AND C.actived = ?3 AND C.status in ?4 "
			+ "AND C.user.statusActive = 0 "
			+ "AND C.user.isDelete = 0 "
			
			)
	Page<CounselorEntity> listPagingBy_CounlorFieldAndNickNameAndIntroduceAndStatus(Integer[] counselor_field, String key, Short actived, Short[] status, Pageable pageable);
	
	@Query(
			value="select"
					+ " (select count(*) from counselor_schedule_setting c inner join counselor co on co.id = c.counselor_id  where  c.reservation_time like ?1 and c.reservation_time like ?2 and co.status in ?3 and Date(c.reservation_date) = Date(now())) as count1, "
					+ " (select count(*) from counselor c inner join user u on u.id = c.id where c.status in ?3 and u.status_active = 0) as count2,"
					+ " (select count(*) from counselor_schedule_setting c inner join counselor co on co.id = c.counselor_id where  c.online_time like ?1 and c.online_time like ?2 and co.status in ?3 and co.counsel_online = 1 and Date(c.reservation_date) = Date(now())) as count3",nativeQuery=true
			)
	List<Object[]> toalCounselorHome(String hour1, String hour2, Short[] status);
	
	@Query(value = "SELECT cf1.id, cf1.thumbnail_url,u.fullname, demo.Ggg "
			+ " FROM "
			+ " (SELECT cf.counselor_id, CONCAT(',',(SELECT GROUP_CONCAT(f.counselor_field_id) FROM counselor_field f WHERE f.counselor_id = cf.counselor_id),',') AS Ggg FROM counselor_field cf) AS demo, counselor cf1 "
			+ " INNER JOIN `user` u ON cf1.id = u.id "
			+ " WHERE cf1.id = demo.counselor_id and cf1.actived= ?2 "
			+ " GROUP BY cf1.id "
			+ " HAVING demo.Ggg LIKE ?1 ", nativeQuery = true)
	List<Object[]> counselorsByCategorySelected(String categoryIds, Short active);
	
	@Query(value = "select count(*) from  counseling_session c  where c.status in (2,3) and c.counselor_id = ?1 ", nativeQuery = true)
	Integer totalCounlingByCounselorId(Integer counselorId);
	
	@Query("Select u From CounselorEntity u where u.status = 1 and u.user.statusActive = 0 and u.user.isDelete = 0 ")
	Page<CounselorEntity> listSuggetPagingBy_CounlorFieldAndStatus(Pageable pageable);
	
	@Query(value = "SELECT IF(c.reservation_time IS NULL OR c.reservation_time='', 0, 1) FROM counselor_schedule_setting c WHERE c.counselor_id = ?1 and DATE(c.reservation_date) = DATE(?2) ", nativeQuery = true)
	Integer isReservation(Integer counselorId, Date tomorrowDate);
}
