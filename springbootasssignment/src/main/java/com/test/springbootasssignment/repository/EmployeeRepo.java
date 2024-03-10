package com.test.springbootasssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springbootasssignment.model.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

	List<Employee> findByFistnameContainsAllIgnoreCase(String firstname);

	List<Employee> findAllByOrderByFirstameAsc();

}
