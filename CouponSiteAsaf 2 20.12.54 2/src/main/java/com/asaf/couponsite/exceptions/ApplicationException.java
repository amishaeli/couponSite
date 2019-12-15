package com.asaf.couponsite.exceptions;

import com.asaf.couponsite.enums.ErrorType;


public class ApplicationException extends RuntimeException {

	private ErrorType errorType;

	public ApplicationException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}

	public ApplicationException(Exception e, ErrorType errorType, String message) {
		super(message, e);
		this.errorType = errorType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

}

