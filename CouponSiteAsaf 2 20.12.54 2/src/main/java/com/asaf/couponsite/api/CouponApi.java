package com.asaf.couponsite.api;


import com.asaf.couponsite.entities.AddCoupon;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.CouponController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponApi {
    // All functions below are working!!!!!!
    @Autowired
    private CouponController couponController;
    //ghghghg
    /*http://localhost:8080/coupon
    create only Coupon without companyId
    {
    "couponType":"FASHION",
         "title":"test",
         "description":"a",
         "createDate":"2019-12-08",
         "expirationDate":"2019-12-31",
         "amount":5,
         "price":12.3,
         "image":"adsdd"
        }

     */
    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) throws ApplicationException {
        couponController.createCoupon(coupon);
    }
/*http://localhost:8080/coupon
Update coupon BUT the companyId change to null
{
  "id":3,
"couponType":"FASHION",
     "title":"newtest",
     "description":"a",
     "createDate":"2019-12-08",
     "expirationDate":"2019-12-31",
     "amount":5,
     "price":12.3,
     "image":"adsdd"
    }

 */
	@PostMapping("/add")
    public void addCoupon(@RequestBody AddCoupon addCoupon) throws Exception {
        couponController.addCoupon(addCoupon);
    }

    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon) throws Exception {
        couponController.updateCoupon(coupon);
    }

    //http://localhost:8080/coupon/2
    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable("id") long couponId) throws Exception {
        return couponController.getCouponById(couponId);
    }

    //http://localhost:8080/coupon/2
    @DeleteMapping
    @RequestMapping("/{couponId}")
    public void deleteCoupon(@PathVariable("couponId") long couponId) throws Exception {
        couponController.deleteCoupon(couponId);
    }

    //	http://localhost:8080/coupon
    @GetMapping
    @RequestMapping
    public List<Coupon> getAllCoupons() throws ApplicationException {
        return couponController.getAllCoupons();

    }

    //http://localhost:8080/coupon/byCreateDate?createDate=2019-12-09
    @GetMapping("/byCreateDate")
    public List<Coupon> getCouponByCreateDate(@RequestParam("createDate") String strCreateDate)
            throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createDate = dateFormat.parse(strCreateDate);
        return couponController.getAllCouponsSinceCreateDate(createDate);
    }

    //	http://localhost:8080/coupon/byExpirationDate?expirationDate=2019-12-29
    @GetMapping("/byExpirationDate")
    public List<Coupon> getCouponByExpirationDate(@RequestParam("expirationDate") String strExpirationDate) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expirationDate = dateFormat.parse(strExpirationDate);
        return couponController.getCouponByExpirationDate(expirationDate);
    }

    //http://localhost:8080/coupon/upToPrice?couponPrice=200
    @GetMapping("/upToPrice")
    public List<Coupon> getCouponsUpToPrice(@RequestParam("couponPrice") float couponPrice) throws Exception {
        return couponController.getCouponsUpToPrice(couponPrice);
    }
}
