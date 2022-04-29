package com.mondee.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mondee.model.Course;

public interface CourseRepository extends MongoRepository<Course,Integer>{

}
