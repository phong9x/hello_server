/*
 * Created on 23 thg 11 2016 ( Time 13:38:13 )
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
import org.trams.hello.bean.UserRole;
import org.trams.hello.bean.jpa.UserRoleEntity;
import org.trams.hello.business.service.UserRoleService;
import org.trams.hello.business.service.mapping.UserRoleServiceMapper;
import org.trams.hello.data.repository.jpa.UserRoleJpaRepository;
/**
 * Implementation of UserRoleService
 */
@Component
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleJpaRepository userRoleJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private UserRoleServiceMapper userRoleServiceMapper;
	
	@Override
	public UserRole findById(Integer id) {
		UserRoleEntity userRoleEntity = userRoleJpaRepository.findOne(id);
		return userRoleServiceMapper.mapUserRoleEntityToUserRole(userRoleEntity);
	}

	@Override
	public Page<UserRoleEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return userRoleJpaRepository.findAll(request);
	}

	@Override
	public List<UserRole> findAll() {
		Iterable<UserRoleEntity> entities = userRoleJpaRepository.findAll();
		List<UserRole> beans = new ArrayList<UserRole>();
		for(UserRoleEntity userRoleEntity : entities) {
			beans.add(userRoleServiceMapper.mapUserRoleEntityToUserRole(userRoleEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = userRoleJpaRepository.count();
		return count;
	}

	@Override
	public UserRole save(UserRole userRole) {
		return update(userRole) ;
	}

	@Override
	public UserRole create(UserRole userRole) {
/*
		UserRoleEntity userRoleEntity = userRoleJpaRepository.findOne(userRole.getId());
		if( userRoleEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		userRoleEntity = new UserRoleEntity();
		userRoleServiceMapper.mapUserRoleToUserRoleEntity(userRole, userRoleEntity);
		UserRoleEntity userRoleEntitySaved = userRoleJpaRepository.save(userRoleEntity);
		return userRoleServiceMapper.mapUserRoleEntityToUserRole(userRoleEntitySaved);
*/
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleServiceMapper.mapUserRoleToUserRoleEntity(userRole, userRoleEntity);
		UserRoleEntity userRoleEntitySaved = userRoleJpaRepository.save(userRoleEntity);
		return userRoleServiceMapper.mapUserRoleEntityToUserRole(userRoleEntitySaved);
	}

	@Override
	public UserRole update(UserRole userRole) {
		UserRoleEntity userRoleEntity = userRoleJpaRepository.findOne(userRole.getId());
		userRoleServiceMapper.mapUserRoleToUserRoleEntity(userRole, userRoleEntity);
		UserRoleEntity userRoleEntitySaved = userRoleJpaRepository.save(userRoleEntity);
		return userRoleServiceMapper.mapUserRoleEntityToUserRole(userRoleEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		userRoleJpaRepository.delete(id);
	}

	public UserRoleJpaRepository getUserRoleJpaRepository() {
		return userRoleJpaRepository;
	}

	public void setUserRoleJpaRepository(UserRoleJpaRepository userRoleJpaRepository) {
		this.userRoleJpaRepository = userRoleJpaRepository;
	}

	public UserRoleServiceMapper getUserRoleServiceMapper() {
		return userRoleServiceMapper;
	}

	public void setUserRoleServiceMapper(UserRoleServiceMapper userRoleServiceMapper) {
		this.userRoleServiceMapper = userRoleServiceMapper;
	}

	@Override
	public Page<UserRoleEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return userRoleJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	


}
