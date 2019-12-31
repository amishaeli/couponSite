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
@RequestMapping("/purchases")
public class PurchaseApi {
    @Autowired
    private PurchaseController purchaseController;

    @PostMapping
    public void createPurchase(@RequestBody PurchaseData purchase, HttpServletRequest request) throws Exception {

        LoggedInUserData userData = (LoggedInUserData) request.getAttribute(Constants.USER_DATA_KEY);
        purchaseController.createPurchase(purchase, userData);
    }

    @DeleteMapping
    @RequestMapping("/{purchaseId}")
    public void deletePurchase(@PathVariable("purchaseId") long purchaseId) throws ApplicationException {
        purchaseController.deletePurchase(purchaseId);
    }

    @GetMapping
    public List<Purchase> getAllPurchases() throws ApplicationException {
        return purchaseController.getAllPurchases();
    }
}

