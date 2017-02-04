package com.betahikaru.app.usecase.aws;

import com.betahikaru.app.pojo.aws.AwsStatus;
import com.betahikaru.app.pojo.aws.Ec2Status;

public class AwsMonitor {

	private static AwsMonitor singleton = null;

	private AwsMonitor() {
	}

	public static AwsMonitor getInstance() {
		if (singleton == null) {
			singleton = new AwsMonitor();
		}
		return singleton;
	}

	public AwsStatus monitorStatus() {
		Ec2Status ec2Status = Ec2Monitor.getInstance().monitorStatus();
		AwsStatus awsStatus = new AwsStatus(ec2Status);
		return awsStatus;
	}
}
