package com.betahikaru.app.usecase.aws;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.betahikaru.app.pojo.aws.Ec2Status;

public class Ec2Monitor {

	public static final int STATE_INSTANCE_RUNNING = 16;

	private static Ec2Monitor singleton = null;

	private final AmazonEC2ClientBuilder builder;

	private final AmazonEC2 ec2;

	private Ec2Monitor() {
		builder = AmazonEC2ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1)
				.withCredentials(new ProfileCredentialsProvider("default"));
		ec2 = createClient();
	}

	public static Ec2Monitor getInstance() {
		if (singleton == null) {
			singleton = new Ec2Monitor();
		}
		return singleton;
	}

	public Ec2Status monitorStatus() {
		int countAll = 0;
		int countRunning = 0;
		DescribeInstancesResult result = ec2.describeInstances();
		for (Reservation reservation : result.getReservations()) {
			for (Instance instance : reservation.getInstances()) {
				if (instance.getState().getCode() == STATE_INSTANCE_RUNNING) {
					countRunning++;
				}
				countAll++;
			}
		}
		Ec2Status status = new Ec2Status(countAll, countRunning);
		return status;
	}

	private AmazonEC2 createClient() {
		return builder.build();
	}
}
