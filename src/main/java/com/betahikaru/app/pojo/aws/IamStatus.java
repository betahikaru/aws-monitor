package com.betahikaru.app.pojo.aws;

import com.betahikaru.app.pojo.Status;

public class IamStatus implements Status {
	private final int countUsers;
	private final int countGroups;
	private final int countRoles;

	public IamStatus(int countUsers, int countGroups, int countRoles) {
		this.countUsers = countUsers;
		this.countGroups = countGroups;
		this.countRoles = countRoles;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
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
