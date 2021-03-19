package com.nagarro.EmployeeManager.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.EmployeeManager.entities.Employee;
import com.nagarro.EmployeeManager.services.EmployeeManageService;
import com.nagarro.EmployeeManager.services.HRLoginService;

@Controller
public class HRLoginController {
	
	  @Autowired
	  HRLoginService hrLoginService;
	  
	  @Autowired
	  EmployeeManageService employeeManageService;
	  
	  @GetMapping("/login")
      public String start() {
    	   return "HRLogin";
      }
	
	  @SuppressWarnings("unchecked")
	  @PostMapping("/validate")
	  public ModelAndView processLogin(@RequestParam("userid") String userid,
			                           @RequestParam("password") String password) {
		  
		  ModelAndView mv=new ModelAndView();
		  if(hrLoginService.isvalidLogin(userid,password)) {
			  
			 List<Employee> list=employeeManageService.listEmployees();
			 mv.addObject("empList",list);
			 mv.addObject("HRManager",userid);
			 mv.setViewName("employeeList");
			 return mv;
			  
		  }
		 mv.addObject("isValid",false);
		 mv.setViewName("HRLogin");
		 return mv;
	  }
	 
	 @GetMapping("/edit")
	 public ModelAndView getEditEmployees(@RequestParam String empCode, @RequestParam String hrname) {
		 
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("ecode",empCode);
		 mv.addObject("hrname",hrname);
         mv.setViewName("editEmployee");
		 return mv;
	 }
	 
	 @GetMapping("/logout")
	 public ModelAndView logout() {
		 ModelAndView mv=new ModelAndView();
		 mv.addObject("ecode",null);
		 mv.setViewName("HRLogin");
		 return mv;
	 }
	 
	 
}
