package com.betahikaru.app.pojo.aws;

import com.betahikaru.app.pojo.Status;

public class Ec2Status implements Status {
	private final int countAll;
	private final int countRunning;

	public Ec2Status(int countAll, int countRunning) {
		super();
		this.countAll = countAll;
		this.countRunning = countRunning;
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
}
