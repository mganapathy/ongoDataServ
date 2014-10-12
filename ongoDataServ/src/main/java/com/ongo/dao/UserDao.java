package com.ongo.dao;

import java.util.List;

import com.mongodb.DBObject;
import com.ongo.model.Employee;
import com.ongo.model.User;

public interface UserDao {

	public boolean addUser(User user) throws Exception;
	public User getUserById(long id) throws Exception;
	public User getUserByName(String name) throws Exception;
	public List<User> getAllUsers() throws Exception;
	public List<Employee> getUserList() throws Exception;
	public boolean deleteUser(long id) throws Exception;
	public boolean isValidUser(String userName, String password);
	public boolean updateUser(long id, User user) throws Exception;
	
	public DBObject convertToDBObject(User user);
	public User convertToUser(DBObject dbObject);
}
