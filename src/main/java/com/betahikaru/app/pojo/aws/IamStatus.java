package com.betahikaru.app.pojo.aws;

import java.sql.Timestamp;

import com.betahikaru.app.pojo.Status;

public class IamStatus implements Status {
	private final int countUsers;
	private final int countGroups;
	private final int countRoles;
	private final Timestamp createdAt;

	public IamStatus(int countUsers, int countGroups, int countRoles, Timestamp createdAt) {
		this.countUsers = countUsers;
		this.countGroups = countGroups;
		this.countRoles = countRoles;
		this.createdAt = createdAt;
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

	@Override
	public Timestamp getCreatedAt() {
		return createdAt;
	}
}
