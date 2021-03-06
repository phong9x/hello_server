/*
 * Created on 6 thg 1 2017 ( Time 17:05:04 )
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
import org.trams.hello.bean.UserCertificate;
import org.trams.hello.bean.jpa.UserCertificateEntity;
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.mapping.UserCertificateServiceMapper;
import org.trams.hello.data.repository.jpa.UserCertificateJpaRepository;
/**
 * Implementation of UserCertificateService
 */
@Component
@Transactional
public class UserCertificateServiceImpl implements UserCertificateService {

	@Resource
	private UserCertificateJpaRepository userCertificateJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private UserCertificateServiceMapper userCertificateServiceMapper;
	
	@Override
	public UserCertificate findById(Integer id) {
		UserCertificateEntity userCertificateEntity = userCertificateJpaRepository.findOne(id);
		return userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntity);
	}

	@Override
	public Page<UserCertificateEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userCertificateJpaRepository.findAll(request);
	}

	@Override
	public List<UserCertificate> findAll() {
		Iterable<UserCertificateEntity> entities = userCertificateJpaRepository.findAll();
		List<UserCertificate> beans = new ArrayList<UserCertificate>();
		for(UserCertificateEntity userCertificateEntity : entities) {
			beans.add(userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userCertificateJpaRepository.count();
		return count;
	}

	@Override
	public UserCertificate save(UserCertificate userCertificate) {
		return update(userCertificate) ;
	}

	@Override
	public UserCertificate create(UserCertificate userCertificate) {
/*
		UserCertificateEntity userCertificateEntity = userCertificateJpaRepository.findOne(userCertificate.getId());
		if( userCertificateEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userCertificateEntity = new UserCertificateEntity();
		userCertificateServiceMapper.mapUserCertificateToUserCertificateEntity(userCertificate, userCertificateEntity);
		UserCertificateEntity userCertificateEntitySaved = userCertificateJpaRepository.save(userCertificateEntity);
		return userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntitySaved);
*/
		UserCertificateEntity userCertificateEntity = new UserCertificateEntity();
		userCertificateServiceMapper.mapUserCertificateToUserCertificateEntity(userCertificate, userCertificateEntity);
		UserCertificateEntity userCertificateEntitySaved = userCertificateJpaRepository.save(userCertificateEntity);
		return userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntitySaved);
	}

	@Override
	public UserCertificate update(UserCertificate userCertificate) {
		UserCertificateEntity userCertificateEntity = userCertificateJpaRepository.findOne(userCertificate.getId());
		userCertificateServiceMapper.mapUserCertificateToUserCertificateEntity(userCertificate, userCertificateEntity);
		UserCertificateEntity userCertificateEntitySaved = userCertificateJpaRepository.save(userCertificateEntity);
		return userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userCertificateJpaRepository.delete(id);
	}

	public UserCertificateJpaRepository getUserCertificateJpaRepository() {
		return userCertificateJpaRepository;
	}

	public void setUserCertificateJpaRepository(UserCertificateJpaRepository userCertificateJpaRepository) {
		this.userCertificateJpaRepository = userCertificateJpaRepository;
	}

	public UserCertificateServiceMapper getUserCertificateServiceMapper() {
		return userCertificateServiceMapper;
	}

	public void setUserCertificateServiceMapper(UserCertificateServiceMapper userCertificateServiceMapper) {
		this.userCertificateServiceMapper = userCertificateServiceMapper;
	}

	@Override
	public Page<UserCertificateEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userCertificateJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<UserCertificateEntity> listPagingByUserId(Integer page, Integer size, Integer userId) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userCertificateJpaRepository.listPagingByUserId(userId, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<UserCertificateEntity> findByName(String name, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userCertificateJpaRepository.findByName(name, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int countByUser(Integer userId) {
		return userCertificateJpaRepository.countByUser(userId);
	}

	@Override
	public void deleteByUserId(Integer userId) {
		try {
			userCertificateJpaRepository.deleteByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserCertificateEntity> findByUserId(Integer id) {
		return userCertificateJpaRepository.findByUserId(id);
	}

	@Override
	public UserCertificateEntity findByUserIdAndTypeAndName(Integer userId, short type, String diploma) {
		return userCertificateJpaRepository.findByUserIdAndTypeAndName(userId, type, diploma);
	}

	@Override
	public UserCertificate findOneByUserIdAndTypeAndName(Integer userId, short type, String diploma) {
        UserCertificateEntity userCertificateEntity = userCertificateJpaRepository.findByUserIdAndTypeAndName(userId, type, diploma);
        return userCertificateServiceMapper.mapUserCertificateEntityToUserCertificate(userCertificateEntity);
	}

	@Override
	public void deleteByUserIdAndTypeAndName(Integer userId, short type, String diploma) {
		userCertificateJpaRepository.deleteByUserIdAndTypeAndName(userId, type, diploma);
	}

}
