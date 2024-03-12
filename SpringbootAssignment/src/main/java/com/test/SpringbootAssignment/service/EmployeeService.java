package com.test.SpringbootAssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.SpringbootAssignment.model.Employee;
import com.test.SpringbootAssignment.repository.EmployeeRepo;

@Service
public class EmployeeService {
	@Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }
    
    public String addEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            return "Employee with ID " + employee.getId() + " already exists";
        } else {
            employeeRepo.save(employee);
            return "Employee added successfully";
        }
    }

    public String updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
        if (existingEmployee.isPresent()) {
        	employeeRepo.save(employee);
            return "Employee updated successfully";
        } else {
            return "Employee with ID " + employee.getId() + " does not exist";
        }
    }

    public String deleteEmployee(int id) {
        if (employeeRepo.existsById(id)) {
        	employeeRepo.deleteById(id);
            return "Employee deleted successfully";
        } else {
            return "Employee with ID " + id + " does not exist";
        }
    }

    public Employee findById(int id) {
        return employeeRepo.findById(id).orElse(null);
    }
    
    public List<Employee> searchByFirstname(String firstname) {
        return employeeRepo.findByFirstnameIgnoreCase(firstname);
    }
    
    public List<Employee> sortByFirstNameAscending() {
        return employeeRepo.findAllByOrderByFirstnameAsc();
    }
}