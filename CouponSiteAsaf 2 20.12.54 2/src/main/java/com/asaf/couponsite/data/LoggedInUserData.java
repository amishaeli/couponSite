package com.asaf.couponsite.data;

import com.asaf.couponsite.enums.UserType;


public class LoggedInUserData {

	private int token;
	private UserType userType;
	private Long companyId;
	private long userId;

	public LoggedInUserData(UserType userType, Long companyId, long userId) {
		this(userType, userId);
		this.companyId = companyId;
	}

	public LoggedInUserData(UserType userType, Long userId) {
		this.userType = userType;
		this.companyId = null;
		this.userId = userId;
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
	public Long getCompany() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
