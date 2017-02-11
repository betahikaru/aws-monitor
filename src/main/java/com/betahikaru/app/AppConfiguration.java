package com.betahikaru.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.betahikaru.app.config.aws.AwsApiConfig;
import com.betahikaru.app.usecase.aws.Ec2Monitor;
import com.betahikaru.app.usecase.aws.IamMonitor;

@SpringBootApplication
public class AppConfiguration {

	private Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(AppConfiguration.class, args);
	}

	@Bean
	AwsApiConfig defaultAwsApiConfig() {
		AwsApiConfig apiConfig = null;
		AWSCredentialsProvider credentialsProvider = null;

		// Select Credentials Provider
		String awsProfile = environment.getProperty("aws.profile");
		logger.info("aws.profile=" + awsProfile);
		if ("environment".equals(awsProfile)) {
			credentialsProvider = new EnvironmentVariableCredentialsProvider();
		} else {
			if (awsProfile == null || "".equals(awsProfile)) {
				awsProfile = "default";
			}
			credentialsProvider = new ProfileCredentialsProvider(awsProfile);
		}
		logger.info("aws credential provider: " + credentialsProvider);

		// Select Region
		String awsRegion = environment.getProperty("aws.region");
		logger.info("aws.region=" + awsRegion);
		Regions regions = Regions.AP_NORTHEAST_1;
		if (awsRegion != null && !"".equals(awsRegion)) {
			regions = Regions.fromName(awsRegion);
		}
		logger.info("aws region: " + regions);

		apiConfig = new AwsApiConfig().withRegions(regions).withCredentialsProvider(credentialsProvider);
		return apiConfig;
	}

	@Bean
	Ec2Monitor ec2Monitor() {
		return new Ec2Monitor();
	}

	@Bean
	IamMonitor iamMonitor() {
		return new IamMonitor();
	}
}
