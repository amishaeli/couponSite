package com.asaf.couponsite.api;


import com.asaf.couponsite.consts.Constants;
import com.asaf.couponsite.data.LoggedInUserData;
import com.asaf.couponsite.data.PurchaseData;
import com.asaf.couponsite.entities.Coupon;
import com.asaf.couponsite.entities.Purchase;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.PurchaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {
// NEED TO ADD UPDATE, GETBYCOUPONID, GETBYCUSTOMERID
	@Autowired
	private PurchaseController purchaseController;

	/*
	http://localhost:8080/purchase
	{
  "amount":3,
  "couponId":1,
  "customerId":2
}
	 */
	@PostMapping
	public void createPurchase(@RequestBody PurchaseData purchase, HttpServletRequest request) throws Exception {

		LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
		purchaseController.createPurchase(purchase, userData);
	}


//	@GetMapping("/purchasesByCouponId")
//	public List<Coupon> getPurchasesByCouponId(@RequestParam("couponId") long couponId) throws Exception{
////		return purchaseController.getPurchasesByCustomerId(customerId);
//		return purchaseController.getPurchasesByCouponId(couponId);
//	}

//	@GetMapping
//	@RequestMapping("/getCouponPurchases")
//	public List<Customer> getPurchasesByCouponId(@RequestParam("couponId") long couponId) throws ApplicationException{
//		return purchaseController.getPurchasesByCouponId(couponId);
//	}

	//	http://localhost:8080/purchase
	@DeleteMapping
	@RequestMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") long purchaseId) throws ApplicationException{
		purchaseController.deletePurchase(purchaseId);
	}

//	http://localhost:8080/purchase
	@GetMapping
	public List<Purchase> getAllPurchases() throws ApplicationException{
		return purchaseController.getAllPurchases();
	}

}

