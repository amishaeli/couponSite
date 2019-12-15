package com.asaf.couponsite.data;

import com.asaf.couponsite.enums.UserType;


public class LoginResponseDataObject {

	private UserType userType;
	private int token;

	public LoginResponseDataObject(UserType userType, int token) {
		this.userType = userType;
		this.token = token;
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
}
