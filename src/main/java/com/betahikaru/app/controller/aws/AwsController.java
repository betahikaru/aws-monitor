package com.betahikaru.app.controller.aws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.aws.AbstractStatus;
import com.betahikaru.app.pojo.aws.AwsStatus;
import com.betahikaru.app.pojo.aws.Ec2Status;
import com.betahikaru.app.pojo.aws.IamStatus;
import com.betahikaru.app.usecase.aws.Ec2Monitor;
import com.betahikaru.app.usecase.aws.IamMonitor;

@Controller
@RequestMapping("/aws")
public class AwsController {

	@Autowired
	Ec2Monitor ec2Monitor;

	@Autowired
	IamMonitor iamMonitor;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody AwsStatus status(
			@RequestParam(value = "iam", required = false, defaultValue = "") String iam) {
		Ec2Status ec2Status = ec2Monitor.monitorStatus();
		IamStatus iamStatus = iamMonitor.monitorStatus();
		Map<String, AbstractStatus> statusMap = new HashMap<>();
		statusMap.put(Ec2Status.class.getName(), ec2Status);
		statusMap.put(IamStatus.class.getName(), iamStatus);
		AwsStatus awsStatus = new AwsStatus(statusMap);
		return awsStatus;
	}
}
