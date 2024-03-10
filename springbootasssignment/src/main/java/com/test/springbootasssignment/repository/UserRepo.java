package com.test.springbootasssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springbootasssignment.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
