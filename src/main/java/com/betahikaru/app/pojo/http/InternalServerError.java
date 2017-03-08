package com.betahikaru.app.pojo.http;

import org.springframework.http.HttpStatus;

public class InternalServerError extends Result {
	/** Error detail message */
	private final String detailMessage;

	public InternalServerError(String detailMessage) {
		super(HttpStatus.INTERNAL_SERVER_ERROR);
		this.detailMessage = detailMessage;
	}

	public String getDetailMessage() {
		return detailMessage;
	}
}
