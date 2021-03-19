package com.nagarro.EmployeeManager.servicesimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.EmployeeManager.entities.HRManager;
import com.nagarro.EmployeeManager.repository.HRManagerRepository;
import com.nagarro.EmployeeManager.services.HRLoginService;

@Service
public class HRLoginServiceImpl implements HRLoginService {
	
	 @Autowired
	 HRManagerRepository hrManagerRepository;
	 
     public boolean isvalidLogin(String userid,String password) {
    	 
    	 //System.out.println(userid);
    	 Optional<HRManager> optional=hrManagerRepository.findById(userid);
    	 if(optional.isPresent()) {
    		 System.out.println("get");
    		 HRManager manager=optional.get();
    		 System.out.println(manager.getPassword());
    		 if(manager.getPassword().equals(password)) {
    			 return true;
    		 }
    	 }
     return false;	 
    	 
     }
}
