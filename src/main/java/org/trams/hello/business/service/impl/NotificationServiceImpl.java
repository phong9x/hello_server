/*
 * Created on 11 thg 1 2017 ( Time 10:17:14 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.Notification;
import org.trams.hello.bean.UserNotification;
import org.trams.hello.bean.jpa.NotificationEntity;
import org.trams.hello.bean.jpa.PushNotificationEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.service.NotificationService;
import org.trams.hello.business.service.UserNotificationService;
import org.trams.hello.business.service.mapping.NotificationServiceMapper;
import org.trams.hello.data.repository.jpa.NotificationJpaRepository;
import org.trams.hello.data.repository.jpa.UserNotificationJpaRepository;
import org.trams.hello.push.service.impl.SendPushServiceImpl;
import org.trams.hello.web.common.ApplicationDefine;

/**
 * Implementation of NotificationService
 */
@Component
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Resource
	private NotificationJpaRepository notificationJpaRepository;
	@Resource
	private UserNotificationService userNotificationService;
	@Resource
	private UserNotificationJpaRepository userNotificationJpaRepository;
	
	private SendPushServiceImpl sendPushServiceImpl = new SendPushServiceImpl();

	private static final Integer PAGE_SIZE = 15;
	
	@Autowired
	private EntityManager em;

	@Resource
	private NotificationServiceMapper notificationServiceMapper;

	@Override
	public Notification findById(Integer id) {
		NotificationEntity notificationEntity = notificationJpaRepository.findOne(id);
		return notificationServiceMapper.mapNotificationEntityToNotification(notificationEntity);
	}

	@Override
	public Page<NotificationEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, new Sort(new Order(Direction.DESC, "id")));
		return notificationJpaRepository.findAll(request);
	}

	@Override
	public List<Notification> findAll() {
		Iterable<NotificationEntity> entities = notificationJpaRepository.findAll();
		List<Notification> beans = new ArrayList<Notification>();
		for (NotificationEntity notificationEntity : entities) {
			beans.add(notificationServiceMapper.mapNotificationEntityToNotification(notificationEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * 
	 * @return Long
	 */
	public Long countTotal() {
		Long count = notificationJpaRepository.count();
		return count;
	}

	@Override
	public Notification save(Notification notification) {
		return update(notification);
	}

	@Override
	public Notification create(Notification notification) {
		/*
		 * NotificationEntity notificationEntity =
		 * notificationJpaRepository.findOne(notification.getId()); if(
		 * notificationEntity != null ) { throw new
		 * IllegalStateException("already.exists"); }
		 * 
		 * notificationEntity = new NotificationEntity();
		 * notificationServiceMapper.mapNotificationToNotificationEntity(
		 * notification, notificationEntity); NotificationEntity
		 * notificationEntitySaved =
		 * notificationJpaRepository.save(notificationEntity); return
		 * notificationServiceMapper.mapNotificationEntityToNotification(
		 * notificationEntitySaved);
		 */
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationServiceMapper.mapNotificationToNotificationEntity(notification, notificationEntity);
		NotificationEntity notificationEntitySaved = notificationJpaRepository.save(notificationEntity);
		return notificationServiceMapper.mapNotificationEntityToNotification(notificationEntitySaved);
	}

	@Override
	public Notification update(Notification notification) {
		NotificationEntity notificationEntity = notificationJpaRepository.findOne(notification.getId());
		notificationServiceMapper.mapNotificationToNotificationEntity(notification, notificationEntity);
		NotificationEntity notificationEntitySaved = notificationJpaRepository.save(notificationEntity);
		return notificationServiceMapper.mapNotificationEntityToNotification(notificationEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		notificationJpaRepository.delete(id);
	}

	public NotificationJpaRepository getNotificationJpaRepository() {
		return notificationJpaRepository;
	}

	public void setNotificationJpaRepository(NotificationJpaRepository notificationJpaRepository) {
		this.notificationJpaRepository = notificationJpaRepository;
	}

	public NotificationServiceMapper getNotificationServiceMapper() {
		return notificationServiceMapper;
	}

	public void setNotificationServiceMapper(NotificationServiceMapper notificationServiceMapper) {
		this.notificationServiceMapper = notificationServiceMapper;
	}

	@Override
	public Page<NotificationEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return notificationJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Page<NotificationEntity> findByTitle(String title, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return notificationJpaRepository.findByTitle(title, pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long countNotification(Map<String, Object> params) {
		Specification<NotificationEntity> specification = new Specification<NotificationEntity>() {
			@Override
			public Predicate toPredicate(Root<NotificationEntity> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("id") != null) {
					Integer id = Integer.parseInt(params.get("id").toString());
					predicates.add(criteriaBuilder.equal(root.get("id"), id));
				}
				if (params.get("title") != null) {
					String title = String.valueOf(params.get("title"));
					predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
				}
				if (params.get("types") != null) {
					Short[] types = (Short[]) params.get("types");
					predicates.add(criteriaBuilder.isTrue(root.get("type").in(types)));
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return notificationJpaRepository.count(specification);
	}

	public Page<NotificationEntity> filter(Map<String, Object> params) {

		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());

		PageRequest pageable = new PageRequest(page - 1, size,
				new Sort(new Order(Direction.fromString(String.valueOf(params.getOrDefault("direction", "DESC"))),
						"createDate")));

		Specification<NotificationEntity> specification = new Specification<NotificationEntity>() {
			@Override
			public Predicate toPredicate(Root<NotificationEntity> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (params.get("id") != null) {
					Integer id = Integer.parseInt(params.get("id").toString());
					predicates.add(criteriaBuilder.equal(root.get("id"), id));
				}
				if (params.get("title") != null) {
					String title = String.valueOf(params.get("title"));
					predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
				}
				if (params.get("types") != null) {
					Short[] types = (Short[]) params.get("types");
					predicates.add(criteriaBuilder.isTrue(root.get("type").in(types)));
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return notificationJpaRepository.findAll(specification, pageable);
	}
	
	

	@Override
	public void createNotification(UserEntity user, String identity, String title, String message, Integer pageId,
			Short type, Integer counselorId) {
		try {
			if (user != null) {
				if (((user.getReservationNotification() == 1) && ((type == 1) || (type == 5) || (type == 6) || (type == 7) || (type == 8))) || ((user.getMessageNotification() == 1) && (type == 2))
						|| ((user.getEventNoticeNotification() == 1) && (type == 4)) || ((user.getIniquiryNotification() == 1) && (type == 3) )) {
					Notification n = new Notification();
					n.setUserId(user.getId());
					n.setTitle(title);
					n.setContent(message);
					n.setType((short) 1);
					n.setCreateDate(new Date());
					n.setUpdateDate(new Date());
					n.setTypePush(type);
					JSONObject json = new JSONObject();
					json.put("id", pageId);
					json.put("counselorId", counselorId);
					n.setInformation(json.toJSONString());
					n = create(n);
					UserNotification un = new UserNotification();
					un.setCreateDate(new Date());
					un.setIsRead((short) 0);
					un.setNotificationId(n.getId());
					un.setUpdateDate(new Date());
					un.setUserId(user.getId());
					un.setUuid("");
					un = userNotificationService.create(un);
					sendPushServiceImpl.sendTargetOneAndroid(user.getId(), identity, title, message, pageId, type, counselorId, n.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*Send users*/
	@Override
	public void createNotificationUsers(Integer userId, String identity, String title, String message, Integer pageId,
			Short type, Integer counselorId) {
		try {
			if (userId != null) {
				Notification n = new Notification();
				n.setUserId(userId);
				n.setTitle(title);
				n.setContent(message);
				n.setType((short) 1);
				n.setCreateDate(new Date());
				n.setUpdateDate(new Date());
				n.setTypePush(type);
				JSONObject json = new JSONObject();
				json.put("id", pageId);
				json.put("counselorId", counselorId);
				n.setInformation(json.toJSONString());
				n = create(n);
				UserNotification un = new UserNotification();
				un.setCreateDate(new Date());
				un.setIsRead((short) 0);
				un.setNotificationId(n.getId());
				un.setUpdateDate(new Date());
				un.setUserId(userId);
				un.setUuid("");
				un = userNotificationService.create(un);
				sendPushServiceImpl.sendTargetOneAndroid(userId, identity, title, message, pageId, type, counselorId, n.getId());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<NotificationEntity> getListNotificationByNotifiId(Integer userId, Integer offSet, Integer defaulElement) {
		try {
			String str_select = "SELECT * ";
			int endLimit = offSet + defaulElement;
			String str_from = " FROM "
					+ " (SELECT n.id, n.title, n.content, n.type, n.create_date, n.update_date, un.id as userNotifiId, un.is_read as isReadUserNotifiId, n.information, n.type_push "
					+ " FROM notification n INNER JOIN user_notification un on n.id = un.notification_id where un.user_id = "+userId+" and n.type = 1 "
					+ " UNION "
					+ " SELECT n1.id, n1.title, n1.content, n1.type, n1.create_date, n1.update_date, un1.id as userNotifiId, un1.is_read as isReadUserNotifiId, n1.information, n1.type_push "
					+ " FROM notification n1 left join user_notification un1 on n1.id = un1.notification_id where n1.type = 2) AS notificationAll "
					+ " ORDER BY notificationAll.create_date DESC limit "+0+"," + endLimit;
			Query q = em.createNativeQuery(str_select + str_from);
			@SuppressWarnings("unchecked")
			List<Object[]> rss = q.getResultList();
			List<NotificationEntity> list = new ArrayList<>();
			for (int i = 0; i < rss.size(); i++) {
				NotificationEntity n = new NotificationEntity();
				n.setId((Integer) (rss.get(i))[0]);
				n.setTitle((String) (rss.get(i))[1]);
				n.setContent((String) (rss.get(i))[2]);
				n.setType((Short) (rss.get(i))[3]);
				n.setCreateDate((Date)(rss.get(i))[4]);
				n.setUpdateDate((Date) (rss.get(i))[5]);
				n.setInformation((String) (rss.get(i))[8]);
				n.setTypePush((Short)(rss.get(i))[9]);
				/*1:true - 0/false*/
				Short typeData = (Short) (rss.get(i))[3];
				if (typeData == 1) {
					Short isReadUserNotifiId = (Short) (rss.get(i))[7];
					if (isReadUserNotifiId == null) {
						n.setRead(false);
					} else if (isReadUserNotifiId == 0 ) {
						n.setRead(false);
					} else {
						n.setRead(true);
					}
				} else {
					Integer userNotifiId = (Integer) (rss.get(i))[6];
					if (userNotifiId == null) {
						n.setRead(false);
					} else {
						n.setRead(true);
					}
				}
				
				list.add(n);
			}
			
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer countListNotificationByNotifiId(Integer userId) {
		try {
			String sql = "SELECT COUNT(*) FROM "
					+ " (SELECT n.id, n.title, n.content, n.type, n.create_date, n.update_date, un.id as userNotifiId, un.is_read as isReadUserNotifiId, n.information, n.type_push "
					+ " FROM notification n INNER JOIN user_notification un on n.id = un.notification_id where un.user_id = "+userId+" and n.type = 1 "
					+ " UNION "
					+ " SELECT n1.id, n1.title, n1.content, n1.type, n1.create_date, n1.update_date, un1.id as userNotifiId, un1.is_read as isReadUserNotifiId, n1.information, n1.type_push "
					+ " FROM notification n1 left join user_notification un1 on n1.id = un1.notification_id where n1.type = 2) AS notificationAll ";
			
			Query q = em.createNativeQuery(sql);
			Integer totalPublic = ((BigInteger) q.getSingleResult()).intValue();
			
			/*			
			 * String str_count_public = "SELECT COUNT(*) FROM notification n WHERE n.type = 2 ";
			String str_count = "SELECT COUNT(*) FROM notification n WHERE n.type = 1 and n.user_id = "+userId+" ";
			Query q = em.createNativeQuery(str_count_public);
			Integer totalPublic = ((BigInteger) q.getSingleResult()).intValue();
			Query q2 = em.createNativeQuery(str_count);
			Integer totalNotPublic = ((BigInteger) q2.getSingleResult()).intValue();
			*/
			return totalPublic;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public NotificationEntity findByNotifiId(Integer notifiId) {
		return notificationJpaRepository.findByNotifiId(notifiId);
	}
	
	
	@Override
	public Integer countNewestNotificationByNotifiId(Integer userId) {
		try {
			String str_select = " SELECT n.id, n.title, n.content, n.type, n.create_date, n.update_date, un.id as userNotifiId, un.is_read as isReadUserNotifiId, n.information, n.type_push "
							+ " FROM notification n INNER JOIN user_notification un on n.id = un.notification_id where un.user_id = "+userId+" and n.type = 1 UNION "
							+ " SELECT n1.id, n1.title, n1.content, n1.type, n1.create_date, n1.update_date, un1.id as userNotifiId, un1.is_read as isReadUserNotifiId, n1.information, n1.type_push "
							+ " FROM notification n1 INNER join user_notification un1 on n1.id = un1.notification_id where n1.type = 2";
			
			Query q = em.createNativeQuery(str_select);
			@SuppressWarnings("unchecked")
			List<Object[]> rss = q.getResultList();
			int count = 0;
			for (int i = 0; i < rss.size(); i++) {
				/*1:true - 0/false*/
				Short typeData = (Short) (rss.get(i))[3];
				if (typeData == 1) {
					Short isReadUserNotifiId = (Short) (rss.get(i))[7];
					if (isReadUserNotifiId == null) {
						count ++ ;
					} else if (isReadUserNotifiId == 0 ) {
						count ++ ;
					}
				} else {
					Integer userNotifiId = (Integer) (rss.get(i))[6];
					if (userNotifiId == null) {
						count ++ ;
					}
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Notification createByPush(PushNotificationEntity p) {
		try {
			Notification n = new Notification();
			n.setPushNotificationId(p.getId());
			n.setContent(p.getContent());
			n.setCreateDate(new Date());
			JSONObject json = new JSONObject();
			json.put("id", p.getLandingPageId());
			json.put("counselorId", null);
			n.setInformation(json.toJSONString());
			n.setOsType(p.getOsType());
			n.setTitle(p.getTitle());
			n.setType(p.getTypeSend());
			n.setTypePush(p.getTypePush());
			n.setUpdateDate(new Date());
			n.setUserId(p.getUser().getId());
			return create(n);
		} catch (Exception e) {
			return null;
		}
		
	}

	

}
