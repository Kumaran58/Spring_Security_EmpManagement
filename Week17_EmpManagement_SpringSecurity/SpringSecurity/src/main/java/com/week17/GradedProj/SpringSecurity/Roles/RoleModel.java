package com.week17.GradedProj.SpringSecurity.Roles;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {

	@Id
	private int id;
	@Column(unique = true)
	private String name;
	
}
