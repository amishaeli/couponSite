package com.asaf.couponsite.api;


import com.asaf.couponsite.consts.Constants;
import com.asaf.couponsite.data.LoggedInUserData;
import com.asaf.couponsite.entities.AddCoupon;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.CouponController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponApi {
    @Autowired
    private CouponController couponController;

//    @PostMapping
//    public void createCoupon(@RequestBody Coupon coupon) throws ApplicationException {
//        couponController.createCoupon(coupon);
//    }

    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws Exception {
        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
        couponController.addCoupon(coupon, userData.getCompanyId());
    }

    @PostMapping("/byComName")
    public void createCouponByAdmin(@RequestBody Coupon coupon, String companyName) throws Exception {

        couponController.addCouponByAdmin(coupon, companyName);
    }

    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws Exception {
        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
        couponController.updateCoupon(coupon, userData.getCompanyId());
    }

    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable("id") long couponId) throws Exception {
        return couponController.getCouponById(couponId);
    }



    @DeleteMapping
    @RequestMapping("/{couponId}")
    public void deleteCoupon(@PathVariable("couponId") long couponId) throws Exception {
        couponController.deleteCoupon(couponId);
    }

    @GetMapping
    public List<Coupon> getAllCoupons() throws ApplicationException {
        return couponController.getAllCoupons();
    }

    @GetMapping
    @RequestMapping("/cId")
    public List<Coupon> getCouponsByCompany(HttpServletRequest request) throws Exception {

        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);

        return couponController.getCouponsByCompany(userData.getCompanyId());
    }

    @GetMapping("/byCreateDate")
    public List<Coupon> getCouponByCreateDate(@RequestParam("createDate") String strCreateDate)
            throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = dateFormat.parse(strCreateDate);
        return couponController.getAllCouponsSinceCreateDate(createDate);
    }

    @GetMapping("/byExpirationDate")
    public List<Coupon> getCouponByExpirationDate(@RequestParam("expirationDate") String strExpirationDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = dateFormat.parse(strExpirationDate);
        return couponController.getCouponByExpirationDate(expirationDate);
    }

    @GetMapping("/upToPrice")
    public List<Coupon> getCouponsUpToPrice(@RequestParam("couponPrice") float couponPrice) throws Exception {
        return couponController.getCouponsUpToPrice(couponPrice);
    }
}
