package com.asaf.couponsite.data;

public class UserLoginDetailsDataObject {

    private String userName;
    private String password;
    private long companyId;
    private long userId;

    public UserLoginDetailsDataObject() {
    }

    public UserLoginDetailsDataObject(String userName, String password, long companyId, long userId) {
        this.userName = userName;
        this.password = password;
        this.companyId = companyId;
        this.userId = userId;
    }

    public UserLoginDetailsDataObject(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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


}
