package com.test.SpringbootAssignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.SpringbootAssignment.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstnameIgnoreCase(String firstname);

	List<Employee> findAllByOrderByFirstnameAsc();

}
