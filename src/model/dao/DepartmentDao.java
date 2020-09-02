package model.dao;

import java.util.List;

import model.entities.Department;

// this interface will provide the methods to manage the database for commands related to the Department
public interface DepartmentDao {

	void insert(Department obj);
	void update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
