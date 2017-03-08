package com.betahikaru.app.repository.aws;

import org.springframework.data.repository.CrudRepository;

import com.betahikaru.app.entity.aws.Ec2Entity;

public interface Ec2Repository extends CrudRepository<Ec2Entity, Long> {

}
