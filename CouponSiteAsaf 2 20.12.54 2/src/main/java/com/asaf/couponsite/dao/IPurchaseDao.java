package com.asaf.couponsite.dao;


import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.entities.Customer;
import com.asaf.couponsite.entities.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPurchaseDao extends CrudRepository<Purchase, Long> {

//    @Query("FROM Coupon c JOIN Purchase p ON c.id = p.couponId WHERE c.id=:couponId")
////	@Query("FROM Purchase p JOIN Customer c ON c.id = p.customerId WHERE Coupon.id=? :couponId")
//    List<Coupon> findByCouponId(@Param("couponId") long couponId);
//
//	@Query("SELECT c FROM Purchase p JOIN Coupon c ON c.id = p.couponId WHERE p.customerId= :customerId")
////	@Query("FROM Purchase p JOIN Coupon c ON c.id = p.couponId WHERE Customer.id=? :customerId")
//	List<Coupon> findByCustomerId(@Param("customerId") long customerId);





}
