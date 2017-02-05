package com.betahikaru.app.controller.aws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.Status;
import com.betahikaru.app.pojo.aws.AwsStatus;
import com.betahikaru.app.usecase.Monitor;

public abstract class AwsController {

	List<Monitor> monitors;

	public AwsController() {
		monitors = new ArrayList<>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody AwsStatus status(
			@RequestParam(value = "iam", required = false, defaultValue = "") String iam) {
		Map<String, Status> statusMap = new HashMap<>();
		for (Monitor monitor : monitors) {
			Status status = monitor.monitorStatus();
			statusMap.put(status.getName(), status);
		}
		AwsStatus awsStatus = new AwsStatus(statusMap);
		return awsStatus;
	}
}
