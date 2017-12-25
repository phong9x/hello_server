/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.trams.hello.bean.Message;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.UserReceiveMessage;
import org.trams.hello.bean.jpa.MessageEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.web.admin.MessageCounselor;
import org.trams.hello.bean.web.counselor.InboxMessages;
import org.trams.hello.bean.web.counselor.SendMessages;
import org.trams.hello.business.service.MessageService;
import org.trams.hello.business.service.UserReceiveMessageService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.mapping.MessageServiceMapper;
import org.trams.hello.data.repository.jpa.MessageJpaRepository;
import org.trams.hello.data.repository.jpa.UserReceiveMessageJpaRepository;
import org.trams.hello.web.common.utils.DataUtils;
/**
 * Implementation of MessageService
 */
@Component
@Transactional
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageJpaRepository messageJpaRepository;
	@Resource
	private UserService userService;
	@Resource
	private UserReceiveMessageJpaRepository userReceiveMessageJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private MessageServiceMapper messageServiceMapper;
	
	@Autowired
	private EntityManager em;
	
	@Resource
	private UserReceiveMessageService userReceiveMessageService;
	
	@Override
	public Message findById(Integer id) {
		MessageEntity messageEntity = messageJpaRepository.findOne(id);
		return messageServiceMapper.mapMessageEntityToMessage(messageEntity);
	}

	@Override
	public Page<MessageEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return messageJpaRepository.findAll(request);
	}

	@Override
	public List<Message> findAll() {
		Iterable<MessageEntity> entities = messageJpaRepository.findAll();
		List<Message> beans = new ArrayList<Message>();
		for(MessageEntity messageEntity : entities) {
			beans.add(messageServiceMapper.mapMessageEntityToMessage(messageEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = messageJpaRepository.count();
		return count;
	}

	@Override
	public Message save(Message message) {
		return update(message) ;
	}

	@Override
	public Message create(Message message) {
		MessageEntity messageEntity = new MessageEntity();
		messageServiceMapper.mapMessageToMessageEntity(message, messageEntity);
		MessageEntity messageEntitySaved = messageJpaRepository.save(messageEntity);
		
		return messageServiceMapper.mapMessageEntityToMessage(messageEntitySaved);
	}

	@Override
	public Message update(Message message) {
		MessageEntity messageEntity = messageJpaRepository.findOne(message.getId());
		messageServiceMapper.mapMessageToMessageEntity(message, messageEntity);
		MessageEntity messageEntitySaved = messageJpaRepository.save(messageEntity);
		return messageServiceMapper.mapMessageEntityToMessage(messageEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		messageJpaRepository.delete(id);
	}

	public MessageJpaRepository getMessageJpaRepository() {
		return messageJpaRepository;
	}

	public void setMessageJpaRepository(MessageJpaRepository messageJpaRepository) {
		this.messageJpaRepository = messageJpaRepository;
	}

	public MessageServiceMapper getMessageServiceMapper() {
		return messageServiceMapper;
	}

	public void setMessageServiceMapper(MessageServiceMapper messageServiceMapper) {
		this.messageServiceMapper = messageServiceMapper;
	}

	@Override
	public Page<MessageEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return messageJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Message send(Map<String, Object> params) {

		int toId 		= Integer.parseInt(params.get("toId").toString());
		int fromId 		= Integer.parseInt(params.get("fromId").toString());
		int type 		= Integer.parseInt(params.get("type").toString());
		String content 	= (String) params.get("content");
		Date now 		= Calendar.getInstance().getTime();

		UserEntity sendUserEntity = new UserEntity();
		sendUserEntity.setId(fromId);

		UserEntity receiveUserEntity = new UserEntity();
		receiveUserEntity.setId(toId);

		MessageEntity message = new MessageEntity();

		message.setContent(content);
		message.setSendDate(now);
		message.setRecieveDate(now);
		message.setUpdateDate(now);
		message.setCreateDate(now);
		message.setTypeUser(type);
		message.setUser(sendUserEntity);

		MessageEntity messageEntitySaved = messageJpaRepository.save(message);

		UserReceiveMessage userReceiveMessage = new UserReceiveMessage();
		userReceiveMessage.setCreateDate(now);
		userReceiveMessage.setCreateDate(now);
		userReceiveMessage.setUpdateDate(now);
		userReceiveMessage.setStatus((short) 0);
		userReceiveMessage.setMessageId(messageEntitySaved.getId());
		userReceiveMessage.setUserId(toId);

		userReceiveMessageService.create(userReceiveMessage);

		return messageServiceMapper.mapMessageEntityToMessage(messageEntitySaved);
	}

	@Override
	public MessageEntity findByMessageId(Integer id) {
		return messageJpaRepository.findByMessageId(id);
	}

	@Override
	public PageCustom<InboxMessages> filterInboxMessagesOfCounselor(String start_search, String end_search, String keyword,
			Integer counselorId, Integer page, Integer size, String orderBy) {
		try {
			List<InboxMessages> list = new ArrayList<>();
			String str_select = "SELECT ur.id AS urd, m.id AS mId, u.id AS uId, u.email, u.fullname, m.content, ur.create_date ";
			String str_count = "SELECT count(*) ";
			String str_from = " FROM user_receive_message ur "
					+ " INNER JOIN message m ON ur.message_id = m.id "
					+ " INNER JOIN user u ON m.send_user_id = u.id ";
			
			String str_where = " WHERE ur.user_id = "+counselorId+" "
					+ " and DATE(ur.create_date) >= '"+start_search+"' and DATE(ur.create_date) <= '"+end_search+"' ";
			
			String str_wh_keyword = "";
			if(keyword != null) {
				String key = "%"+keyword+"%";
				str_wh_keyword = " and (u.fullname like '"+key+"' or m.content like '"+key+"') ";
			}
			String str_orderby = " ";
			if(orderBy.equals("create_date_mess")){
				str_orderby = " ORDER BY ur.create_date DESC " ;
			} else {
				str_orderby = " ORDER BY u.fullname ASC " ;
			}
			
			Query q = em.createNativeQuery(str_select + str_from + str_where + str_wh_keyword + str_orderby + " limit " + (page-1)*size  + ", " + size);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			
			for (Object[] i : rss) {
				InboxMessages iMess = new InboxMessages();
				iMess.setId((Integer) i[0]);
				iMess.setMessageId((Integer) i[1]);
				iMess.setUserId((Integer) i[2]);
				iMess.setUserEmail((String) i[3]);
				iMess.setUserName((String) i[4]);
				iMess.setContent((String) i[5]);
				iMess.setReceivedDate((Date) i[6]);
				
				list.add(iMess);
			}
			
			Query c = em.createNativeQuery(str_count + str_from + str_where + str_wh_keyword + str_orderby);
			Long totalCount = ((Number) c.getSingleResult()).longValue();
			PageCustom<InboxMessages> pageCustom = new PageCustom<>(list, totalCount, page, size);
			
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageCustom<SendMessages> filterSendMessagesOfCounselor(String start_search, String end_search,
			String keyword, Integer counselorId, Integer page, Integer size, String orderBy) {
		try {
			List<SendMessages> list = new ArrayList<>();
			String str_select = "SELECT ur.id AS urd, m.id AS mId, u.id AS uId, u.email, u.fullname, m.content, m.send_date ";
			String str_count = "SELECT count(*) ";
			String str_from = " FROM user_receive_message ur "
					+ " INNER JOIN user u ON ur.user_id = u.id "
					+ " INNER JOIN message m ON ur.message_id = m.id ";
			
			String str_where = " WHERE m.send_user_id = "+counselorId+" "
					+ " and DATE(m.send_date) >= '"+start_search+"' and DATE(m.send_date) <= '"+end_search+"' ";
			
			String str_wh_keyword = "";
			if(keyword != null) {
				String key = "%"+keyword+"%";
				str_wh_keyword = " and (u.fullname like '"+key+"' or m.content like '"+key+"') ";
			}
			String str_orderby = " ";
			if(orderBy.equals("create_date_mess")){
				str_orderby = " ORDER BY ur.create_date DESC " ;
			} else {
				str_orderby = " ORDER BY u.fullname ASC " ;
			}
			
			Query q = em.createNativeQuery(str_select + str_from + str_where + str_wh_keyword + str_orderby + " limit " + (page-1)*size  + ", " + size);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			
			for (Object[] i : rss) {
				SendMessages sMess = new SendMessages();
				sMess.setId((Integer) i[0]);
				sMess.setMessageId((Integer) i[1]);
				sMess.setUserId((Integer) i[2]);
				sMess.setUserEmail((String) i[3]);
				sMess.setUserName((String) i[4]);
				sMess.setContent((String) i[5]);
				sMess.setSendDate((Date) i[6]);
				
				list.add(sMess);
			}
			
			Query c = em.createNativeQuery(str_count + str_from + str_where + str_wh_keyword + str_orderby);
			Long totalCount = ((Number) c.getSingleResult()).longValue();
			PageCustom<SendMessages> pageCustom = new PageCustom<>(list, totalCount, page, size);
			
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PageCustom<MessageCounselor> listPagingByMessIdAdmin(String start_search, String end_search, String keyword,
			String type_search, String orderBy, Integer page, Integer size) {
		try {
			String sql = "SELECT m.id, admin.fullname, m.create_date, m.content, GROUP_CONCAT(ur.id) AS userReceiveMessageId, "
					+ " GROUP_CONCAT(u.fullname) AS userReceiveFullName, ur.id as firstUserReceiveMessageId, u.fullname AS firstUserRecName, COUNT(ur.id) "
					+ " FROM message m INNER JOIN user_receive_message ur ON m.id = ur.message_id "
					+ " INNER JOIN user u ON u.id = ur.user_id "
					+ " INNER JOIN user admin ON admin.id = m.send_user_id ";
			
			String sql_where = "WHERE m.type_user = 2 ";
			String sql_groupBy = "  GROUP BY m.id " ;
			
			if (start_search != null) {
				sql_where = sql_where + " and DATE(m.create_date) >= '"+start_search+"' ";
			} 
			if (end_search != null) {
				sql_where = sql_where + " and DATE(m.create_date) <= '"+end_search+"' ";
			}
			
			String key = "%"+keyword+"%";
			if(type_search.equals("adminNameSearch")) {
				sql_where = sql_where + " and admin.fullname LIKE '"+key+"' ";
			} else if (type_search.equals("recipientSearch")) {
				sql_groupBy = sql_groupBy + " HAVING userReceiveFullName LIKE '"+key+"' " ;
			} else {
				sql_where = sql_where + " and m.content LIKE '"+key+"' ";
			}
			
			if (orderBy.equals("createDateOrder")){
				sql_groupBy = sql_groupBy + " ORDER BY m.create_date DESC ";
			} else if (orderBy.equals("idOrder")) {
				sql_groupBy = sql_groupBy + " ORDER BY m.send_user_id ASC ";
			} else {
				sql_groupBy = sql_groupBy + " ORDER BY firstUserReceiveMessageId ASC ";
			}
			
			Query q = em.createNativeQuery(sql + sql_where + sql_groupBy + " limit " + (page-1)*size  + ", " + size);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			List<MessageCounselor> list = new ArrayList<>();
			for (Object[] i : rss) {
				MessageCounselor m = new MessageCounselor();
				m.setId((Integer) i[0]);
				m.setAdminName((String) i[1]);
				m.setCreateDate((Date) i[2]);
				m.setContent((String) i[3]);
				m.setUserReceiveMessageId((String) i[4]);
				m.setUserReceiveFullName((String) i[5]);
				m.setFirstUserReceiveMessageId((Integer) i[6]);
				m.setFirstUserRecName((String) i[7]);
				m.setTotalUserRec(((BigInteger) i[8]).intValue());
				list.add(m);
			}
			
			String sql_order = "";
			if (orderBy.equals("createDateOrder")){
				sql_order = sql_order + " ORDER BY m.create_date DESC ";
			} else if (orderBy.equals("idOrder")) {
				sql_order = sql_order + " ORDER BY m.send_user_id ASC ";
			} else {
				sql_order = sql_order + " ORDER BY ur.id ASC ";
			}
			
			Query c = em.createNativeQuery("SELECT COUNT( DISTINCT m.id) "
					+ " FROM message m INNER JOIN user_receive_message ur ON m.id = ur.message_id "
					+ " INNER JOIN user u ON u.id = ur.user_id "
					+ " INNER JOIN user admin ON admin.id = m.send_user_id " + sql_where + sql_order);
			Long totalCount = ((Number) c.getSingleResult()).longValue();
			PageCustom<MessageCounselor> pageCustom = new PageCustom<>(list, totalCount, page, size);
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MessageCounselor getByMessIdAdmin(Integer messageId) {
		try {
			String sql = "SELECT m.id, admin.fullname, m.create_date, m.content, GROUP_CONCAT(ur.id) AS userReceiveMessageId, "
					+ " GROUP_CONCAT(u.fullname) AS userReceiveFullName, ur.id as firstUserReceiveMessageId, u.fullname AS firstUserRecName, COUNT(ur.id) "
					+ " FROM message m INNER JOIN user_receive_message ur ON m.id = ur.message_id "
					+ " INNER JOIN user u ON u.id = ur.user_id "
					+ " INNER JOIN user admin ON admin.id = m.send_user_id WHERE m.id = "+messageId+" ";
			
			Query q = em.createNativeQuery(sql);
			Object[] i = (Object[]) q.getSingleResult();
			MessageCounselor m = new MessageCounselor();
			m.setId((Integer) i[0]);
			m.setAdminName((String) i[1]);
			m.setCreateDate((Date) i[2]);
			m.setContent((String) i[3]);
			m.setUserReceiveMessageId((String) i[4]);
			m.setUserReceiveFullName((String) i[5]);
			m.setFirstUserReceiveMessageId((Integer) i[6]);
			m.setFirstUserRecName((String) i[7]);
			m.setTotalUserRec(((BigInteger) i[8]).intValue());
			return m;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public PageCustom<MessageCounselor> getListFromAdmin(String start_search, String end_search, String keyword,
			String type_search, String orderBy, Integer page, Integer size) {
		try {
			String select = "SELECT AA.id, "
								 + "AA.fullname, "
								 + "AA.send_date, "
								 + "CASE AA.receivedUserCount "
								 	+ "WHEN 1 THEN AA.receivedUser "
								 	+ "ELSE CONCAT(AA.receivedUser, ' 외 ', AA.receivedUserCount - 1, '명') "
								 + "END AS received_user, "
								 + "AA.content ";
			String from = "FROM (SELECT M.id, "
									 + "U.fullname, "
									 + "M.send_date, "
									 + "M.content, "
									 + "(SELECT RU.fullname "
									    + "FROM user_receive_message URM, user RU "
									    + "WHERE URM.message_id = M.id "
									      + "AND URM.user_id = RU.id "
									    + "ORDER BY URM.id ASC "
									    + "LIMIT 1) AS receivedUser, "
									 + "(SELECT COUNT(*) "
									    + "FROM user_receive_message URM, user RU "
									    + "WHERE URM.message_id = M.id "
									    + "AND URM.user_id = RU.id "
									    + ") AS receivedUserCount, "
									 + "(SELECT GROUP_CONCAT(RU.fullname) "
									    + "FROM user_receive_message URM, user RU "
									    + "WHERE URM.message_id = M.id "
									      + "AND URM.user_id = RU.id "
									  + ") AS receivedUserName "
								+ "FROM message M, user U "
								+ "WHERE M.send_user_id = U.id "
								  + "AND U.role_id = 8 "
								+ ") AA ";
			String where = "WHERE 1=1 ";
			
			if (start_search != null) {
				where += "AND DATE(AA.send_date) >= '"+start_search+"' ";
			} 
			if (end_search != null) {
				where += "AND DATE(AA.send_date) <= '"+end_search+"' ";
			}
			
			if(keyword != null && keyword != ""){
				String key = "%"+keyword+"%";
				if(type_search.equals("adminNameSearch")) {
					where += "AND AA.fullname LIKE '"+key+"' ";
				} else if (type_search.equals("recipientSearch")) {
					where += " AND AA.receivedUserName LIKE '"+key+"' ";
				} else {
					where += "AND AA.content LIKE '"+key+"' ";
				}
			}
			
			String order = "ORDER BY ";
			
			if (orderBy.equals("idRecipientOrder")){
				order += "received_user ASC ";
			} else if (orderBy.equals("idOrder")) {
				order += "fullname ASC ";
			} else {
				order += "send_date DESC ";
			}
			
			//limit
			Integer offset = DataUtils.getOffset(page, size);
			String limit = " LIMIT " + offset + ", " + size;
			
			String queryString = select + from + where + order + limit;
			Query q = em.createNativeQuery(queryString);
			@SuppressWarnings("unchecked")
			List<Object[] > rss = q.getResultList();
			List<MessageCounselor> list = new ArrayList<>();
			for (Object[] i : rss) {
				MessageCounselor m = new MessageCounselor();
				m.setId((Integer) i[0]);
				m.setAdminName((String) i[1]);
				m.setCreateDate((Date) i[2]);
				m.setFirstUserRecName((String) i[3]);
				m.setContent((String) i[4]);
				list.add(m);
			}
			
			String countQueryString = "SELECT COUNT(*) "
					+ "FROM ("
					+ select
					+ from
					+ where
					+ ") AA";
			
			Query c = em.createNativeQuery(countQueryString);
			Long totalCount = ((Number) c.getSingleResult()).longValue();
			PageCustom<MessageCounselor> pageCustom = new PageCustom<>(list, totalCount, page, size);
			return pageCustom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
