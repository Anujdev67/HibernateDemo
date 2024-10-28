package com.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vendor {
	@Id
	private int id;
	private String name;
	private String contactInfo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + "]";
	}
	

}
