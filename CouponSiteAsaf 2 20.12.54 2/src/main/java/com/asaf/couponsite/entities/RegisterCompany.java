package com.asaf.couponsite.entities;

public class RegisterCompany {

    private Company company;
    private User user;

    public RegisterCompany(Company company, User user) {
        this.company = company;
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
