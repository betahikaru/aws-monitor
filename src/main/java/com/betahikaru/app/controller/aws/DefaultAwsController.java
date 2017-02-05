package com.betahikaru.app.controller.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.betahikaru.app.usecase.aws.Ec2Monitor;
import com.betahikaru.app.usecase.aws.IamMonitor;

@Controller
@RequestMapping("/aws")
public class DefaultAwsController extends AbstractAwsController {
	@Autowired
	public void setEc2Monitor(Ec2Monitor ec2Monitor) {
		this.monitors.add(ec2Monitor);
	}

	@Autowired
	public void setIamMonitor(IamMonitor iamMonitor) {
		this.monitors.add(iamMonitor);
	}
}
