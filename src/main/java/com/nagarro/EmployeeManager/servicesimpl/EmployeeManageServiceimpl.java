package com.nagarro.EmployeeManager.servicesimpl;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.nagarro.EmployeeManager.entities.Employee;
import com.nagarro.EmployeeManager.services.EmployeeManageService;




@Service
public class EmployeeManageServiceimpl implements EmployeeManageService{

	private String baseUrl = "http://localhost:9090/";

    private RestTemplate restTemplate = new RestTemplate();
    
	@SuppressWarnings("unchecked")
	public List<Employee> listEmployees() {
   	    String url = baseUrl + "/employees";
		List<Employee> employeeDetails =restTemplate.getForObject(url, List.class);
        return employeeDetails;
		
	 }
    
     public void updateEmployee(Employee employee) {
    	 String ecode=employee.getCode();
    	 String url=baseUrl+"/employees/"+ecode;
    	 restTemplate.put(url,employee);
     }
     
     
     public boolean addEmployee(Employee emp) {
    	 String url = baseUrl + "/employees";
    	 if(!isAlreadyExist(emp.getCode())) {
              restTemplate.postForObject(url,emp,Employee.class);
              return true;
    	 }
    	return false; 
    	
     }
     
     public boolean isAlreadyExist(String ecode) {
    	 String url=baseUrl+"/employees/"+ecode;
    	 Employee employee=restTemplate.getForObject(url,Employee.class);
    	 if(employee==null) {
    		 System.out.println("exist");
    		 return false;
    	 }
    	 return true;
     }
	  
}
