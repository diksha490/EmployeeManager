package com.nagarro.EmployeeManager.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HRManager {
	
   
   @Id
   private String userid;
   private String password;
   public HRManager(){}
   public String getUserid() {
	  return userid;
   }
   public void setUserId(String userid) {
	  this.userid = userid;
   }
   public String getPassword() {
	  return password;
   }
   public void setPassword(String password) {
	   this.password = password;
    }
   public HRManager(String userid,String password) {
	   this.userid=userid;
	   this.password=password;
   }
   
   
}
