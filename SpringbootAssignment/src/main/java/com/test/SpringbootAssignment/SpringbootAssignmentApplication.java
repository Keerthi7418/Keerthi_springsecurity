package com.test.SpringbootAssignment;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.SpringbootAssignment.model.ApiUser;
import com.test.SpringbootAssignment.service.ApiUserService;

@SpringBootApplication
public class SpringbootAssignmentApplication implements CommandLineRunner {

	@Autowired
	ApiUserService apiUserService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Set<String> authAdmin = new HashSet<>();
		authAdmin.add("Admin");

		Set<String> authUser = new HashSet<>();
		authUser.add("User");

		Set<String> authAll = new HashSet<>();
		authAll.add("User");
		authAll.add("Admin");

		PasswordEncoder en = new BCryptPasswordEncoder();

		ApiUser apiAdmin = new ApiUser(1, "admin", "admin", en.encode("adminPassword"), authAdmin);
		apiUserService.add(apiAdmin);

		ApiUser apiUser = new ApiUser(2, "user", "user", en.encode("userPassword"), authUser);
		apiUserService.add(apiUser);

		ApiUser apiAll = new ApiUser(3, "superuser", "superuser", en.encode("SuperPassword "), authAll);
		apiUserService.add(apiAll);

	}

}
