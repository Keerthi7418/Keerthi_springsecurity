package com.test.SpringbootAssignment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.SpringbootAssignment.model.ApiUser;
import com.test.SpringbootAssignment.repository.ApiUserRepo;
import com.test.SpringbootAssignment.security.JwtUtils;

@Service
public class ApiUserService implements UserDetailsService {

	@Autowired
	ApiUserRepo apiUserRepo;

	@Autowired

	private JwtUtils jwtUtils;

	public void add(ApiUser user) {

		apiUserRepo.save(user);

	}

	@Secured("ROLE_SUPERUSER")

	public List<ApiUser> getAll() {

		return apiUserRepo.findAll();

	}

	@Override

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// TODO Auto-generated method stub

		Optional<ApiUser> appUser = apiUserRepo.findByName(username);

		// we are converting the roles from db to grantedauth of userDetailsService

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (String temp : appUser.get().getAuthorities()) {

			GrantedAuthority ga = new SimpleGrantedAuthority(temp);

			grantedAuthorities.add(ga);

		}

		// converting appuser to user from user security

		User user = new User(username, appUser.get().getPassword(), grantedAuthorities);

		return user;

	}

	public String login(String name, String password) {

		// empty token

		String token = null;

		Optional<ApiUser> appUser = apiUserRepo.findByName(name);

		// getting the actual user from the optional

		ApiUser user = null;

		if (!appUser.isEmpty()) {

			user = appUser.get();

		}

		// create encode object

		PasswordEncoder en = new BCryptPasswordEncoder();

		//// en.matches(plinText, encode)

		if (user == null || en.matches(password, user.getPassword()) == false)

		{

			System.out.println("Invalid login");

			token = "Invalid Login";

		} else {

			System.out.println("Login Success");

			// generate jwt

			token = jwtUtils.generateJwt(user);

		}

		System.out.println("token : " + token);

		return token;

	}

}
