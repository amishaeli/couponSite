package com.asaf.couponsite.data;

import com.asaf.couponsite.enums.UserType;


public class LoginResponseDataObject {

	private UserType userType;
	private int token;
	private long userId;
	private long companyId;

	public LoginResponseDataObject(int token,UserType userType,  long id, long companyId) {
		this.userType = userType;
		this.token = token;
		this.userId = id;
		this.companyId = companyId;
	}

	public LoginResponseDataObject(int token, UserType userType) {
		this.userType = userType;
		this.token = token;
	}

	public LoginResponseDataObject() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
}
