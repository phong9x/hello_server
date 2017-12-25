package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CounselorTempEntity;
/**
 * Repository : CounselorTemp.
 */
public interface CounselorTempJpaRepository extends JpaSpecificationExecutor<CounselorTempEntity>, PagingAndSortingRepository<CounselorTempEntity, Integer> {
	@Query(
			"Select u From CounselorTempEntity u Where u.isDelete = 0"
			)
	Page<CounselorTempEntity> listPaging(Pageable pageable);
	

	@Modifying
	@Query(
			value="UPDATE counselortemp SET is_delete = 1 WHERE id = ?1",nativeQuery=true
			)
	void update_isDelete(Integer id);

	
}
