package com.asaf.couponsite.enums;


public enum ErrorType {

	GENERAL_ERROR(601, "GENERAL ERROR", "General error", true), 
	FAIL_TO_GENERATE_ID(602, "FAIL TO GENERATE ID", "Couldn't generate an ID", false),
	LOGIN_FAILED(603, "LOGIN FAILED", "Login failed. Please try again.", false),
	NAME_ALREADY_EXISTS(604, "NAME ALREADY EXIST", "The name you chose already exists. Please enter another name", false), 
	MUST_ENTER_NAME(605, "MUST ENTER NAME",  "Can not insert an null/empty name", false),
	MUST_ENTER_ADDRESS(606, "MUST ENTER ADDRESS", "Can not insert an null/empty address", false),
	ID_DOES_NOT_EXIST(607, "ID DOES NOT EXIST", "This ID does'nt exist", false),
//	NON_REPLACEABLE_NAME("Cannot change the name", false),
	INVALID_PASSWORD(608, "INVALID PASSWORD", "Password must contain at least 8 charaters, only UpperCase lettersand and at least one digit", false),
	NOT_ENOUGH_COUPONS_LEFT(609, "NOT ENOUGH COUPONS LEFT", "Not enough coupons left to purchase the amount requested", false),
	COUPON_EXPIERED(610, "COUPON EXPIRED", "The coupon is expiered", false),
	COUPON_TITLE_EXIST(611, "COUPON TITLE EXIST",  "The title of this coupon is already exists, please change the title", false),
	INVALID_PRICE(612, "INVALID PRICE", "Coupon price must be more than 0", false),
	INVALID_EMAIL(613, "INVALID EMAIL", "Email address is InValid, Please enter a valid email address", false),
	INVALID_AMOUNT(614, "INVALID AMOUNT", "Coupon's amount must be more than 0", false), 
	INVALID_DATES(615, "INVALID DATES", "The dates you've entered are wrong", false),
	MUST_INSERT_A_VALUE(616, "MUST INSERT A VLUE DATES", "Must insert a value", false),
	WRONG_USER_NAME_PASS(617, "USERNAME OR PASSWORD IS WRONG", "Username or password is wrong", false);

	private int errorNumber;
	private String ErrorName;
	private String errorMessage;
	private boolean isShowStackTrace;

	private ErrorType(int errorNumber, String errorName, String internalMessage, boolean isShowStackTrace) {
		this.errorNumber = errorNumber;
		this.ErrorName = errorName;
		this.errorMessage = internalMessage;
		this.isShowStackTrace = isShowStackTrace;
	}

	private ErrorType(int errorNumber, String internalMessage) {
		this.errorNumber = errorNumber;
		this.errorMessage = internalMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public String getErrorName() {
		return ErrorName;
	}

	public void setErrorName(String errorName) {
		ErrorName = errorName;
	}

	
}

