package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

//this interface will provide the methods to manage the database for commands related to the Seller
public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);
}
