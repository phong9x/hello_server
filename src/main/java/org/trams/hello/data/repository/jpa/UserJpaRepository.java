package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserEntity;
/**
 * Repository : User.
 */
public interface UserJpaRepository extends JpaSpecificationExecutor<UserEntity>, PagingAndSortingRepository<UserEntity, Integer> {
	@Query(
			"Select u From UserEntity u Where u.isDelete = 0"
			)
	Page<UserEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.businessSub.id = ?1 and u.isDelete = 0"
			)
	Page<UserEntity> listPagingBy_BusinessId(Integer id, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.changeHeartDate > ?1 and u.statusActive !=3 and u.isDelete = 0"
			)
	List<UserEntity> listByChangeHeartDate(Date date);
	
	@Query(
			"Select u From UserEntity u Where Year(u.lastLogin) = ?1 and Month(u.lastLogin) = ?2 and DAY(u.lastLogin) = ?3 and u.isDelete = 0"
			)
	List<UserEntity> listByLastLogin(Integer year, Integer month, Integer date);

	@Modifying
	@Query(
			value="UPDATE user SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and (u.userRole.id = 1 or u.userRole.id = 5 or u.userRole.id =9) and (statusActive = 0 or statusActive = 2)  and u.isDelete = 0"
			)
	UserEntity loginApp(String username);
	
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.password = ?2 and u.userRole.id in ?3 and u.isDelete = 0"
			)
	UserEntity loginAdmin(String username, String password, Integer[] roleId);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.password = ?2 and u.userRole.id in (2,3,4) and u.isDelete = 0"
			)
	UserEntity loginCounselor(String username, String password);

	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.passwordTemp = ?2 and u.userRole.id in (2,3,4) and u.isDelete = 0"
	)
	UserEntity loginCounselorTemp(String username, String passwordTemp);

	@Query(
			"Select u From UserEntity u Where u.username like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByUsername(String username, Pageable pageable);
	@Query(
			"Select u From UserEntity u Where u.nickname like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByNickname(String nickname, Pageable pageable);
	@Query(
			"Select u From UserEntity u Where u.fullname like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByFullname(String fullname, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.email like ?1 and u.isDelete = 0"
			)
	Page<UserEntity> findByEmail(String email, Pageable pageable);
	
	@Query(
			"Select u From UserEntity u Where u.email = ?1 and u.isDelete = 0"
			)
	UserEntity findOneByEmail(String email);
	
	@Query(
			value="Select count(*) from user u where u.username = ?1 and u.is_delete = 0", nativeQuery = true
			)
	Integer findOneByUsername(String username);

	@Query(
			"Select u From UserEntity u Where u.phone = ?1 and u.isDelete = 0"
	)
	UserEntity findOneByPhone(String phone);
	
	@Query(
			"Select u From UserEntity u Where u.username = ?1 and u.isDelete = 0"
	)
	UserEntity findOneBy_Username(String username);
	
	@Query(
			value="select "
					+ "	(select count(*) from user where is_delete =0 and status_active != 3 and role_id in(1,5) and DATE(create_date) <= DATE(?1) ) as count_1,"
					+ " (select count(*) from user u inner join counselor c on u.id = c.id where  u.status_active in(0,1) and c.status in(1,2) and DATE(u.create_date) <= DATE(?1)) as count_2,"
					+ " (select count(*) from business where is_delete =0 and contract_status in(1,3) and DATE(create_date) <= DATE(?1)) as count_3,"
					+ " (select count(*) from counseling_center where is_delete =0 and status in (0,1,3,4)  and DATE(create_date) <= DATE(?1) ) as count_4,"
					+ " (select count(*) from business b inner join user u on b.id =u.id"
					+ " where DATEDIFF(NOW(), b.end_contract_date) < 90 and u.is_delete =0 ) as count_5"
					,nativeQuery=true
			)	
	List<Object[]> totalUserHome(Date date);
	
	@Query(
			value="select (select count(*) from counselor where is_delete =0 and status = 0) as count_1,"
					+ " (select count(*) from counselor_temp where status =0 and status = 0) as count_2,"
					+ " (select count(*) from promotion_page where status =0) as count_3",nativeQuery=true
			)
	List<Object[]> totalRequestAndCounselingHome();
	
	@Query(
			value="SELECT count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.create_date) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.create_date) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.create_date) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.create_date) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.create_date) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.create_date) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.create_date) then e.id end) As Day7 "
					+ "from user e where is_delete = 0 and role_id in (1,5) "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_JoinNumber( Date date);
	
	@Query(
			value="SELECT count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.withdrawal_date) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.withdrawal_date) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.withdrawal_date) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.withdrawal_date) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.withdrawal_date) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.withdrawal_date) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.withdrawal_date) then e.id end) As Day7 "
					+ "from user e where status_active = ?2 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_RemoveAccount( Date date, Short status);
	
	@Query(
			value=  "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 0 DAY)) "
					+ "union all "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 1 DAY)) "
					+ " union all "
					+ " SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 2 DAY)) "
					+ "union all "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 3 DAY)) "
					+ "union all "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 4 DAY)) "
					+ "union all "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 5 DAY)) "
					+ "union all "
					+ "SELECT case when sum(e.login_number) is null then 0 else sum(e.login_number) end from report_daily e where DATE(e.create_date) = DATE(DATE_ADD(?1,INTERVAL 6 DAY)) "
,nativeQuery=true)
	Object[] getDataChart_LoginNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.end_time) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.end_time) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.end_time) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.end_time) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.end_time) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.end_time) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.end_time) then e.id end) As Day7 "
					+ "from counseling_session e where status !=0 and e.counsel_type =2 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_ReservationNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.end_time) then e.id end) As Day1, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.end_time) then e.id end) As Day2, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.end_time) then e.id end) As Day3, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.end_time) then e.id end) As Day4, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.end_time) then e.id end) As Day5, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.end_time) then e.id end) As Day6, "
					+ "count(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.end_time) then e.id end) As Day7 "
					+ "from counseling_session e where status !=0 and e.counsel_type =1 "
					,nativeQuery=true
			)
	List<Object[]> getDataChart_CounselingNumber( Date date);
	
	@Query(
			value="SELECT "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 0 DAY))= DATE(e.create_date) then e.coin end) As Day1, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 1 DAY))= DATE(e.create_date) then e.coin end) As Day2, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 2 DAY))= DATE(e.create_date) then e.coin end) As Day3, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 3 DAY))= DATE(e.create_date) then e.coin end) As Day4, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 4 DAY))= DATE(e.create_date) then e.coin end) As Day5, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 5 DAY))= DATE(e.create_date) then e.coin end) As Day6, "
					+ "sum(case when  DATE(DATE_ADD(?1,INTERVAL 6 DAY))= DATE(e.create_date) then e.coin end) As Day7 "
					+ "from payment_history e inner join counseling_session c on e.id = c.payment_id where e.type_coin = 1 and e.type_payment = 2 and c.status !=0"
					,nativeQuery=true
			)
	List<Object[]> getDataChart_CounselingFee( Date date);
	
	@Query(
			value="select("
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 1 and p.status = 1 and p.type_payment = 1 and p.user_id = ?1) - "
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 1 and p.status = 1 and p.type_payment = 2 and p.user_id = ?1  )"
					+ ") as totalCoin"
					,nativeQuery=true
			)
	Integer getTotalCoin( Integer userId);
	
	@Modifying
	@Query(
			value="update user u set u.heart =("
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 2 and p.status = 1 and p.type_payment = 1  and p.user_id = u.id) - "
					+ "(select case when sum(p.coin) is null then 0 else sum(p.coin) end FROM payment_history p where p.type_coin = 2 and p.status = 1 and p.type_payment = 2  and p.user_id = u.id)"
					+ ") where id = ?1"
					,nativeQuery=true
			)
	void updateHeartByUserId( Integer userId);
	
	@Modifying
	@Query(
			value="update user set voucher_number = (select count(*) from voucher_user where status_use = 0 and user_id = ?1) "
					+ " where id = ?1"
					,nativeQuery=true
			)
	void updateVoucherNumberByUserId( Integer userId);

	UserEntity findByFullnameAndPhone(String fullname, String phone);

	UserEntity findByFullnameAndUsernameAndPhone(String fullname, String username, String phone);
	
	@Query("Select u From UserEntity u Where u.email = ?1 and DATE(u.dayOfBirth) = ?2 and u.isDelete = 0 ")
	UserEntity findOneByEmailAndBirthDay(String email, Date birthDay);
	
	@Query("Select u From UserEntity u Where u.email = ?1 and DATE(u.dayOfBirth) = ?2 and u.phone = ?3 and u.isDelete = 0 ")
	UserEntity findOneByEmailAndBirthDayAndPhone(String email, Date birthDay, String phone);
	
	@Query(value="SELECT * FROM user u INNER JOIN counselor c ON u.id = c.id WHERE u.role_id IN(2,3) and u.fullname LIKE ?1 ", nativeQuery=true)
	List<UserEntity> getCounselorsByFullName(String keyword);
	
	@Query("SELECT u FROM UserEntity u WHERE u.userRole.id IN(1,5) and u.statusActive = 3 and DATE(u.withdrawalDate) = DATE(?1) ")
	List<UserEntity> getUserWithDrawlByDay(Date date);
	
	@Query(
			value=	"Select count(*) from user u where u.gender = 0 and DATE(u.create_date) < DATE(?1) and u.role_id in ?2  and u.status_active != 3 "
					+ "union "
					+ "Select count(*) from user u where u.gender = 1 and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and u.status_active != 3 "
					,nativeQuery=true
			)
	List<Object> getGenderChart(Date now, Integer[] roleIds);
	
	@Query(
			value=	  "Select count(*) from user u where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 		  (YEAR(NOW()) - YEAR(u.day_of_birth)) < 20 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 20 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 30 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 30 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 40 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 40 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 50 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 50 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and u.status_active != 3"
					,nativeQuery=true
			)
	List<Object> getAgeChart(Date now, Integer[] roleIds);
	
	@Query(
			value=	  "Select count(*) from user u where u.role_id = 1 and DATE(u.create_date) < DATE(?1) and u.status_active != 3 "
					+ "union "
					+ "Select count(*) from user u where u.role_id = 5 and DATE(u.create_date) < DATE(?1) and u.status_active != 3 "
					,nativeQuery=true
			)
	List<Object> getUserTypeChart(Date now);
	
	@Query(
			value=	"Select count(*) from user u inner join counselor c on (u.id = c.id and c.status != 0) where u.gender = 0 and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 "
					+ "union "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status != 0) where u.gender = 1 and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 "
					,nativeQuery=true
			)
	List<Object> getGenderCounselorChart(Date now, Integer[] roleIds);
	
	@Query(
			value=	  "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status in (1,2)) where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 		  (YEAR(NOW()) - YEAR(u.day_of_birth)) < 20 "
					+ "union all "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status in (1,2)) where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 20 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 30 "
					+ "union all "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status in (1,2)) where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 30 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 40 "
					+ "union all "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status in (1,2)) where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 40 < (YEAR(NOW()) - YEAR(u.day_of_birth)) and (YEAR(NOW()) - YEAR(u.day_of_birth)) <= 50 "
					+ "union all "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status in (1,2)) where DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and 50 < (YEAR(NOW()) - YEAR(u.day_of_birth)) "
					,nativeQuery=true
			)
	List<Object> getAgeCounselorChart(Date now, Integer[] roleIds);
	
	@Query(
			value=	  "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status != 0) where u.role_id = 2 and DATE(u.create_date) < DATE(?1)"
					+ "union "
					+ "Select count(*) from user u inner join counselor c on (u.id = c.id and c.status != 0) where u.role_id = 3 and DATE(u.create_date) < DATE(?1)"
					,nativeQuery=true
			)
	List<Object> getCounselorTypeChart(Date now);
	
	@Query(
			value=	  "Select count(*) from user u where u.os_name like '%android%' and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where u.os_name like '%ios%' and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and u.status_active != 3 "
					+ "union all "
					+ "Select count(*) from user u where u.os_name NOT like '%ios%' and u.os_name NOT like '%android%' and u.os_name is not null and DATE(u.create_date) < DATE(?1) and u.role_id in ?2 and u.status_active != 3 "
					,nativeQuery=true
			)
	List<Object> getOsChart(Date now, Integer[] roleIds);
	
	@Query(value="SELECT COUNT(*) FROM user u WHERE u.username = ?1 ",nativeQuery=true)
	Integer countUserExitsByUsername(String username);
	
	@Query(value="SELECT count(DISTINCT(u.id)) FROM user u INNER JOIN business_sub bs ON u.business_sub_id = bs.id WHERE u.role_id = 5 and bs.business_id = ?1 ",nativeQuery=true)
	Integer totalMemberByBusinessId(Integer businessId);
	
	@Query(
			value=	"SELECT "
					+ " (SELECT COUNT(*) FROM counselor c1 INNER JOIN user u1 ON c1.id = u1.id WHERE c1.status = 2 and c1.counselor_center_id = ?1 and u1.role_id = 3 and u1.status_active = 0  ) AS totalCounselor_NotShowInApp, "
					+ " (SELECT COUNT(*) FROM counselor c2 INNER JOIN user u2 ON c2.id = u2.id WHERE u2.role_id = 3 and c2.counselor_center_id = ?1 and c2.actived = 0 ) AS totalCounselor_Inactive, "
					+ " (SELECT COUNT(*) FROM counselor c3 INNER JOIN user u3 ON c3.id = u3.id WHERE c3.status = 1 and c3.counselor_center_id = ?1 and u3.role_id = 3 and u3.status_active = 0 ) AS totalCounselor_normal, "
					+ " (SELECT COUNT(*) FROM counseling_session cl INNER JOIN counselor c ON cl.counselor_id = c.id WHERE c.counselor_center_id = ?1 and cl.status = 1 ) AS totalCounseling, "
					+ " (SELECT COUNT(*) FROM counseling_session cl1 INNER JOIN counselor c1 ON cl1.counselor_id = c1.id WHERE c1.counselor_center_id = ?1 and cl1.status IN (2,3) ) AS totalCounseled ", nativeQuery=true
			)
	List<Object[]> totalCounselingCenterStatistics(Integer counselingCenterId);
	
	@Query(value="SELECT if(COUNT(*)>0,'true','false') FROM user u WHERE u.phone = ?1 ",nativeQuery=true)
	boolean isDuplicatePhone(String phone);
}

