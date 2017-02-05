package com.betahikaru.app.pojo.aws;

public class IamStatus extends AbstractStatus {
	private final int countUsers;
	private final int countGroups;
	private final int countRoles;

	public IamStatus(int countUsers, int countGroups, int countRoles) {
		this.countUsers = countUsers;
		this.countGroups = countGroups;
		this.countRoles = countRoles;
	}

	public int getCountUsers() {
		return countUsers;
	}

	public int getCountGroups() {
		return countGroups;
	}

	public int getCountRoles() {
		return countRoles;
	}
}
