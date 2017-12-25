package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.FaqEntity;

/**
 * Repository : Faq.
 */
public interface FaqJpaRepository extends PagingAndSortingRepository<FaqEntity, Integer> {
	@Query("Select u From FaqEntity u ")
	Page<FaqEntity> listPaging(Pageable pageable);

	@Query("Select u From FaqEntity u Where u.title like ?1 ")
	Page<FaqEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From FaqEntity u Where u.isShow = 1 and u.type = 1 ")
	List<FaqEntity> listByIsShow();
	
	@Query(value="Select * from faq ORDER BY create_date DESC limit 4 ", nativeQuery=true)
	List<FaqEntity> listInCounselorWeb();
	
	@Query("Select u From FaqEntity u Where u.type = 2 and u.isShow = 1 and (u.title like ?1 or u.content like ?1 ) ")
	List<FaqEntity> findByTitleOrContent(String keyword);
	
	@Query("Select u From FaqEntity u Where u.isShow = 1 and u.type = ?1 and u.osDisplay in(2,3) ")
	List<FaqEntity> listByIsShowAndType(Short type);
	
	@Query("Select u From FaqEntity u Where u.type = ?1 order by u.orderNumber ASC ")
	List<FaqEntity> listInAdmin(Short type);
	
	@Query("Select Max(u.id) From FaqEntity u Where u.type = ?1 order by u.orderNumber ASC ")
	Integer findFaqIdMax(Short type);
}
