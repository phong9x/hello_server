package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.QuestionnaireEntity;

/**
 * Repository : Questionnaire.
 */
public interface QuestionnaireJpaRepository extends PagingAndSortingRepository<QuestionnaireEntity, Integer> {
	@Query("Select u From QuestionnaireEntity u ")
	Page<QuestionnaireEntity> listPaging(Pageable pageable);

	@Query("Select u From QuestionnaireEntity u Where u.title like ?1 ")
	Page<QuestionnaireEntity> findByTitle(String title, Pageable pageable);
	
	@Query("Select u From QuestionnaireEntity u Where u.isShow = 1 and u.type = ?1")
	QuestionnaireEntity findQuestionnaireByIsShowAndType(Integer type);
	
	@Query("SELECT u FROM QuestionnaireEntity u WHERE u.type = ?1")
	Page<QuestionnaireEntity> listPagingByType(Short type,Pageable pageable);
	
	@Query("SELECT u FROM QuestionnaireEntity u WHERE u.type = ?1")
	List<QuestionnaireEntity> listByType(Short type,Pageable pageable);
	
	@Modifying
	@Query(
			value ="UPDATE questionnaire u set u.is_show = 0 WHERE u.type = ?1", nativeQuery = true
			)
	void updateIsShowByType(Short type);
	
	@Query(
			value ="select q.version from questionnaire q where q.type = ?1 order by q.create_date DESC limit 1", nativeQuery = true
			)
	String getNameVersionBy_Type(Short type);
	
}
