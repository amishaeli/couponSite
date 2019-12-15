package com.asaf.couponsite.entities;

import java.io.Serializable;

public class ErrorBean implements Serializable {
	
	private int errorNumber;
	private String errorMessage;
	private String errorType;
	
	public ErrorBean() {
	}
	
	public ErrorBean(int errorNumber, String errorMessage, String errorName) {
		this.errorNumber = errorNumber;
		this.errorMessage = errorMessage;
		this.errorType = errorName;
	}
	
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorName() {
		return errorType;
	}
	public void setErrorName(String errorName) {
		this.errorType = errorName;
	}

	
	
}
