package com.week17.GradedProj.SpringSecurity.Roles;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleModel, Integer>{
	
	Optional<RoleModel> findByName(String name);
	void deleteByName(String name);
	

}
