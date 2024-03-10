package com.week17.GradedProj.SpringSecurity.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.week17.GradedProj.SpringSecurity.EmpModel.EmpolyeeModel;
import com.week17.GradedProj.SpringSecurity.EmpService.EmpService;
import com.week17.GradedProj.SpringSecurity.Roles.RoleModel;
import com.week17.GradedProj.SpringSecurity.Roles.RoleService;
import com.week17.GradedProj.SpringSecurity.SercurityConfig.UserCredentials;
import com.week17.GradedProj.SpringSecurity.SercurityConfig.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class SecurityController {

	@Autowired
	RoleService r1;

	@Autowired
	EmpService e1;

	@Autowired
	UserService u1;
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addRole")
	String addRole(@RequestParam int id,@RequestParam String name) {

		RoleModel r=r1.findById(id);
		if(r == null) {
			RoleModel m=new RoleModel(id, name);
			r1.addRoles(m);
			return "Added";
		}
		else {
			return "Invalid ";

		}

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addUser")
	String addUser(@RequestParam int id,@RequestParam String name,@RequestParam String password,@RequestParam String role[]) {
		PasswordEncoder en = new BCryptPasswordEncoder();
		
		List<String> list = Arrays.asList(role);
		Set<String> set = new HashSet<String>(list);
		
		u1.add(new UserCredentials(id, name, en.encode(password), set));
		
		return "user added";

	}
	

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addemp")
	String addEmp(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email) {
		
		EmpolyeeModel s=e1.FindEmpById(id);
		if(s == null) {
			EmpolyeeModel e=new EmpolyeeModel(id, firstName, lastName, email);
			e1.addEmp(e);			
			return "added";
		}
		else {
			return "Invalid";

		}

	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("updateEmp")
	String update(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email) {
		
		EmpolyeeModel m=e1.FindEmpById(id);
		if(m== null) {
			return "no emp is found";
		}
		else {
			EmpolyeeModel z=new EmpolyeeModel(id, firstName, lastName, email);
			e1.updateEmp(z);
			return "updated";
		}
		
		
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("deleteEmp")
	String delete(@RequestParam int id) {
		
		e1.deleteEmp(id);
		return "Deleted employee id "+id;
		
	}
	
	
	@GetMapping("getallEmp")
	List<EmpolyeeModel> getall(){
		return e1.showAll();
	}
	
	
	@GetMapping("getEmpById")
	EmpolyeeModel getbyid(@RequestParam int id) {
		
		EmpolyeeModel l2= e1.FindEmpById(id);		
		return l2;
		
	}
	
	
	@Operation(summary = "type asc for ascending order and desc for descending order")
	@GetMapping("GetBySort")
	List<EmpolyeeModel> sort(@RequestParam String Order){
		if(Order.equalsIgnoreCase("asc")) {
			return e1.getBySortonly(Direction.ASC, "firstName"); 
		}
		else {
			return e1.getBySortonly(Direction.DESC, "firstName");
		}
	}
	
	
	@GetMapping("Search")
	List<EmpolyeeModel> search(@RequestParam String FirstName){
		return e1.SearchByFirstname(FirstName);
	}
	
	
	

}
