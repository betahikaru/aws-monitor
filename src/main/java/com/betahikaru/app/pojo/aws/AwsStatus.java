package com.betahikaru.app.pojo.aws;

import java.sql.Timestamp;
import java.util.Map;

import com.betahikaru.app.pojo.Status;

public class AwsStatus implements Status {
	private final Map<String, Status> statusMap;
	private final Timestamp createdAt;

	public AwsStatus(Map<String, Status> statusMap, Timestamp createdAt) {
		this.statusMap = statusMap;
		this.createdAt = createdAt;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public Map<String, Status> getStatusMap() {
		return statusMap;
	}

	@Override
	public Timestamp getCreatedAt() {
		return createdAt;
	}
}
