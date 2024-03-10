package com.test.springbootasssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springbootasssignment.model.Employee;
import com.test.springbootasssignment.repository.EmployeeRepo;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	

	public void add(Employee employee) {
		employeeRepo.save(employee);
	}

	public void update(Employee employee) {
		employeeRepo.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public Employee getById(int id) {
		Optional<Employee> employeeOptional = employeeRepo.findById(id);
		Employee temp = null;
		if (employeeOptional.get() != null) {
			temp = employeeOptional.get();
		}
		return temp;
	}

	public void delete(Employee employee)

	{
		employeeRepo.delete(employee);
	}
	
	public List<Employee> searchByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return employeeRepo.findByFistnameContainsAllIgnoreCase(firstname);
	}

	public List <Employee> sortByFirstname() {
		// TODO Auto-generated method stub
		return employeeRepo.findAllByOrderByFirstameAsc();
	}

}
