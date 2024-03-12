package com.test.SpringbootAssignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private int id;
	private String firstname;
	private String lastname;
	private String email;

	public Employee(String firstname, String lastname, String email) {

		super();

		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

}
