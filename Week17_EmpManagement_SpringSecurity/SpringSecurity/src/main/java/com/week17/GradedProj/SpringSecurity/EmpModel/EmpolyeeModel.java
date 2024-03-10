package com.week17.GradedProj.SpringSecurity.EmpModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpolyeeModel {
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
}
