package com.asaf.couponsite.data;

import com.asaf.couponsite.enums.UserType;


public class LoggedInUserData {

    private int token;
    private UserType userType;
    private Long companyId;
    private long userId;
    private long customerId;

    public LoggedInUserData(int token, UserType userType, long companyId, long userId, long customerId) {
        this.token = token;
        this.userType = userType;
        this.companyId = companyId;
        this.userId = userId;
        this.customerId = customerId;
    }

    public LoggedInUserData(UserType userType, Long companyId, long userId, long customerId) {
        this.userType = userType;
        this.companyId = companyId;
        this.userId = userId;
        this.customerId = customerId;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}