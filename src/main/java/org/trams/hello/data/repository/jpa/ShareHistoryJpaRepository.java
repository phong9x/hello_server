package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.ShareHistoryEntity;
/**
 * Repository : ShareHistory.
 */
public interface ShareHistoryJpaRepository extends PagingAndSortingRepository<ShareHistoryEntity, Integer>,
		JpaSpecificationExecutor<ShareHistoryEntity> {
	@Query(
			"Select u From ShareHistoryEntity u "
			)
	Page<ShareHistoryEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From ShareHistoryEntity u Where u.userId = ?1 "
			)
	Page<ShareHistoryEntity> listPagingByUserId( Integer userId, Pageable pageable);


}
