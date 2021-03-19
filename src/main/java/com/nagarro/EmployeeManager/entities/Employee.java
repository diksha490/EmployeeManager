package com.nagarro.EmployeeManager.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
   @Id
   private String code;
   private String name;
   private String location;
   private String email;
   private Date dob;
public Employee() {
	super();
}
public Employee(String code, String name, String location, String email, Date dob) {
	super();
	this.code = code;
	this.name = name;
	this.location = location;
	this.email = email;
	this.dob = dob;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
   
}
