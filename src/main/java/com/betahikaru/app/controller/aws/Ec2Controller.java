package com.betahikaru.app.controller.aws;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betahikaru.app.entity.aws.Ec2Entity;
import com.betahikaru.app.pojo.PojoList;
import com.betahikaru.app.pojo.aws.Ec2Status;
import com.betahikaru.app.pojo.http.Created;
import com.betahikaru.app.pojo.http.InternalServerError;
import com.betahikaru.app.pojo.http.Result;
import com.betahikaru.app.repository.aws.Ec2Repository;
import com.betahikaru.app.usecase.aws.Ec2Monitor;

@Controller
@RequestMapping("/aws/ec2")
public class Ec2Controller {

	@Autowired
	Ec2Monitor ec2Monitor;

	@Autowired
	Ec2Repository ec2Repository;

	@GetMapping
	public @ResponseBody Ec2Status getStatus() {
		Ec2Status ec2Status = ec2Monitor.monitorStatus();
		return ec2Status;
	}

	@PostMapping
	public @ResponseBody ResponseEntity<Result> recordStatus() {
		Ec2Status ec2Status = ec2Monitor.monitorStatus();
		Ec2Entity entity = new Ec2Entity();
		entity.setCountAll(ec2Status.getCountAll());
		entity.setCountRunning(ec2Status.getCountRunning());
		entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		ResponseEntity<Result> response = null;
		try {
			Ec2Entity savedEntity = ec2Repository.save(entity);
			Result created = new Created(savedEntity.getId());
			response = new ResponseEntity<>(created, HttpStatus.CREATED);
		} catch (DataAccessException e) {
			Result internalServerError = new InternalServerError(e.getMessage());
			response = new ResponseEntity<>(internalServerError, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@CrossOrigin(origins = "http://localhost:8000")
	@GetMapping(path = "/all")
	public @ResponseBody PojoList<Ec2Status> getRecordedStatusList() {
		Iterable<Ec2Entity> result = ec2Repository.findAll();
		List<Ec2Status> list = new ArrayList<>();
		for (Ec2Entity entity : result) {
			Ec2Status status = new Ec2Status(entity.getCountAll(), entity.getCountRunning(), entity.getCreatedAt());
			list.add(status);
		}
		PojoList<Ec2Status> responce = new PojoList<>(list);
		return responce;
	}

}
