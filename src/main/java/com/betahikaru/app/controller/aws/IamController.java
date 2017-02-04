package com.betahikaru.app.controller.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.aws.IamStatus;
import com.betahikaru.app.usecase.aws.IamMonitor;

@Controller
@RequestMapping("/aws/iam")
public class IamController {

	@Autowired
	IamMonitor iamMonitor;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody IamStatus status(
			@RequestParam(value = "iam", required = false, defaultValue = "") String iam) {
		IamStatus iamStatus = iamMonitor.monitorStatus();
		return iamStatus;
	}
}
