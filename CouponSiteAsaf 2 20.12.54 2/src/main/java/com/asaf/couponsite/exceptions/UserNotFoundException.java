package com.asaf.couponsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User name or password is wrong")
public class UserNotFoundException extends Exception {

}
