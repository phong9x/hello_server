package org.trams.hello.data.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.VoucherEntity;

/**
 * Repository : Voucher.
 */
public interface VoucherJpaRepository extends PagingAndSortingRepository<VoucherEntity, Integer>,
JpaSpecificationExecutor<VoucherEntity> {
	@Query("Select u From VoucherEntity u ")
	Page<VoucherEntity> listPaging(Pageable pageable);

	@Query("Select u From VoucherEntity u Where u.name like ?1 ")
	Page<VoucherEntity> findByName(String name, Pageable pageable);

	@Query("Select u From VoucherEntity u where u.businessSub.id = ?1 ")
	VoucherEntity findOneByBusinessId(Integer businessId);
	
	@Query("Select u From VoucherEntity u where u.businessSub.id = ?1 ")
	List<VoucherEntity> listByBusinessId(Integer businessId);
	
	@Query("Select u From VoucherEntity u where u.typeVoucher = ?1 ")
	VoucherEntity findByTypeHeart(Short type);
	
	VoucherEntity findByBusinessSubId(Integer businessSubId);
	
	VoucherEntity findByBusinessSubIdAndCounselingTimeType(Integer businessSubId, Short counselingTimeType);
	
	@Query(
			value ="SELECT V.id, B.business_name, BS.manage_year, BS.business_sub_name "
					+ "FROM user_business UB "
						+ "INNER JOIN business_sub BS ON UB.business_sub_id = BS.id "
						+ "INNER JOIN business B ON BS.business_id = B.id "
						+ "INNER JOIN voucher V ON BS.id = V.business_sub_id "
					+ "WHERE UB.user_id = ?1", nativeQuery = true
			)
	List<Object[]> listBussinessVoucherByUserId(Integer userId);
	
	@Query(
			value ="SELECT * "
				+ "FROM voucher "
				+ "WHERE business_sub_id = ?1 "
				+ "AND number > used ", nativeQuery = true
			)
	List<VoucherEntity> findVouchersByBusinessSubId(Integer businessId);
	
	@Query(
			value ="SELECT * "
				+ "FROM voucher "
				+ "WHERE business_sub_id = ?1 "
				, nativeQuery = true
			)
	List<VoucherEntity> findByBusinessId(Integer businessId);
}
