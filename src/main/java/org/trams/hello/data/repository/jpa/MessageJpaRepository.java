package org.trams.hello.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.MessageEntity;

/**
 * Repository : Message.
 */
public interface MessageJpaRepository extends PagingAndSortingRepository<MessageEntity, Integer> {
	@Query("Select u From MessageEntity u ")
	Page<MessageEntity> listPaging(Pageable pageable);

	@Query("Select u From MessageEntity u where u.id = ?1 ")
	MessageEntity findByMessageId(Integer id);
}
