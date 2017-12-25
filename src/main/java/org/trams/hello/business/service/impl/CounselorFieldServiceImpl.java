/*
 * Created on 1 thg 12 2016 ( Time 11:42:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.CounselorField;
import org.trams.hello.bean.jpa.CounselorFieldEntity;
import org.trams.hello.bean.jpa.CounselorFieldEntityKey;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.mapping.CounselorFieldServiceMapper;
import org.trams.hello.data.repository.jpa.CounselorFieldJpaRepository;
/**
 * Implementation of CounselorFieldService
 */
@Component
@Transactional
public class CounselorFieldServiceImpl implements CounselorFieldService {

	@Resource
	private CounselorFieldJpaRepository counselorFieldJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private CounselorFieldServiceMapper counselorFieldServiceMapper;
	
	@Override
	public CounselorField findById(Integer counselorId, Integer counselorFieldId) {
		CounselorFieldEntityKey id = new CounselorFieldEntityKey(counselorId, counselorFieldId);
		CounselorFieldEntity counselorFieldEntity = counselorFieldJpaRepository.findOne(id);
		return counselorFieldServiceMapper.mapCounselorFieldEntityToCounselorField(counselorFieldEntity);
	}

	@Override
	public Page<CounselorFieldEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "counselorId, counselorFieldId")));
		return counselorFieldJpaRepository.findAll(request);
	}

	@Override
	public List<CounselorField> findAll() {
		Iterable<CounselorFieldEntity> entities = counselorFieldJpaRepository.findAll();
		List<CounselorField> beans = new ArrayList<CounselorField>();
		for(CounselorFieldEntity counselorFieldEntity : entities) {
			beans.add(counselorFieldServiceMapper.mapCounselorFieldEntityToCounselorField(counselorFieldEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = counselorFieldJpaRepository.count();
		return count;
	}

	@Override
	public CounselorField save(CounselorField counselorField) {
		return update(counselorField) ;
	}

	@Override
	public CounselorField create(CounselorField counselorField) {
/*
		CounselorFieldEntityKey id = new CounselorFieldEntityKey(counselorField.getCounselorId(), counselorField.getCounselorFieldId());
		CounselorFieldEntity counselorFieldEntity = counselorFieldJpaRepository.findOne(id);
		if( counselorFieldEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		counselorFieldEntity = new CounselorFieldEntity();
		counselorFieldServiceMapper.mapCounselorFieldToCounselorFieldEntity(counselorField, counselorFieldEntity);
		CounselorFieldEntity counselorFieldEntitySaved = counselorFieldJpaRepository.save(counselorFieldEntity);
		return counselorFieldServiceMapper.mapCounselorFieldEntityToCounselorField(counselorFieldEntitySaved);
*/
		CounselorFieldEntity counselorFieldEntity = new CounselorFieldEntity();
		counselorFieldServiceMapper.mapCounselorFieldToCounselorFieldEntity(counselorField, counselorFieldEntity);
		CounselorFieldEntity counselorFieldEntitySaved = counselorFieldJpaRepository.save(counselorFieldEntity);
		return counselorFieldServiceMapper.mapCounselorFieldEntityToCounselorField(counselorFieldEntitySaved);
	}

	@Override
	public CounselorField update(CounselorField counselorField) {
		CounselorFieldEntityKey id = new CounselorFieldEntityKey(counselorField.getCounselorId(), counselorField.getCounselorFieldId());
		CounselorFieldEntity counselorFieldEntity = counselorFieldJpaRepository.findOne(id);
		counselorFieldServiceMapper.mapCounselorFieldToCounselorFieldEntity(counselorField, counselorFieldEntity);
		CounselorFieldEntity counselorFieldEntitySaved = counselorFieldJpaRepository.save(counselorFieldEntity);
		return counselorFieldServiceMapper.mapCounselorFieldEntityToCounselorField(counselorFieldEntitySaved);
	}

	@Override
	public void delete(Integer counselorId, Integer counselorFieldId) {
		CounselorFieldEntityKey id = new CounselorFieldEntityKey(counselorId, counselorFieldId);
		counselorFieldJpaRepository.delete(id);
	}

	public CounselorFieldJpaRepository getCounselorFieldJpaRepository() {
		return counselorFieldJpaRepository;
	}

	public void setCounselorFieldJpaRepository(CounselorFieldJpaRepository counselorFieldJpaRepository) {
		this.counselorFieldJpaRepository = counselorFieldJpaRepository;
	}

	public CounselorFieldServiceMapper getCounselorFieldServiceMapper() {
		return counselorFieldServiceMapper;
	}

	public void setCounselorFieldServiceMapper(CounselorFieldServiceMapper counselorFieldServiceMapper) {
		this.counselorFieldServiceMapper = counselorFieldServiceMapper;
	}

	@Override
	public Page<CounselorFieldEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return counselorFieldJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<CounselorFieldEntity> listBy_CounselorId(Integer counselorId) {
		try {
			return counselorFieldJpaRepository.listBy_CounselorId(counselorId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public void deleteByCounselorId(Integer counselorId) {
		try {
			 counselorFieldJpaRepository.deleteByCounselorId(counselorId);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Object> getCategoryNameBy_CouselorId(Integer counselorId) {
		try {
			 return  counselorFieldJpaRepository.getCategoryNameBy_CouselorId(counselorId);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}