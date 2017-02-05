package com.betahikaru.app.usecase.aws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryResult;
import com.betahikaru.app.config.aws.AwsApiConfig;
import com.betahikaru.app.pojo.aws.IamStatus;
import com.betahikaru.app.usecase.Monitor;

@Component
public class IamMonitor implements Monitor {

	@Autowired
	private AwsApiConfig apiConfig;

	private AmazonIdentityManagement createClient(AmazonIdentityManagementClientBuilder builder) {
		return builder.build();
	}

	private AmazonIdentityManagement createClient() {
		Regions region = apiConfig.getRegion();
		AWSCredentialsProvider credentialsProvider = apiConfig.getCredentialsProvider();
		AmazonIdentityManagementClientBuilder builder = AmazonIdentityManagementClientBuilder.standard()
				.withRegion(region).withCredentials(credentialsProvider);
		return createClient(builder);
	}

	public IamStatus monitorStatus() {
		AmazonIdentityManagement iamClient = createClient();
		GetAccountSummaryResult result = iamClient.getAccountSummary();
		Map<String, Integer> summary = result.getSummaryMap();
		int countUsers = summary.get("Users");
		int countGroups = summary.get("Groups");
		int countRoles = summary.get("Roles");
		IamStatus status = new IamStatus(countUsers, countGroups, countRoles);
		return status;
	}
}
