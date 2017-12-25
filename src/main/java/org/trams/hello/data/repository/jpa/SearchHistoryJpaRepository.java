package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.SearchHistoryEntity;
/**
 * Repository : SearchHistory.
 */
public interface SearchHistoryJpaRepository extends PagingAndSortingRepository<SearchHistoryEntity, Long>,
		JpaSpecificationExecutor<SearchHistoryEntity> {
	@Query(
			"Select u From SearchHistoryEntity u "
			)
	Page<SearchHistoryEntity> listPaging(Pageable pageable);

	@Query(
			"Select u From SearchHistoryEntity u Where u.userId = ?1 "
			)
	Page<SearchHistoryEntity> listPagingByUserId( Integer userId, Pageable pageable);


}
