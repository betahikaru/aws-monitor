package com.betahikaru.app.pojo.http;

import org.springframework.http.HttpStatus;

public class Created extends Result {
	/** Created record id */
	private final Integer id;

	public Created(Integer id) {
		super(HttpStatus.CREATED);
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
