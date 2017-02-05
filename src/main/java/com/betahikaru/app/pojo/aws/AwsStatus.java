package com.betahikaru.app.pojo.aws;

import java.util.Map;

public class AwsStatus {
	private final Map<String, AbstractStatus> statusMap;

	public AwsStatus(Map<String, AbstractStatus> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<String, AbstractStatus> getStatusMap() {
		return statusMap;
	}
}
