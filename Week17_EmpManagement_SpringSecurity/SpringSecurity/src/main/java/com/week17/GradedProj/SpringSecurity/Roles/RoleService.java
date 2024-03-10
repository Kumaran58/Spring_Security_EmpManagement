package com.week17.GradedProj.SpringSecurity.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

	@Autowired
	RoleRepo repo;
	
	public void addRoles(RoleModel role){
		repo.save(role);
	}
	
	public void deleteRole(int id){
		repo.deleteById(id);
	}
	
	public void deleteByName(String name){
		repo.deleteByName(name);
	}
	
	
	public RoleModel findById(int id){
		if(repo.findById(id).isEmpty()) {
			return null;
		}
		else {
			return repo.findById(id).get();
		}
	}
	
}
