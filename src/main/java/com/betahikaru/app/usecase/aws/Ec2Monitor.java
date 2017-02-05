package com.betahikaru.app.usecase.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.betahikaru.app.common.aws.AwsConst;
import com.betahikaru.app.config.aws.AwsApiConfig;
import com.betahikaru.app.pojo.aws.Ec2Status;
import com.betahikaru.app.usecase.Monitor;

@Component
public class Ec2Monitor implements Monitor {

	@Autowired
	private AwsApiConfig apiConfig;

	private AmazonEC2 createClient(AmazonEC2ClientBuilder builder) {
		return builder.build();
	}

	private AmazonEC2 createClient() {
		Regions region = apiConfig.getRegion();
		AWSCredentialsProvider credentialsProvider = apiConfig.getCredentialsProvider();
		AmazonEC2ClientBuilder builder = AmazonEC2ClientBuilder.standard().withRegion(region)
				.withCredentials(credentialsProvider);
		return createClient(builder);
	}

	public Ec2Status monitorStatus() {
		int countAll = 0;
		int countRunning = 0;
		AmazonEC2 ec2Client = createClient();
		DescribeInstancesResult result = ec2Client.describeInstances();
		for (Reservation reservation : result.getReservations()) {
			for (Instance instance : reservation.getInstances()) {
				if (instance.getState().getCode() == AwsConst.EC2_INSTANCESTATE_RUNNING) {
					countRunning++;
				}
				countAll++;
			}
		}
		Ec2Status status = new Ec2Status(countAll, countRunning);
		return status;
	}
}
