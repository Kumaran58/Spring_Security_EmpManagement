package com.week17.GradedProj.SpringSecurity.EmpRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.week17.GradedProj.SpringSecurity.EmpModel.EmpolyeeModel;

public interface EmpolyeeRepo extends JpaRepository<EmpolyeeModel, Integer> {
	
}
