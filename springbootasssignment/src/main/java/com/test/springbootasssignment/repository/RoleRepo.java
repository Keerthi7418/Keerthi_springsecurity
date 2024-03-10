package com.test.springbootasssignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.springbootasssignment.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
