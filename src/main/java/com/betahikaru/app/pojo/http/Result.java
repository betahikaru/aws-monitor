package com.betahikaru.app.pojo.http;

import org.springframework.http.HttpStatus;

public abstract class Result {
	/** Status code */
	private final HttpStatus status;

	public Result(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
