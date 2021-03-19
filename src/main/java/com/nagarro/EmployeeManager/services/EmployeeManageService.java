package com.nagarro.EmployeeManager.services;

import java.util.List;

import com.nagarro.EmployeeManager.entities.Employee;

public interface EmployeeManageService {
	 @SuppressWarnings("rawtypes")
	public List listEmployees();
	public void updateEmployee(Employee employee);
	public boolean addEmployee(Employee employee);
} 
