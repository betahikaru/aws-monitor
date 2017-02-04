package com.betahikaru.app.pojo.aws;

public class AwsStatus {
	private final Ec2Status ec2Status;

	public AwsStatus(Ec2Status ec2Status) {
		super();
		this.ec2Status = ec2Status;
	}

	public Ec2Status getEc2Status() {
		return ec2Status;
	}

}
