package com.asaf.couponsite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Purchases")
public class Purchase implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "AMOUNT")
    private int amount;

    @ManyToOne
    @JsonIgnore
//    @MapsId("ID")
    private Coupon coupon;

    @ManyToOne
    @JsonIgnore
//    @MapsId("ID")
    private Customer customer;

    public Purchase() {
    }


    public Purchase(int amount, Coupon coupon, Customer customer) {
        this.amount = amount;
        this.coupon = coupon;
        this.customer = customer;
    }


    public Purchase(long id, int amount, Coupon coupon, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.coupon = coupon;
        this.customer = customer;
    }

    public Purchase(int amount, Coupon coupon) {
    }

    public Purchase(int amount, long id) {
    }

    public Purchase(int amount, long couponId, long customerId) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", amount=" + amount +
                ", coupon=" + coupon +
                ", customer=" + customer +
                '}';
    }
}
