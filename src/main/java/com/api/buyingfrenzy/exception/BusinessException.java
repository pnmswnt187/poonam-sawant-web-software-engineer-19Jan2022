package com.api.buyingfrenzy.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(
		code = HttpStatus.BAD_REQUEST
		, reason = "Business validation failed"
)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7817449175782499556L;
		
}
