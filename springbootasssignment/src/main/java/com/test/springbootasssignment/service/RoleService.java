package com.test.springbootasssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springbootasssignment.model.Role;
import com.test.springbootasssignment.repository.RoleRepo;

@Service
public class RoleService {
	
	@Autowired

    private RoleRepo roleRepo;

    public Role save(Role role) {

        return roleRepo.save(role);

    }

}
