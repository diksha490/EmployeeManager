package com.nagarro.EmployeeManager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.EmployeeManager.entities.HRManager;

@Repository
public interface HRManagerRepository extends CrudRepository<HRManager,String> {

}
