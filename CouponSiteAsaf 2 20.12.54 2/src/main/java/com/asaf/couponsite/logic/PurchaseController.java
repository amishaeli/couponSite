package com.asaf.couponsite.logic;


import com.asaf.couponsite.dao.ICompanyDao;
import com.asaf.couponsite.dao.ICouponDao;
import com.asaf.couponsite.dao.IPurchaseDao;
import com.asaf.couponsite.data.LoggedInUserData;
import com.asaf.couponsite.data.PurchaseData;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.entities.Customer;
import com.asaf.couponsite.entities.Purchase;
import com.asaf.couponsite.entities.User;
import com.asaf.couponsite.enums.ErrorType;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;


@Controller
public class PurchaseController {

	@Autowired
	private IPurchaseDao purcahseDao;
	@Autowired
	private ICompanyDao companyDao;
	@Autowired
	private ICouponDao couponDao;

	@Autowired
	private CouponController couponController;

	@Autowired
	private CustomerController customerController;

	public void createPurchase(PurchaseData purchaseData, LoggedInUserData userData) throws Exception {

		validatePurchase(purchaseData);

		Coupon coupon = couponController.getCouponById(purchaseData.getCouponId());
		long couponId = coupon.getId();

		int updatedCouponAmount = (coupon.getAmount()) - (purchaseData.getAmount());
		couponController.reduceCouponAmountByPurchaseAmount(couponId,updatedCouponAmount);

		Customer customer = customerController.getCustomerById(purchaseData.getCustomerId());
		Purchase userPurchase = new Purchase(purchaseData.getAmount(), coupon, customer);
		purcahseDao.save(userPurchase);
	}

//	public List<Coupon> getPurchasesByCouponId (long couponId) throws ApplicationException {
////		List<Coupon> purchasesByCouponIdList = (List<Coupon>) couponDao.findByCouponId(couponId);
//		List<Coupon> purchasesByCouponIdList = purcahseDao.findByCouponId(couponId);
//		if (purchasesByCouponIdList.size() == 0) {
//			new Exception("There are no Purchases with this couponId: " + couponId);
//		}
//		return purchasesByCouponIdList;

//		List<Customer> purchasesByCouponId = (List<Customer>) purcahseDao.findByCouponId(couponId);
//		return purchasesByCouponId;
//		return ;
//	}

//	public List<Coupon> getPurchasesByCustomerId(long customerId) throws ApplicationException {
//
//		List<Coupon> purchasesByCustomerId = (List<Coupon>) purcahseDao.findByCustomerId(customerId);
//		return purchasesByCustomerId;
//	}

	public void deletePurchase (long purchaseId) throws ApplicationException {

		Purchase purchase = purcahseDao.findById(purchaseId).get();
		purcahseDao.delete(purchase);
	}

	public List<Purchase> getAllPurchases() throws ApplicationException {

		List<Purchase> purchases =  (List<Purchase>) purcahseDao.findAll();
		return purchases;
	}

	private void validatePurchase(PurchaseData purchase) throws Exception {

		if (purchase == null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
					+ "Error in purchaseController.validatePurchase(), You must insert details.");
		}

		if (couponController.getCouponById(purchase.getCouponId()) == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
					+ "Error in purchaseController.validatePurchase(),  The coupon you are tempting to buy does'nt exist.");
		}

		if (couponController.getCouponById(purchase.getCouponId()) == null) {
			throw new ApplicationException(ErrorType.ID_DOES_NOT_EXIST, DateUtils.getCurrentDateAndTime()
					+ "Error in purchaseController.validatePurchase(),  The coupon you are tempting to buy does'nt exist.");
		}

		Coupon coupon = couponController.getCouponById(purchase.getCouponId());
		int couponAmount = coupon.getAmount();


		if (purchase.getAmount() > couponAmount) {
			throw new ApplicationException(ErrorType.NOT_ENOUGH_COUPONS_LEFT, DateUtils.getCurrentDateAndTime()
					+ "Error in purchaseController.validatePurchase(), Not enough coupons left to purchase the amount requested.");
		}
	}

}

