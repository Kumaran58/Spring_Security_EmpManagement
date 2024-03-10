package com.week17.GradedProj.SpringSecurity.SercurityConfig;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<UserCredentials, Integer>{
	
	Optional<UserCredentials> findByName(String name);

}
