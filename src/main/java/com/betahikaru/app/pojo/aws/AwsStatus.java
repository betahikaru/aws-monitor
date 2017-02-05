package com.betahikaru.app.pojo.aws;

import java.util.Map;

import com.betahikaru.app.pojo.Status;

public class AwsStatus implements Status {
	private final Map<String, Status> statusMap;

	public AwsStatus(Map<String, Status> statusMap) {
		this.statusMap = statusMap;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public Map<String, Status> getStatusMap() {
		return statusMap;
	}
}
