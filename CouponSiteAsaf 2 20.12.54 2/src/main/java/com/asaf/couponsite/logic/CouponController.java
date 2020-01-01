package com.asaf.couponsite.logic;


import com.asaf.couponsite.dao.ICompanyDao;
import com.asaf.couponsite.dao.ICouponDao;
import com.asaf.couponsite.entities.AddCoupon;
import com.asaf.couponsite.entities.Company;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.enums.ErrorType;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;



@Controller
public class CouponController {

	@Autowired
	private ICouponDao couponDao;

	@Autowired
	private ICompanyDao companyDao;
	private Company companyId;


	public void createCoupon(Coupon coupon) throws ApplicationException {

		if (isCouponExistByName(coupon.getTitle())) {
			throw new ApplicationException(ErrorType.COUPON_TITLE_EXIST, DateUtils.getCurrentDateAndTime()
					+ "Error in couponController.createCoupon(), coupon's name already exists.");
		}

		validateCoupon(coupon);

		couponDao.save(coupon);
	}

	public void addCoupon(Coupon coupon, long companyId) throws Exception {

		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);

		couponDao.save(coupon);
	}

	public void addCouponByAdmin(Coupon coupon, String companyName) throws Exception {
	Company company = companyDao.getByName(companyName);
//		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);

		couponDao.save(coupon);
	}

	public void updateCoupon(Coupon coupon, long companyId) throws Exception {

		Coupon originCoupon = getCouponById(coupon.getId());
		String couponOriginName = originCoupon.getTitle();

		if (!coupon.getTitle().equalsIgnoreCase(couponOriginName)) {
			if (isCouponExistByName(coupon.getTitle())) {
				throw new ApplicationException(ErrorType.COUPON_TITLE_EXIST, DateUtils.getCurrentDateAndTime()
						+ "Error in couponController.updateCoupon(), coupon's name already exists.");
			}
		}

		Company company = companyDao.findByCompanyId(companyId);
		coupon.setCompany(company);

		validateCoupon(coupon);

		couponDao.save(coupon);
	}

	public Coupon getCouponById(long id) throws Exception {
		Coupon coupon = couponDao.findByCouponId(id);
		if (coupon == null){
			throw new Exception("Coupon does not exist");
		}
		return coupon;
	}


	public List<Coupon> getCouponsByCompany(long companyId) throws Exception {
		if(companyId <=0){
			List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
			return coupons;
		} else {
			return couponDao.getCouponsByCompanyId(companyId);
		}
	}

//	public Coupon getCouponByCompanyId(long companyId) throws Exception {
//		Coupon coupon = couponDao.getCouponByCompanyId(companyId);
//		if (coupon == null){
//			throw new Exception("Coupon does not exist");
//		}
//		return coupon;
//	}

	public void deleteCoupon(long couponId) throws Exception {

		Coupon coupon = getCouponById(couponId);
		couponDao.delete(coupon);
	}

	public List<Coupon> getAllCoupons() throws ApplicationException {

		List<Coupon> coupons = (List<Coupon>) couponDao.findAll();
		return coupons;
	}

	public boolean isCouponExistByName(String couponName) throws ApplicationException {
		return couponDao.existsByTitle(couponName);
	}

	public void deleteAllExpiredCoupons(Date date) throws ApplicationException {
		couponDao.deleteAllExpiredCoupons(date);
	}

	public void reduceCouponAmountByPurchaseAmount(long couponId, int amount) throws ApplicationException {
		couponDao.updateCouponAmountByPurchaseAmount(couponId, amount);
	}

	public List<Coupon> getCouponsUpToPrice(float couponPrice) throws Exception {
		List<Coupon> coupons = couponDao.findByPriceLessThan(couponPrice);
		if(coupons.size()==0) {
			throw new Exception("There are no coupons with price less than: " + couponPrice);
		}
		return coupons;
	}

	public List<Coupon> getAllCouponsSinceCreateDate(Date createDate) throws Exception {
		List<Coupon> coupons = couponDao.getAllCouponsSinceCreateDate(createDate);
		if (coupons.size() == 0) {
			throw new Exception("There are no coupons with createDate after " + createDate);
		}
		return coupons;
	}

	public List<Coupon> getCouponByExpirationDate(Date expirationDate) throws Exception {
		List<Coupon> coupons = couponDao.getCouponByExpirationDate(expirationDate);
		if (coupons.size() == 0) {
			throw new Exception("There are no coupons with expiration date after " + expirationDate);
		}
		return coupons;
	}

	private void validateCoupon(Coupon coupon) throws ApplicationException {

		if (coupon == null) {
			System.out.println("Null");
			throw new ApplicationException(ErrorType.MUST_INSERT_A_VALUE, DateUtils.getCurrentDateAndTime()
					+ "Error in couponController.validateCoupon(), You must insert a Coupon");		}

		if (coupon.getAmount() <= 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT, DateUtils.getCurrentDateAndTime()
					+ "Error in couponController.createCoupon(), Minimum quantity must be 1.");
		}

		if (coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.INVALID_PRICE, DateUtils.getCurrentDateAndTime()
					+ "Error in couponController.createCoupon(), Coupon's price can't be negetive.");
		}
	}



}
