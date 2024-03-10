package com.test.springbootasssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.springbootasssignment.model.Employee;
import com.test.springbootasssignment.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	
	@RequestMapping("/addemployeeform")
	public String EmployeeForm() {
		
		return "employeeform";
	}

	@PostMapping("/addemployees")
	public String addEmployee(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email, Model data) {

		Employee addemployees = new Employee(id, firstname, lastname, email);
		employeeService.add(addemployees);
		
		List<Employee> employee = employeeService.getAll();
		data.addAttribute("models", employee);
		return "showemployees";
	}
	
	@RequestMapping("/showemployees")
	public String showEmployee(Model data) {

		List<Employee> employees = employeeService.getAll();

		data.addAttribute("datas", employees);

		return "showemployees";

	}
	
	@GetMapping("/updateemployees")
	public String updateEmployees(@RequestParam int id, Model data) {
		Employee employees = employeeService.getById(id);
		data.addAttribute("employees", employees); 
	    return "updateemployees";    
	}

	@PostMapping("/updated")
	public String updatedEmployees(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email, Model data) {
		Employee employees = new Employee(id, firstname, lastname, email);
		employeeService.update(employees);
		data.addAttribute("employees", employees);
		
		List<Employee> employee = employeeService.getAll();

		data.addAttribute("data", employee);
		
		return "showemployees";

		
	}

	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam int id,Model data) {

		Employee delete = new Employee(id, "", "", "");

		employeeService.delete(delete);
		
		List<Employee> employees = employeeService.getAll();

		data.addAttribute("data", employees);
		
		return "showemployees";
	}
	
	@GetMapping("/searchbyemployee")
	public List<Employee> searchByFirstname(@PathVariable String firstname) {
		return employeeService.searchByFirstname(firstname);
	}

	@GetMapping("/sortemployee")
	public List<Employee> sortByFirstName() {
		return employeeService.sortByFirstname();
	}
	

}
