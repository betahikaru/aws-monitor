package com.betahikaru.app.controller.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.aws.Ec2Status;
import com.betahikaru.app.usecase.aws.Ec2Monitor;

@Controller
@RequestMapping("/aws/ec2")
public class Ec2Controller {

	@Autowired
	Ec2Monitor ec2Monitor;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody Ec2Status status(
			@RequestParam(value = "iam", required = false, defaultValue = "") String iam) {
		Ec2Status ec2Status = ec2Monitor.monitorStatus();
		return ec2Status;
	}
}
