package com.mondee.model;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
public class Student {
	
	private int sId;
	private String sName;
	private int cId;
	private int instId;
	private List<String> address;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public List<String> getAddress() {
		return address;
	}
	public void setAddress(List<String> address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", cId=" + cId + ", instId=" + instId + ", address="
				+ address + "]";
	}
	

}
