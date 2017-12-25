package org.trams.hello.data.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.api.RequestUserPsychologicalApi;
import org.trams.hello.bean.jpa.RequestUserPsychologicalEntity;

/**
 * Repository : RequestUserPsychological.
 */
public interface RequestUserPsychologicalJpaRepository
		extends PagingAndSortingRepository<RequestUserPsychologicalEntity, Integer> {
	@Query("Select u From RequestUserPsychologicalEntity u ")
	Page<RequestUserPsychologicalEntity> listPaging(Pageable pageable);

	@Query("Select u From RequestUserPsychologicalEntity u Where u.user.id = ?1 ")
	Page<RequestUserPsychologicalApi> listPagingByUserId(Integer userId, Pageable pageable);
	
	@Query(value=" SELECT u.fullname, r.counselor_id, r.request_psychological, r.id, r.effect_payment_psychological, "
			+ " (SELECT GROUP_CONCAT(p1.test_name) FROM user_psychological up1 INNER JOIN psychological_test_setting p1 ON up1.psychological_test_id = p1.id WHERE up1.request_psychological_id = r.id) AS testName, r.fee, "
			+ " (SELECT COUNT(*) FROM user_psychological up2 INNER JOIN psychological_test_setting p2 ON up2.psychological_test_id = p2.id WHERE up2.request_psychological_id = r.id) AS totalTest "
			+ " FROM request_user_psychological r "
			+ " INNER JOIN user u ON r.counselor_id = u.id WHERE r.user_id = ?1 and r.status = 0 ORDER BY r.request_psychological DESC ", nativeQuery=true)
	List<Object[]> listRequestUserPsyByUserId(Integer userId);
	
	@Query("Select u From RequestUserPsychologicalEntity u Where u.id = ?1 ")
	RequestUserPsychologicalEntity findByIdEntity(Integer id);
	
	@Query(value="select DISTINCT up.request_psychological_id from user_psychological up inner join psychological_test_setting p where p.id = ?1", nativeQuery=true)
	List<Object[]> getRequestPsychologicalByPsychologicalId(Integer testId);
	
	@Modifying
	@Query(
			value="update request_user_psychological r set r.status = 2 where r.effect_payment_psychological < ?1 and r.status = 0", nativeQuery=true
			)
	void updateRequestNotPayment(Date date);

}
