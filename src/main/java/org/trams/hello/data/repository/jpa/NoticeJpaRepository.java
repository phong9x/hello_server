package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.NoticeEntity;

/**
 * Repository : Notice.
 */
public interface NoticeJpaRepository extends PagingAndSortingRepository<NoticeEntity, Integer> {
	@Query("Select u From NoticeEntity u ")
	Page<NoticeEntity> listPaging(Pageable pageable);

	@Query("Select u From NoticeEntity u Where u.title like ?1 ")
	Page<NoticeEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From NoticeEntity u Where u.type = 1  ")
	Page<NoticeEntity> listPagingByCounselorIdInWebC(Pageable pageable);
	
	@Query("Select u From NoticeEntity u Where DATE(u.createDate) >= ?1 and DATE(u.createDate) <= ?2 and u.type = ?4 ")
	Page<NoticeEntity> listPagingByUser(String startSearch, String endSearch, String orderBy, Integer type, String keyword, Pageable pageable);
	
	@Query("Select u From NoticeEntity u Where u.id = ?1  ")
	NoticeEntity findByIdEntity(Integer id);

	@Query("Select MAX(u.id) From NoticeEntity u")
	Integer getEndElementInListId();
}
