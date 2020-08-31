package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

// Will offer operations/services/calcs related to the Department Class.
public class DepartmentService {

	public List<Department> findAll(){
		// this method in the future will get the values from the database
		// for now, lets only MOCK, use fake data
		
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "book"));
		list.add(new Department(2, "Computer"));
		list.add(new Department(3, "Electronics"));
		
		return list;
		
		
	}
}
