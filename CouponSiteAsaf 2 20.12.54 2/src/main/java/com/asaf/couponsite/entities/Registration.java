package com.asaf.couponsite.entities;

import com.asaf.couponsite.enums.UserType;

import java.util.List;

public class Registration {
    Customer customer;
    User user;

    public Registration(Customer customer, User user) {
        this.customer = customer;
        this.user = user;
        user.setUserType(UserType.CUSTOMER);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


