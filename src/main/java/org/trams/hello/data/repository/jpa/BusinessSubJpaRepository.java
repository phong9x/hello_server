package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.trams.hello.bean.jpa.BusinessSubEntity;

/**
 * Repository : BusinessSub.
 */
public interface BusinessSubJpaRepository extends PagingAndSortingRepository<BusinessSubEntity, Integer> {
	@Query(value =
			"SELECT BS.id, BS.manage_year, BS.business_sub_name, "
				+ "(SELECT COUNT(*) FROM user_business WHERE business_sub_id = BS.id) as user_count, "
				+ "V.used, BS.status, V.fee "+
			  "FROM business_sub BS, voucher V "+
	         "WHERE BS.business_id = :businessSubId "+
			   "AND V.business_sub_id = BS.id "+
			 "ORDER BY BS.id DESC " +
			 "LIMIT :offset, :size ", 
			nativeQuery = true
	)
	List<Object[]> findForlist(@Param("businessSubId") Integer businessId, @Param("offset") Integer offset, @Param("size") Integer size);
	
	@Query(value = 
			"SELECT COUNT(BS.id) " +
			  "FROM business_sub BS, voucher V "+
	         "WHERE BS.business_id = :businessSubId "+
			   "AND V.business_sub_id = BS.id ",
			nativeQuery = true
	)
	Long countForList(@Param("businessSubId") Integer businessId);
	
	
	List<BusinessSubEntity> findByBusinessId(Integer businessId);
	
	List<BusinessSubEntity> findByBusinessIdAndManageYear(Integer businessId, Integer manageYear);
	
	@Query(
			"Select u From BusinessSubEntity u Where u.business.businessType = ?1 group by u.manageYear"
			)
	List<BusinessSubEntity> listPagingByType(Short businessType);
	
	@Query(
			"SELECT u FROM BusinessSubEntity u "
			+ "WHERE u.businessSubNo = ?1 "
			+ "AND u.status = 1 "
			+ "AND u.contractStartDate <= NOW() "
			+ "AND u.contractEndDate >= NOW() "
			)
	BusinessSubEntity findByBusinessSubNo(String businessSubNo);
	
	@Query(
			"Select u From BusinessSubEntity u Where u.manageYear = ?1 and u.business.businessType = ?2"
			)
	List<BusinessSubEntity> listPagingByManagerYearAndBusinessType(Integer manageYear, Short businessType);
	
	
	@Query("Select bs From BusinessSubEntity bs Where bs.business.id = ?1 and bs.status = 1 ")
	List<BusinessSubEntity> getListBusinessSubByBusinessId(Integer businessId);
	
	@Query(value = 
			"SELECT BS.manage_year "
			+ "FROM business_sub BS,"
				 + "business B "
			+ "WHERE BS.business_id = B.id "
			+ "AND B.business_type = ?1 "
			+ "GROUP BY BS.manage_year"
			, nativeQuery = true
	)
	List<Integer> getListManageYearByType(Short businessType);
}
