/*
 * Created on 23 thg 11 2016 ( Time 13:38:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.trams.hello.bean.Comment;
import org.trams.hello.bean.jpa.CommentEntity;
import org.trams.hello.business.service.CommentService;
import org.trams.hello.business.service.mapping.CommentServiceMapper;
import org.trams.hello.data.repository.jpa.CommentJpaRepository;
/**
 * Implementation of CommentService
 */
@Component
@Transactional
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentJpaRepository commentJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private CommentServiceMapper commentServiceMapper;
	
	@Override
	public Comment findById(Integer id) {
		CommentEntity commentEntity = commentJpaRepository.findOne(id);
		return commentServiceMapper.mapCommentEntityToComment(commentEntity);
	}

	@Override
	public Page<CommentEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return commentJpaRepository.findAll(request);
	}

	@Override
	public List<Comment> findAll() {
		Iterable<CommentEntity> entities = commentJpaRepository.findAll();
		List<Comment> beans = new ArrayList<Comment>();
		for(CommentEntity commentEntity : entities) {
			beans.add(commentServiceMapper.mapCommentEntityToComment(commentEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = commentJpaRepository.count();
		return count;
	}

	@Override
	public Comment save(Comment comment) {
		return update(comment) ;
	}

	@Override
	public Comment create(Comment comment) {
/*
		CommentEntity commentEntity = commentJpaRepository.findOne(comment.getId());
		if( commentEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
*/
		CommentEntity commentEntity = new CommentEntity();
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public Comment update(Comment comment) {
		CommentEntity commentEntity = commentJpaRepository.findOne(comment.getId());
		commentServiceMapper.mapCommentToCommentEntity(comment, commentEntity);
		CommentEntity commentEntitySaved = commentJpaRepository.save(commentEntity);
		return commentServiceMapper.mapCommentEntityToComment(commentEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		commentJpaRepository.delete(id);
	}

	public CommentJpaRepository getCommentJpaRepository() {
		return commentJpaRepository;
	}

	public void setCommentJpaRepository(CommentJpaRepository commentJpaRepository) {
		this.commentJpaRepository = commentJpaRepository;
	}

	public CommentServiceMapper getCommentServiceMapper() {
		return commentServiceMapper;
	}

	public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
		this.commentServiceMapper = commentServiceMapper;
	}

	@Override
	public Page<CommentEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return commentJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Page<CommentEntity> listPagingBy_CounsolorId(Integer counsolorId, Integer page,Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return commentJpaRepository.listPagingBy_CounsolorId(counsolorId, pageable);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Page<CommentEntity> filter(Map<String, Object> params) {
		int page = Integer.parseInt(params.get("page").toString());
		int size = Integer.parseInt(params.get("size").toString());

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(new Order(Direction.DESC, "createDate")));

		Specification<CommentEntity> specification = new Specification<CommentEntity>() {
			@Override
			public Predicate toPredicate(Root<CommentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();

				if (params.get("id") != null) {
					Integer id = Integer.parseInt(params.get("id").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("id"), id)
					);
				}
				if (params.get("counselorId") != null) {
					Integer counselorId = Integer.parseInt(params.get("counselorId").toString());
					predicates.add(
							criteriaBuilder.equal(root.get("counselorId"), counselorId)
					);
				}
				if (params.get("userId") != null) {
					Integer userId = Integer.parseInt(params.get("userId").toString());
					predicates.add(
							criteriaBuilder.equal(root.join("user").get("id"), userId)
					);
				}

				if (predicates.isEmpty()) {
					return criteriaBuilder.isNotNull(root.get("id"));
				} else {
					return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
				}
			}
		};

		return commentJpaRepository.findAll(specification, pageable);
	}

}