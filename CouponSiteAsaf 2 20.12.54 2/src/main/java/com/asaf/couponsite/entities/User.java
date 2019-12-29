package com.asaf.couponsite.entities;

import com.asaf.couponsite.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_NAME", unique = true, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;


    @ManyToOne
//    @JoinColumn(name = "company_id" ,nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Company company;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional=false)
//    private Customer customer;


    @ManyToOne
//    @JoinColumn(name = "company_id" ,nullable = true, insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    public User() {
    }

    public User(String userName, String password, UserType userType, Company company, Customer customer) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.company = company;
        this.customer = customer;
    }

    public User(long id, String userName, String password, UserType userType, Company company, Customer customer) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.company = company;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
