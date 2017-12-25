package org.trams.hello.data.repository.jpa;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselingSessionEntity;

/**
 * Repository : CounselingSession.
 */
public interface CounselingSessionJpaRepository extends PagingAndSortingRepository<CounselingSessionEntity, Integer>,
		JpaSpecificationExecutor<CounselingSessionEntity> {
	@Query("Select u From CounselingSessionEntity u Where u.isDelete = 0")
	Page<CounselingSessionEntity> listPaging(Pageable pageable);

	@Modifying
	@Query(value = "UPDATE counseling_session SET is_delete = 1 WHERE id = ?1", nativeQuery = true)
	void update_isDelete(Integer id);

	@Query(value = "select cs.id from counseling_session cs where cs.user_id = ?1 and cs.counselor_id = ?2 and cs.is_delete =0 and cs.id not in "
			+ "(select c.counseling_session_id  from comment c where c.user_id = ?1)", nativeQuery = true)
	Integer countSatisfactionEvaluation(Integer userId, Integer counselorId);

	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and cs.status in (2,3)", nativeQuery = true)
	Integer countCounselingSession(Integer userId);

	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and cs.start_time > ?2 and cs.status = 1", nativeQuery = true)
	Integer countCounselingSessionHaveCounselYet(Integer userId, Date date);

	@Query(value = "select exists( select id from counseling_session where counselor_id = ?1 and status = ?2 and is_delete = 0 )", nativeQuery = true)
	Integer countCounselingSessionOfCounselor(Integer counselorId, Short status);

	@Query("Select u From CounselingSessionEntity u "
			+ "Where u.user.id = ?1 and u.startTime >= ?2 and u.startTime <= ?3 and u.status in ?4 "
			+ "and u.paymentHistory.status = ?5 and u.paymentHistory.typePayment = ?6 and u.paymentHistory.typeCoin in ?7 ")
	List<CounselingSessionEntity> filterByMonth(Integer userId, Date startDate, Date toDate, Short[] statuses, Short paymentStatus, Integer typePayment, Short[] typeCoin);
	
	@Query("Select c From CounselingSessionEntity c Where c.user.id = ?1 and c.startTime > NOW() and c.status = 1 ORDER BY c.startTime ASC ")
	List<CounselingSessionEntity> findEarliestCounselingTime(Integer userId);
	
	@Query(value = "select count(*) from counseling_session cs where cs.user_id = ?1 and cs.is_delete = 0 and cs.status in (2,3,4) ", nativeQuery = true)
	Integer totalCompletedCounselingHistoryByUser(Integer userId);
	
	@Query(value = "SELECT A.end_time "
					+ "FROM (SELECT DISTINCT YEAR(end_time) end_time "
							+ "FROM counseling_session "
							+ "WHERE status IN (2,3,4) and user_id = ?1) A "
					+ "ORDER BY A.end_time DESC", nativeQuery = true)
	Integer[] getYearCompletedCounseling(Integer userId);
	
	@Query("Select TIME(c.startTime), c.counselingTimeType, TIME(c.endTime) From CounselingSessionEntity c Where c.counselor.id = ?2 and DATE(c.startTime) = DATE(?1) and c.status IN (1,2,3,4) order by c.startTime DESC ")
	List<Object[]> listStartTimeCounselingSSByDay(Date dayConvert, Integer counselorId);
	
	@Query("Select c.startTime, c.endTime From CounselingSessionEntity c Where c.counselor.id = ?2 and DATE(c.startTime) = DATE(?1) and c.counselType = 2 and c.status IN (1,2,3,4) order by c.startTime ASC ")
	List<Object[]> getReservedList(Date dayConvert, Integer counselorId);
	
	@Query(value="SELECT DISTINCT u.fullname, u.email, u.id FROM counseling_session c "
			+ " INNER JOIN `user` u ON c.user_id = u.id "
			+ " WHERE c.counselor_id = ?1 "
			+ " and c.start_time >= (NOW() - INTERVAL 7 DAY) and c.start_time <= NOW() ", nativeQuery= true)
	List<Object[]> listCounselingSSInAWeek(Integer counselorId);
	
	@Query(value="SELECT DISTINCT u.fullname, u.email, u.id FROM counseling_session c "
			+ " INNER JOIN `user` u ON c.user_id = u.id "
			+ " WHERE c.counselor_id = ?1 ", nativeQuery= true)
	List<Object[]> listCounselingSessionByCounselorId(Integer counselorId);
	
	
	@Query("Select u From CounselingSessionEntity u Where u.user.id = ?1 and u.id = ?2 and u.isDelete = 0")
	CounselingSessionEntity findByIdAnhUserId(Integer userId, Integer revertionId);
	
	@Query(
			value ="SELECT count(*) "
				+ "FROM counseling_session u "
				+ "WHERE ((u.start_time BETWEEN ?1 AND ?2) "
						+ "OR ( u.end_time BETWEEN ?1 AND ?2)"
						+ "OR ( ?1 BETWEEN u.start_time AND u.end_time)"
						+ "OR ( ?2 BETWEEN u.start_time AND u.end_time)) "
				+ "AND u.counselor_id = ?3 "
				+ "AND u.status = 1", nativeQuery = true
			)
	int countBy(Date start, Date end, Integer counselorId);
	
	@Query(
			value ="SELECT count(*) "
				+ "FROM counseling_session u "
				+ "WHERE ?1 BETWEEN u.start_time AND u.end_time "
				+ "AND u.counselor_id = ?2 "
				+ "AND u.status = 1", nativeQuery = true
			)
	int countByNow(Date now, Integer counselorId);
	
	@Query("Select c From CounselingSessionEntity c Where Year(c.startTime)=?1 and Month(c.startTime)=?2 and Day(c.startTime)=?3 and Hour(c.startTime)=?4 and MINUTE(c.startTime)=?5 "
			+ "and c.status = 1 and c.counselType =2 and c.user.reservationNotification = 1 ORDER BY c.counselor.id ASC ")
	List<CounselingSessionEntity> listNotificaionByMinutes(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query("Select c From CounselingSessionEntity c Where Year(c.startTime)=?1 and Month(c.startTime)=?2 and Day(c.startTime)=?3 and Hour(c.startTime)=?4 and MINUTE(c.startTime)=?5 "
			+ "and c.status = 1 and c.counselType =2 and c.user.reservationNotification = 1  ORDER BY c.counselor.id ASC ")
	List<CounselingSessionEntity> listNotificaionByHour(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query( value = "Select u.username, c.id, c.counselor_id, u.id as userId From counseling_session c inner join user u on c.user_id = u.id Where Year(c.end_time)=?1 and Month(c.end_time)=?2 and Day(c.end_time)=?3 and Hour(c.end_time)=?4 and MINUTE(c.end_time)=?5 and (c.status !=0) and c.id not in (select uq.counseling_session_id from user_questionaire uq where uq.type = 1) and u.reservation_notification = 1 ",nativeQuery = true)
	List<Object[]> listRequiredSatisfaction(Integer year, Integer month, Integer day, Integer hour, Integer minute);
	
	@Query(value="Select (HOUR(c.start_time) * 60 + MINUTE(c.start_time)) From counseling_session c Where DATE(c.start_time) = DATE(?2) and c.counselor_id = ?1 and c.counsel_type = 2 and c.status IN (1,2,3,4) ORDER BY c.start_time ASC ", nativeQuery=true)
	List<BigInteger> listTimeByDate(Integer couselorId, Date date);
	
	@Query(value= "SELECT COUNT(*) "
			+ " FROM "
			+ " counseling_session c "
			+ " WHERE "
			+ " c.start_time >= ?1 and c.start_time <= ?2 and c.status IN(2,3) and c.counselor_id = ?3 ", nativeQuery=true)
	Integer countTotalCouseling(Date startTime, Date endTime, Integer couselorId);
	@Modifying
	@Query(value="UPDATE counseling_session  "
				+ "SET status =2 "
				+ "WHERE end_time <= DATE_SUB(NOW(), INTERVAL 5 MINUTE) AND status = 1", 
				nativeQuery = true)
	void updateCounselingFinished();
	@Modifying
	@Query(value="update counseling_session  set status =1 Where end_time > NOW() and status = 2",nativeQuery = true)
	void updateCounselingNotFinished();

    CounselingSessionEntity findByRoomId(String roomId);
    
    @Query(value="SELECT (SUM(c.point) / COUNT(c.id)) FROM counseling_session c WHERE c.counselor_id = ?1 ",nativeQuery = true)
    Float totalPointByCounselor(Integer counselorId);
    
	@Query(value="SELECT COUNT(*) FROM counseling_session cl INNER JOIN counselor c ON cl.counselor_id = c.id WHERE c.counselor_center_id = ?2 and cl.status = 1 and DATE(cl.create_date) = ?1 ",nativeQuery=true)
	Integer totalCounselingByCounselingCenterId(String date, Integer counselingId);
	
	@Query(value="SELECT COUNT(*) FROM counseling_session cl1 INNER JOIN counselor c1 ON cl1.counselor_id = c1.id WHERE c1.counselor_center_id = ?2 and cl1.status IN (2,3) and DATE(cl1.create_date) = ?1 ",nativeQuery=true)
	Integer totalCounseledsByCounselingCenterId(String date, Integer counselingId);
    
	CounselingSessionEntity findByPreRoomId(String roomId);
	
	@Query(value="SELECT * FROM counseling_session c WHERE DATE(start_time) = ?3 and (HOUR(start_time) * 60 + MINUTE(start_time)) >= ?1 and (HOUR(end_time) * 60 + MINUTE(end_time)) <= ?2 and c.status = 1 and c.counselor_id = ?4 ORDER BY c.start_time ASC " , nativeQuery=true)
	List<CounselingSessionEntity> listCounselingSessionByFromAndToDate(Integer totalMinuteFromDate, Integer totalMinuteToDate, String now, Integer counselor);
	
	@Query(value="SELECT COUNT(*) "
				+ "FROM counseling_session "
				+ "WHERE counselor_id = ?1 "
				+ "AND status = 1 "
				+ "AND start_time <= ?2 "
				+ "AND end_time >= ?2 "
				,nativeQuery=true)
	Integer countInProgressCounseling(Integer counselorId, Date now);
	
	@Query(value="SELECT end_time "
			+ "FROM counseling_session "
			+ "WHERE counselor_id = ?1 "
			+ "AND DATE(end_time) = DATE(NOW()) "
			+ "AND end_time < NOW() "
			+ "AND status = 2 "
			+ "ORDER BY end_time DESC "
			+ "LIMIT 1"
			,nativeQuery=true)
	Date getLatestCounselingEndTime(Integer counselorId);
	
	@Query(value="SELECT start_time "
			+ "FROM counseling_session "
			+ "WHERE counselor_id = ?1 "
			+ "AND DATE(start_time) = DATE(NOW()) "
			+ "AND start_time > NOW() "
			+ "AND status = 1 "
			+ "ORDER BY start_time ASC "
			+ "LIMIT 1"
			,nativeQuery=true)
	Date getClosestCounselingStartTime(Integer counselorId);
}
