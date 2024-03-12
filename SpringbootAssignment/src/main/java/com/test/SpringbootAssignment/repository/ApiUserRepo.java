package com.test.SpringbootAssignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.SpringbootAssignment.model.ApiUser;

public interface ApiUserRepo extends JpaRepository<ApiUser, Integer> {

	Optional<ApiUser> findByName(String name);

}
