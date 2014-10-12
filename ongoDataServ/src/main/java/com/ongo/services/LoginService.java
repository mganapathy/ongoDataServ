package com.ongo.services;

import java.util.List;

import com.ongo.model.User;

public interface LoginService {
	
	public boolean addUser(User user);
	public boolean deleteUserById(int id);
	public boolean updateUserById(int id, User user);
	public List<User> getAllUsers();
	public boolean isValidUser(User user);
	public User getUser(int id);
	
}
