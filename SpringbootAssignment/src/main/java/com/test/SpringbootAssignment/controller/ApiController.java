package com.test.SpringbootAssignment.controller;

import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.SpringbootAssignment.model.ApiUser;
import com.test.SpringbootAssignment.model.Employee;
import com.test.SpringbootAssignment.security.JwtUtils;
import com.test.SpringbootAssignment.service.ApiUserService;
import com.test.SpringbootAssignment.service.EmployeeService;

import io.jsonwebtoken.Claims;

@RestController
public class ApiController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ApiUserService apiUserService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/api/CheckLogin")
	@ResponseBody
	public String checkLlogin(@RequestParam String name, @RequestParam String password) {

		return apiUserService.login(name, password);

	}

	@GetMapping("/EmployeeHome")
	public String EmployeeHome(Authentication authentication, Model model) {

		String acceptedRole = "ROLE_admin"; 
		boolean roleFound = false;

		System.out.println("Current login by: " + authentication.getName());

		Collection<? extends GrantedAuthority> grantedRoles = authentication.getAuthorities();
		for (GrantedAuthority authority : grantedRoles) {
			String role = authority.getAuthority();
			System.out.println("My role: " + role);
			if (role.equals(acceptedRole)) {
				roleFound = true;
				break;
			}
		}

		if (roleFound) {
			return "adddetails"; 
		} else {
			List<ApiUser> apiUsers = apiUserService.getAll();
			model.addAttribute("appUsers", apiUsers); 
			return "homepage"; 
		}
	}

	public boolean verifyToken(String token) {
		Claims claims = null;
		try {
			claims = jwtUtils.verify(token);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		if (claims == null) {
			return false;
		}
		return true;

	}
	
	@PostMapping("/api/addNewEmployee")
	public String addNewEmployee(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String token) {
		if (verifyToken(token) == false) {
			return null;
		}

		Employee e1 = new Employee(id, firstname, lastname, email);

		Employee duplicateEmployee = employeeService.findById(id);
		if (duplicateEmployee != null) {
			return "Duplicate Id";
		}

		employeeService.addEmployee(e1);
		return "Employee Added Successfully";
	}

	@PostMapping("/api/updateEmployee")
	public String updateEmployee(@RequestParam int id, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String email, @RequestParam String token) {
		if (!verifyToken(token)) {
			return "Not valid authentication";
		}

		Employee employee = new Employee(id, firstname, lastname, email);
		employeeService.updateEmployee(employee);
		return "Employee Updated Successfully";
	}

	@PostMapping("/api/deleteEmployee")
	public String deleteEmployee(@RequestParam int id, @RequestParam String token) {
		if (!verifyToken(token)) {
			return "Not valid authentication";
		}

		employeeService.deleteEmployee(id);
		return "Employee Deleted Successfully";
	}
	
	@GetMapping("/api/searchByFirstname")
	public List<Employee> searchByFirstname(@RequestParam String firstname,  @RequestParam String token) {
		if (!verifyToken(token)) {
			return null;
		}
		return employeeService.searchByFirstname(firstname);
	}

	@GetMapping("/api/sortByFirstName")
	public List<Employee> sortByFirstName( @RequestParam String token) {
		if (!verifyToken(token)) {
			return null;
		}
		return employeeService.sortByFirstNameAscending();
	}
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee(@RequestParam String token) {
		if (verifyToken(token) == false) {
			return null;
		}
		return employeeService.getAll();
	}

}
