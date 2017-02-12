package com.betahikaru.app.controller.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.pojo.aws.S3Status;
import com.betahikaru.app.usecase.aws.S3Monitor;

@Controller
@RequestMapping("/aws/s3")
public class S3Controller {

	@Autowired
	S3Monitor s3Monitor;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody S3Status status() {
		S3Status s3Status = s3Monitor.monitorStatus();
		return s3Status;
	}
}
