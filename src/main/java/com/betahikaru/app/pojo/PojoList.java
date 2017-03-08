package com.betahikaru.app.pojo;

import java.util.List;

public class PojoList<T> {
	private final List<T> list;

	public PojoList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}
}
