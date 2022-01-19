package com.api.buyingfrenzy.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(
		code = HttpStatus.INTERNAL_SERVER_ERROR
		, reason = "Something went wrong !!!"
	)

public class SystemException extends Exception {
		
		private static final long serialVersionUID = 3090313425312202738L;
		
}
