package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorFieldTempEntity;
import org.trams.hello.bean.jpa.CounselorFieldTempEntityKey;
/**
 * Repository : CounselorFieldTemp.
 */
public interface CounselorFieldTempJpaRepository extends PagingAndSortingRepository<CounselorFieldTempEntity, CounselorFieldTempEntityKey> {
	@Query(
			"Select u From CounselorFieldTempEntity u "
			)
	Page<CounselorFieldTempEntity> listPaging(Pageable pageable);
	
	@Query(
			"Select u From CounselorFieldTempEntity u  where u.compositePrimaryKey.counselorTempId = ?1"
			)
	List<CounselorFieldTempEntity> listPagingByCounselorTempId(Integer counselorTempId);
	
	@Modifying
	@Query(
			value = "delete from counselor_field_temp  where counselor_temp_id = ?1", nativeQuery = true
			)
	void deleteBycounselorTempId(Integer counselorTempId);
	
	
	@Query(
			value ="select c.category_name  from counselor_field_temp cf inner join  category c on cf.counselor_field_id = c.id where cf.counselor_temp_id = ?1 ", nativeQuery= true
			)
	List<Object> getCategoryNameBy_CouselorId(Integer counselorId);


}
