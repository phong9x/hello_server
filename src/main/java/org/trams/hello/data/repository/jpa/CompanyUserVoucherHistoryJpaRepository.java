package org.trams.hello.data.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trams.hello.bean.jpa.CompanyUserVoucherHistoryEntity;

/**
 * Repository : CompanyUserVoucherHistory.
 */
public interface CompanyUserVoucherHistoryJpaRepository extends PagingAndSortingRepository<CompanyUserVoucherHistoryEntity, Integer> {

	@Query(value = "SELECT COUNT(*) "
			+ "FROM company_user_voucher_history "
			+ "WHERE business_sub_id = ?1 "
			+ "AND aimmed_user_id = ?2", nativeQuery = true)
	Long countByBusinessSubIdAndAimmedUserId(Integer businessSubId, String aimmedUserId);

}
