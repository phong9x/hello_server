package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.PolicyEntity;

/**
 * Repository : Policy.
 */
public interface PolicyJpaRepository extends PagingAndSortingRepository<PolicyEntity, Integer> {
	@Query("Select u From PolicyEntity u ")
	Page<PolicyEntity> listPaging(Pageable pageable);
	
	@Query("Select u From PolicyEntity u where u.isShow =1 and u.typePolicy = ?1 ")
	List<PolicyEntity> list(Short typePolicy);
	
	@Query("Select u From PolicyEntity u where u.isShow =1 and u.typePolicy = ?1 and u.typeMember = ?2 ")
	PolicyEntity getPoli(Short typePolicy, Short typeMember);
	
	@Query(value="SELECT MAX(vesion) FROM policy p WHERE p.type_policy = ?1 and p.type_member = ?2 ", nativeQuery=true)
	Float maxVersionPolicyByTypePolicy(Short typePolicy, Short typeMember);
	
	@Modifying
	@Query(value="UPDATE policy SET is_show = 0 WHERE type_policy = ?1 and type_member = ?2 ", nativeQuery=true)
	void updateStatusOldVersion(Short typePolicy, Short typeMember);
}
