package com.mondee.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EmployeeInfo")
public class Employee {
	
	private int eId;
	private String eName;
	private int cId;
	private int deptId;
	private double esalary;
	private List<org.bson.Document> address;
	
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public double getEsalary() {
		return esalary;
	}
	public void setEsalary(double esalary) {
		this.esalary = esalary;
	}
	public List<org.bson.Document> getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", cId=" + cId + ", deptId=" + deptId + ", esalary="
				+ esalary + ", address=" + address + "]";
	}
	public void setAddress(List<org.bson.Document> address) {
		this.address = address;
	}
	
	public void setAddress(Object put) {
		this.address = address;	
	}
	
	
}
