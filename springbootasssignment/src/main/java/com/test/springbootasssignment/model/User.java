package com.test.springbootasssignment.model;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
    private int id;
    
    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

	public User(String username, String password, List<Role> roles) {
		super();
		
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	

}
