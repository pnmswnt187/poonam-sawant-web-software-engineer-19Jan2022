package com.api.buyingfrenzy.exception.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.buyingfrenzy.exception.BusinessException;

@ResponseStatus(
		code = HttpStatus.NOT_FOUND
		, reason = "User Not Found"
	)

public class UsersNotFound extends BusinessException {

	private static final long serialVersionUID = 1L;

}