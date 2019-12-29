package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.Coupon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ICouponDao extends CrudRepository<Coupon, Long> {
	List<Coupon> findByTitle(String title);
	boolean existsByTitle(String title);

	@Query("SELECT c FROM Coupon c where c.id=:id")
	Coupon findByCouponId(@Param("id") Long id);

	@Query("SELECT c FROM Coupon c WHERE c.description=:description")
	List<Coupon> fetchCouponsByDescription(@Param("description") String content);

	@Query("from Coupon c where c.createDate > :createDate")
	public List<Coupon> getAllCouponsSinceCreateDate(@Param("createDate") Date createDate);

	@Query("from Coupon c where c.expirationDate > :expirationDate")
	public List<Coupon> getCouponByExpirationDate(@Param("expirationDate") Date expirationDate);

	@Query("from Coupon c where c.createDate BETWEEN :startDate AND :endDate")
	public List<Coupon> getAllCouponsBetweenCreateDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query("DELETE from Coupon c where c.expirationDate > date")
	public	void deleteAllExpiredCoupons(@Param("date") Date expirationDate);

	@Transactional
	@Modifying
	@Query("UPDATE Coupon c SET c.amount= :amount WHERE ID= :couponId")
	void updateCouponAmountByPurchaseAmount(@Param("couponId") long couponId, @Param("amount") int amount);

	@Query("FROM Coupon c WHERE c.price <= :couponPrice")
	List<Coupon> findByPriceLessThan (@Param("couponPrice") float couponPrice);

//	@Query("SELECT c FROM Coupon c where c.companyId=:companyId")
//    Coupon getCouponByCompanyId(@Param("companyId")long companyId);

}
