package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.UserFileTempEntity;
/**
 * Repository : UserFileTemp.
 */
public interface UserFileTempJpaRepository extends PagingAndSortingRepository<UserFileTempEntity, Integer> {
	@Query(
			"Select u From UserFileTempEntity u "
			)
	Page<UserFileTempEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From UserFileTempEntity u Where u.counselorTempId = ?1 "
			)
	Page<UserFileTempEntity> listPagingByUserId( Integer userId, Pageable pageable);


}
