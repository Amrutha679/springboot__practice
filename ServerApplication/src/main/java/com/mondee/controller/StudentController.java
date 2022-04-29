package com.mondee.controller;

import com.mondee.model.*;
import com.mondee.repository.CourseRepository;
import com.mondee.repository.InstituteRepository;
import com.mondee.repository.StudentRepository;
import com.mondee.service.StudentService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	InstituteRepository instituteRepository;
	
	@Autowired
	StudentService studentService;
	
	@GetMapping(path="/getStudents",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getStudents(){
		return studentService.getAllStudents();
	}
	
	@PostMapping(path="/getStudentById",produces=MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentById(@RequestBody Student sId) {
		return studentService.getStudentById(sId);
		
	}
	
	@PutMapping(path="/updateSnameById",produces=MediaType.APPLICATION_JSON_VALUE)
	public String updateSnameById(@RequestBody Student request) {
		
		return studentService.updateSnameById(request);
	}
	
	@PostMapping(path="/addStudent",produces=MediaType.APPLICATION_JSON_VALUE)
	public String addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
		
	}
	
	@DeleteMapping(path="/deleteStudent",produces=MediaType.APPLICATION_JSON_VALUE)
	public String deleteStudent(@RequestBody Student student) {
		return studentService.deleteStudent(student);
	}
	
	
	@GetMapping(path="/sortStudents",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> sortStudents(){
		return studentService.sortStudents();
	}
	
	@GetMapping(path="/getCourses",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Course> getCourses(){
		return courseRepository.findAll();
	}
	
	@GetMapping(path="/getInstitutes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Institute> getInstitutes(){
		return instituteRepository.findAll();
	}
	
	@PutMapping(path="/updateEmployeeAddress",produces=MediaType.APPLICATION_JSON_VALUE)
	public UpdateResult updateEmployeeAddress(@RequestBody Document request) {
		
		return studentService.updateEmployeeAddress(request);
		
	}
	
	@PutMapping(path="/updateEmpActieStatus",produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee updateEmpActiveStatus(@RequestBody Document request) {
		
		return studentService.updateEmpActiveStatus(request);
	}
	
	@GetMapping(path="/getEmployees",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getEmployees(){
		
		return studentService.getEmployees();
	}
	
	
}
