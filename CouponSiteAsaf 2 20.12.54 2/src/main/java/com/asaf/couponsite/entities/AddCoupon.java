package com.asaf.couponsite.entities;

public class AddCoupon {
    Coupon coupon;
    Company companyId;


    public AddCoupon(Coupon coupon, Company companyId) {
        this.coupon = coupon;
        this.companyId = companyId;
    }

    public AddCoupon() {
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
}
