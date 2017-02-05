package com.betahikaru.app.pojo.aws;

public class Ec2Status {
	private final int countAll;
	private final int countRunning;

	public Ec2Status(int countAll, int countRunning) {
		super();
		this.countAll = countAll;
		this.countRunning = countRunning;
	}

	public int getCountAll() {
		return countAll;
	}

	public int getCountRunning() {
		return countRunning;
	}
}
