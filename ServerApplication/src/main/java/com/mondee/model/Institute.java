package com.mondee.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Institute")
public class Institute {
	
	private int instId;
	private String instName;
	private String address;
	
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	public String getInstName() {
		return instName;
	}
	public void setInstName(String instName) {
		this.instName = instName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
