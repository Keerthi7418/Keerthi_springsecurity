package com.test.springbootasssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springbootasssignment.model.Role;
import com.test.springbootasssignment.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping
	public Role addRoles(@RequestBody Role role) {
		return roleService.save(role);
	}
}


