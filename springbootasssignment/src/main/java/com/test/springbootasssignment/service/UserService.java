package com.test.springbootasssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springbootasssignment.model.User;
import com.test.springbootasssignment.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired

    private UserRepo userRepo;



    public User save(User user) {

        return userRepo.save(user);

    }



    public User findByUsername(String username) {

        return userRepo.findByUsername(username);

    }

}
