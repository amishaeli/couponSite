package com.asaf.couponsite.entities;

import com.asaf.couponsite.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Coupons")
public class Coupon implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "COUPON_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @Column(name = "TITLE", unique = true, nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CREATE_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    @Column(name = "EXPIRATION_DATE", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expirationDate;

    @Column(name = "AMOUNT", nullable = false)
    private int amount;

    @Column(name = "PRICE", nullable = false)
    private float price;

    @Column(name = "IMAGE")
    private String image;

//    @Column(name="COMPANY_ID", nullable = false)
//    private long companyId;

    @ManyToOne
    @JsonIgnore
    private Company company;


    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Purchase> purchases;

    public Coupon() {
    }

    public Coupon(Coupon coupon, Company company) {
        this.id = coupon.id;
        this.couponType = coupon.couponType;
        this.title = coupon.title;
        this.description = coupon.description;
        this.createDate = coupon.createDate;
        this.expirationDate = coupon.expirationDate;
        this.amount = coupon.amount;
        this.price = coupon.price;
        this.image = coupon.image;
        this.company=company;
    }

    public Coupon(long id, CouponType couponType, String title, String description, Date createDate, Date expirationDate, int amount, float price, String image, Company company, List<Purchase> purchases) {
        this.id = id;
        this.couponType = couponType;
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.company = company;
        this.purchases = purchases;
    }

    public Coupon(CouponType couponType, String title, String description, Date expirationDate, int amount, float price, String image, Company company, List<Purchase> purchases) {
        this.couponType = couponType;
        this.title = title;
        this.description = description;
        this.createDate = new Date();
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.company = company;
        this.purchases = purchases;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
