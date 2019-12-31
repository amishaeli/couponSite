package com.asaf.couponsite.entities;

public class AddCoupon {
    Coupon coupon;
    Company company;


    public AddCoupon(Coupon coupon, Company company) {
        this.coupon = coupon;
        this.company = company;
    }

    public AddCoupon() {
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
