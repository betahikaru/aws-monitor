package com.betahikaru.app.pojo.aws;

import java.util.Map;

public class AwsStatus {
	private final Map<String, Object> statusMap;

	public AwsStatus(Map<String, Object> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<String, Object> getStatusMap() {
		return statusMap;
	}
}
