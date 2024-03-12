package com.test.SpringbootAssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.SpringbootAssignment.model.Employee;
import com.test.SpringbootAssignment.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAll();
	}

	@PostMapping("/addNewEmployees")
	public String addNewEmployees(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email) {
		Employee employee = new Employee(id, firstname, lastname, email);
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/updateEmployees")
	public String updateEmployees(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email) {
		Employee employee = new Employee(id, firstname, lastname, email);
		return employeeService.updateEmployee(employee);
	}

	@DeleteMapping("/deleteEmployees")
	public String deleteEmployees(@RequestParam int id) {
		return employeeService.deleteEmployee(id);
	}

	@GetMapping("/searchByFirstname")
	public List<Employee> searchByFirstname(@RequestParam String firstname) {
		return employeeService.searchByFirstname(firstname);
	}

	@GetMapping("/sortByFirstName")
	public List<Employee> sortByFirstName() {
		return employeeService.sortByFirstNameAscending();
	}
	
}
