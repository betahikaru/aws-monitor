package com.betahikaru.app.controller.aws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.aws.AwsStatus;
import com.betahikaru.app.usecase.aws.AwsMonitor;

@Controller
@RequestMapping("/aws")
public class AwsController {

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody AwsStatus status(@RequestParam(value = "iam", required = false, defaultValue = "") String iam) {
		AwsStatus awsStatus = AwsMonitor.getInstance().monitorStatus();
		return awsStatus;
	}
}
