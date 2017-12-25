/*
 * Created on 22 May 2017 ( Time 09:42:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.PushNotification;
import org.trams.hello.bean.jpa.PushNotificationEntity;
import org.trams.hello.business.service.PushNotificationService;
import org.trams.hello.business.service.mapping.PushNotificationServiceMapper;
import org.trams.hello.data.repository.jpa.PushNotificationJpaRepository;
import org.trams.hello.web.common.utils.DataUtils;
/**
 * Implementation of PushNotificationService
 */
@Component
@Transactional
public class PushNotificationServiceImpl implements PushNotificationService {

	@Resource
	private PushNotificationJpaRepository pushNotificationJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private PushNotificationServiceMapper pushNotificatinServiceMapper;
	
	@Override
	public PushNotification findById(Integer id) {
		PushNotificationEntity pushNotificatinEntity = pushNotificationJpaRepository.findOne(id);
		return pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntity);
	}

	@Override
	public Page<PushNotificationEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return pushNotificationJpaRepository.findAll(request);
	}

	@Override
	public List<PushNotification> findAll() {
		Iterable<PushNotificationEntity> entities = pushNotificationJpaRepository.findAll();
		List<PushNotification> beans = new ArrayList<PushNotification>();
		for(PushNotificationEntity pushNotificatinEntity : entities) {
			beans.add(pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = pushNotificationJpaRepository.count();
		return count;
	}

	@Override
	public PushNotification save(PushNotification pushNotificatin) {
		return update(pushNotificatin) ;
	}

	@Override
	public PushNotification create(PushNotification pushNotificatin) {
/*
		PushNotificationEntity pushNotificatinEntity = pushNotificationJpaRepository.findOne(pushNotificatin.getId());
		if( pushNotificatinEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		pushNotificatinEntity = new PushNotificationEntity();
		pushNotificatinServiceMapper.mapPushNotificationToPushNotificationEntity(pushNotificatin, pushNotificatinEntity);
		PushNotificationEntity pushNotificatinEntitySaved = pushNotificationJpaRepository.save(pushNotificatinEntity);
		return pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntitySaved);
*/
		PushNotificationEntity pushNotificatinEntity = new PushNotificationEntity();
		pushNotificatinServiceMapper.mapPushNotificationToPushNotificationEntity(pushNotificatin, pushNotificatinEntity);
		PushNotificationEntity pushNotificatinEntitySaved = pushNotificationJpaRepository.save(pushNotificatinEntity);
		return pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntitySaved);
	}

	@Override
	public PushNotification update(PushNotification pushNotificatin) {
		PushNotificationEntity pushNotificatinEntity = pushNotificationJpaRepository.findOne(pushNotificatin.getId());
		pushNotificatinServiceMapper.mapPushNotificationToPushNotificationEntity(pushNotificatin, pushNotificatinEntity);
		PushNotificationEntity pushNotificatinEntitySaved = pushNotificationJpaRepository.save(pushNotificatinEntity);
		return pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntitySaved);
	}
	
	@Override
	public PushNotification update(PushNotificationEntity pushNotificationEntity) {
		PushNotificationEntity pushNotificatinEntitySaved = pushNotificationJpaRepository.save(pushNotificationEntity);
		return pushNotificatinServiceMapper.mapPushNotificationEntityToPushNotification(pushNotificatinEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		pushNotificationJpaRepository.delete(id);
	}

	public PushNotificationJpaRepository getPushNotificationJpaRepository() {
		return pushNotificationJpaRepository;
	}

	public void setPushNotificationJpaRepository(PushNotificationJpaRepository pushNotificationJpaRepository) {
		this.pushNotificationJpaRepository = pushNotificationJpaRepository;
	}

	public PushNotificationServiceMapper getPushNotificationServiceMapper() {
		return pushNotificatinServiceMapper;
	}

	public void setPushNotificationServiceMapper(PushNotificationServiceMapper pushNotificatinServiceMapper) {
		this.pushNotificatinServiceMapper = pushNotificatinServiceMapper;
	}

	@Override
	public Page<PushNotificationEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return pushNotificationJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public Page<PushNotificationEntity> findByTitle(String title, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return pushNotificationJpaRepository.findByTitle(title, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<PushNotificationEntity> filterAdmin(String startDate, String endDate, String type, String key, Integer page, Integer size) {

		PageRequest pageable = new PageRequest(page - 1, size,
				new Sort(new Order(Direction.DESC, "createDate")));

		Specification<PushNotificationEntity> specification = new Specification<PushNotificationEntity>() {
			@Override
			public Predicate toPredicate(Root<PushNotificationEntity> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();
				// date
				if (startDate != null) {
					Date start = DataUtils.parseDate(startDate, "yyyy/MM/dd");
					Predicate startDateLess = cb.greaterThanOrEqualTo(root.get("createDate"), start);
					predicates.add(startDateLess);
				}
				if (endDate != null) {
					Date end = DataUtils.parseDate(endDate + " 23:59:59", "yyyy/MM/dd HH:mm:ss");
					Predicate endDateLess = cb.lessThanOrEqualTo(root.get("createDate"), end);
					predicates.add(endDateLess);
				}
				
				if (type != null && key != null) {
					if(type.equals("title")){
						predicates.add(cb.like(root.get("title"), "%" + key + "%"));
					}else if(type.equals("content")){
						predicates.add(cb.like(root.get("content"), "%" + key + "%"));
					}else{
						predicates.add(cb.like(root.get("user").get("fullname"), "%" + key + "%"));
					}
					
				}
				
				if (predicates.isEmpty()) {
					return cb.isNotNull(root.get("id"));
				} else {
					return cb.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return pushNotificationJpaRepository.findAll(specification, pageable);
	}
	@Override
	public void updateStatusByIds(Short status, Integer[] id) {
		try {
			pushNotificationJpaRepository.updateStatusByIds(status, id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public List<PushNotificationEntity> findByDateAndStatus(String date, short status) {
		try {
			return pushNotificationJpaRepository.findByDateAndStatus(date, status);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
