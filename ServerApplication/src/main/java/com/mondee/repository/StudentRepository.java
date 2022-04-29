package com.mondee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mondee.model.*;

public interface StudentRepository extends MongoRepository<Student,Integer> {

}
