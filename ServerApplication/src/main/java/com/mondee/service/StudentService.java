package com.mondee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mondee.model.Address;
import com.mondee.model.Employee;
import com.mondee.model.Student;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Service
public class StudentService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Student getStudentById(Student sId) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("sId").is(sId.getsId()));
		query.fields().include("sId");
		query.fields().include("sName");
		query.fields().include("cId");
		query.fields().include("instId");
	
		Student student = mongoTemplate.findOne(query, Student.class);
		
		return student;
		
	}

	public String updateSnameById(Student request) {
		
		String msg = "";
		Query query = new Query();
		query.addCriteria(Criteria.where("sId").is(request.getsId()));
		query.fields().include("sId");
		query.fields().include("sName");
		query.fields().include("cId");
		query.fields().include("instId");
		
		Update update = new Update();
		update.set("sName", request.getsName());
		//Student student = mongoTemplate.findAndModify(query, update, Student.class);
		UpdateResult result = mongoTemplate.upsert(query, update, Student.class);
		if(result.getModifiedCount()!= 0) {
			msg = "updated successfully!";
		}
		else {
			msg = "updation failed!";
		}
		return "Updated successfully";
	}

	public List<Student> getAllStudents() {
		
		List<Student> students = mongoTemplate.findAll(Student.class);
		return students;
	}

	public String addStudent(Student student) {
		
		String message = "";
		Query query = new Query();
		query.addCriteria(Criteria.where("sId").is(student.getsId()));
		Student stu = mongoTemplate.findOne(query, Student.class);
		System.out.println(stu);
		if(stu != null) {
			message = "Record existed";
		}
		
		else {
			mongoTemplate.insert(student);		
			message ="inserted successfully!!";
		}
		return message;
	}

	public String deleteStudent(Student student) {
		String msg = "";
		Query query = new Query();
		query.addCriteria(Criteria.where("sId").is(student.getsId()));
		DeleteResult del = mongoTemplate.remove(query, Student.class);
		if(del.getDeletedCount()!= 0) {
			msg = "document deleted!";
		}	
		else {
			msg = "not deleted!";
		}
		return msg;
	}
	public FindIterable<Document> sortStudentsByCid() {
		Student student = new Student();
		List<Student> students = mongoTemplate.findAll(Student.class);
		MongoClient mongo = MongoClients.create();
		return mongo.getDatabase("customers-qa-dbteam").getCollection("Students").find();
//		List<Student> sortedStudents = students.stream()
//                .sorted((s1,s2)->(s1).compareTo(s2))
//                .collect(Collectors.toList());
	}

	public List<Student> sortStudents() {
		
		Student student = new Student();
		List<Student> students = mongoTemplate.findAll(Student.class);
//		List<Student> sortedStudents = students.stream()
//             .sorted((s1,s2)->(s1).compareTo(s2))
//              .collect(Collectors.toList());
		students.sort((s1,s2)->s1.getsName().compareTo(s2.getsName()));
		
		return students;
	}

	public UpdateResult updateEmployeeAddress(Document request) {
		
		UpdateResult result = null;
		try {
			
			Query query = new Query();
			query.addCriteria(Criteria.where("eId").is(request.get("eId")));
			Update update = new Update();
			update.set("address", request.get("address"));
			result = mongoTemplate.upsert(query, update, Employee.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Employee updateEmpActiveStatus(Document request) {
		
		Employee dbEmployee = null;
		List<Document> dbAddress = null;
		
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("eId").is(request.get("eId")));
			
			dbEmployee =  mongoTemplate.findOne(query, Employee.class);
			dbAddress = dbEmployee.getAddress();
			
			List<Document> rqAddress = (List<Document>) request.get("address");
			
			Document address = new Document();
			
			address.putAll((Map)rqAddress.get(0));
			
			System.out.println(address.get("state"));
			
			System.out.println(address.get("state").equals(dbAddress.get(0).get("state")));
			System.out.println(address.get("country").equals(dbAddress.get(0).get("country")));
			//System.out.println(address.get("addressLine1").equals(get("addressLine1")));
		//	System.out.println(address.getString("addressLine2").equalsIgnoreCase(dbAddress.get(0).getString("addressLine2")));
				
			if(dbAddress.get(0).getString("addressLine2") == null) {
				System.out.println("ad is null");
			}
			else {
				System.out.println("not null");
			}
				if(address.get("city").equals(dbAddress.get(0).get("city")) && address.get("state").equals(dbAddress.get(0).get("state")) &&
						address.get("country").equals(dbAddress.get(0).get("country")) && address.get("addressLine1").equals(dbAddress.get(0).get("addressLine1"))
						&& address.getString("addressLine2").equals(dbAddress.get(0).getString("addressLine2"))) {
					
					System.out.println("equall");
					dbEmployee.setAddress(dbAddress.get(0).put("active", "true"));
					//mongoTemplate.findAndReplace(query,dbEmployee);
					
				}
				else {
					
					dbEmployee.setAddress(dbAddress.get(0).put("active", "false"));
					//mongoTemplate.findAndReplace(query,dbEmployee);	
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbEmployee;
	}

	public List<Student> getEmployees() {
		
		List<Student> employee = null;
		List<Student> activeAddressEmployees = null;
		
		Query query = new Query();
		employee = mongoTemplate.find(query,Student.class,"Students");
		
		//System.out.println(employee.get(0).getAddress().get(0).contains("false"));
		
				activeAddressEmployees = employee.stream().filter(p -> p.getAddress().
						get(0).contains("true")).collect(Collectors.toList());
		
		return activeAddressEmployees;
	}
}





