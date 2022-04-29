package com.mondee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mondee.model.*;
public interface InstituteRepository extends MongoRepository<Institute,Integer> {

}
