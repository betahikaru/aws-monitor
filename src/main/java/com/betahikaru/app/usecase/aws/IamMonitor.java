package com.betahikaru.app.usecase.aws;

import java.util.Map;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryResult;
import com.betahikaru.app.pojo.aws.IamStatus;

public class IamMonitor {

	private static IamMonitor singleton = null;

	private final AmazonIdentityManagementClientBuilder builder;

	private final AmazonIdentityManagement iam;

	private IamMonitor() {
		builder = AmazonIdentityManagementClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1)
				.withCredentials(new ProfileCredentialsProvider("default"));
		iam = createClient();
	}

	public static IamMonitor getInstance() {
		if (singleton == null) {
			singleton = new IamMonitor();
		}
		return singleton;
	}

	public IamStatus monitorStatus() {
		GetAccountSummaryResult result = iam.getAccountSummary();
		Map<String, Integer> summary = result.getSummaryMap();
		int countUsers = summary.get("Users");
		int countGroups = summary.get("Groups");
		int countRoles = summary.get("Roles");
		IamStatus status = new IamStatus(countUsers, countGroups, countRoles);
		return status;
	}

	private AmazonIdentityManagement createClient() {
		return builder.build();
	}
}
