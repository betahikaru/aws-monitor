package com.betahikaru.app.pojo.aws;

public class AwsStatus {
	private final Ec2Status ec2Status;
	private final IamStatus iamStatus;

	public AwsStatus(Ec2Status ec2Status, IamStatus iamStatus) {
		this.ec2Status = ec2Status;
		this.iamStatus = iamStatus;
	}

	public Ec2Status getEc2Status() {
		return ec2Status;
	}

	public IamStatus getIamStatus() {
		return iamStatus;
	}
}
