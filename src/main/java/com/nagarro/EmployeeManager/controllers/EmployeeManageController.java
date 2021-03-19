package com.nagarro.EmployeeManager.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.EmployeeManager.entities.Employee;
import com.nagarro.EmployeeManager.services.EmployeeManageService;
import com.nagarro.EmployeeManager.utilities.DateFormatter;


@Controller
public class EmployeeManageController {
	@Autowired
	EmployeeManageService employeeManageService;
	
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/update")
	 public ModelAndView UpdateEmployee(@RequestParam("ecode") String ecode,
                                @RequestParam("ename") String ename,
                                @RequestParam("location") String location,
                                @RequestParam("email") String email,
                                @RequestParam("dob") String dob,
                                @RequestParam("hrname") String hrname) {
		 
	     Date parsedDob=DateFormatter.getFormattedDate(dob);
		 Employee employee=new Employee(ecode,ename,location,email,parsedDob);
		 employeeManageService.updateEmployee(employee);
		 List list=employeeManageService.listEmployees();
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("empList",list);
		 mv.addObject("HRManager",hrname);
		 mv.setViewName("employeeList");
		 return mv;
		 
		 
	 }
	 
	 @GetMapping("/upload")
	 public ModelAndView uploadEmployeeDetails(@RequestParam("hrname") String hrname) {
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("hrname",hrname);
		 mv.setViewName("addEmployee");
		 return mv;
		 
	 }
	 
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/add")
	 public ModelAndView addEmployee(@RequestParam("ecode") String ecode,
                                @RequestParam("ename") String ename,
                                @RequestParam("location") String location,
                                @RequestParam("email") String email,
                                @RequestParam("dob") String dob,
                                @RequestParam("hrname") String hrname) {
		 
	     Date parsedDob=DateFormatter.getFormattedDate(dob);
		 Employee employee=new Employee(ecode,ename,location,email,parsedDob);
		 ModelAndView mv=new ModelAndView();
		 if(!employeeManageService.addEmployee(employee)) {
			 mv.addObject("ifAdded",false);
			 mv.setViewName("addEmployee");
		 }
		 else {
		   List list=employeeManageService.listEmployees();
		   mv.addObject("empList",list);
		   mv.addObject("HRManager",hrname);
		   mv.setViewName("employeeList");
		 } 
		   return mv;
		 
		 
	 }
	 
	
	 @SuppressWarnings("unchecked")
	 @GetMapping("/download")
	 public String exportCSV(HttpServletResponse response) throws IOException {
		    response.setContentType("text/csv");
	        
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=employees_"+ ".csv";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Employee> list =employeeManageService.listEmployees();
	        ObjectMapper mapper = new ObjectMapper();
	        List<Employee> empList = mapper.convertValue( list, 
	        	    new TypeReference<List<Employee>>(){}
	        	);
	        
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
	        String[] csvHeader = {"Employee Code", "Employee Name", "Location", "Email", "DOB"};
	        String[] nameMapping = {"code", "name", "location", "email", "dob"};
	         
	        csvWriter.writeHeader(csvHeader);
	        for(Employee emp:empList) {
	            csvWriter.write(emp,nameMapping);
	        }
	         
	        csvWriter.close();
	        return "employeeList";
	       		   
	 }
	
	
}
