/*
 * Created on 22 thg 2 2017 ( Time 10:38:11 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.trams.hello.bean.MindCheckStatistics;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PsychologicalStatistics;
import org.trams.hello.bean.TestResult;
import org.trams.hello.bean.jpa.TestResultEntity;
import org.trams.hello.web.bean.search.SearchStatistics;

/**
 * Business Service Interface for entity TestResult.
 */
public interface TestResultService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	TestResult findById( Integer id  ) ;

	/** 
	 * Loads all entities.
	 * @return all entities
	 */
	List<TestResult> findAll();
	
	/**
	 * Loads entities in page number.
	 * @return entities
	 */

	Page<TestResultEntity> findAll(Integer page);

	/**
	 * Count all entities
	 * @return Long
	 */
	Long countTotal();
	

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	TestResult save(TestResult entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	TestResult update(TestResult entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	TestResult create(TestResult entity);
	
	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );

	Page<TestResultEntity> listPaging(Integer page,Integer size);
	
	List<TestResultEntity> findBy_UserAndType(Integer user, Integer type, String orderBy);

	Page<TestResultEntity> filter(Map<String, Object> params);

	List<TestResultEntity> listResultBy_UserQuestionId(Integer userQuestionaireId);
	
	void deleteBy_UserQuestionId(Integer userQuestionaireId);
	
	List<TestResultEntity> findBy_UserQuestionaireId(Integer userQuestionaireId);
	
	TestResultEntity getTestResultByUserQuestionaireIdAndQuestionId(Integer userQuestionaireId, Integer questionId);
	
	PageCustom<MindCheckStatistics> filterMindCheckStatistics(SearchStatistics s);

	PageCustom<PsychologicalStatistics> filterPsychologicalStatistics(SearchStatistics s);
}