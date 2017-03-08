package com.betahikaru.app.pojo.aws;

import java.sql.Timestamp;

import com.betahikaru.app.pojo.Status;

public class Ec2Status implements Status {
	private final int countAll;
	private final int countRunning;
	private final Timestamp createdAt;

	public Ec2Status(int countAll, int countRunning, Timestamp createdAt) {
		this.countAll = countAll;
		this.countRunning = countRunning;
		this.createdAt = createdAt;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	public int getCountAll() {
		return countAll;
	}

	public int getCountRunning() {
		return countRunning;
	}

	@Override
	public Timestamp getCreatedAt() {
		return createdAt;
	}
}
