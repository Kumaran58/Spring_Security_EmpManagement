package com.week17.GradedProj.SpringSecurity.EmpService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.week17.GradedProj.SpringSecurity.EmpModel.EmpolyeeModel;
import com.week17.GradedProj.SpringSecurity.EmpRepo.EmpolyeeRepo;


@Service
public class EmpService {

	@Autowired
	EmpolyeeRepo e1;

	public void addEmp(EmpolyeeModel e){
		e1.save(e);
	}

	public void updateEmp(EmpolyeeModel e){
		e1.save(e);
	}

	public void deleteEmp(int id){
		e1.deleteById(id);
	}

	public List<EmpolyeeModel> showAll(){
		return e1.findAll();
	}

	public EmpolyeeModel FindEmpById(int id){

		if(e1.findById(id).isEmpty()) {
			return null;
		}
		return e1.findById(id).get();
	}


	public List<EmpolyeeModel> getBySortonly(Direction d1,String columnName){
		return e1.findAll(Sort.by(d1,columnName));
	}

	public List<EmpolyeeModel> SearchByFirstname(String SearchKey){

		EmpolyeeModel dummy=new EmpolyeeModel();
		dummy.setFirstName(SearchKey);


		ExampleMatcher match=ExampleMatcher.matching().withMatcher("firstName",ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","lastName","email");

		Example<EmpolyeeModel> e=Example.of(dummy, match);
		return e1.findAll(e);
	}




}