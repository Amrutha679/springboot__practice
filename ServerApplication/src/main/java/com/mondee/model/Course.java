package com.mondee.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Course")
public class Course {
	
	private int cId;
	private String cName;
	private int instId;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	
}
