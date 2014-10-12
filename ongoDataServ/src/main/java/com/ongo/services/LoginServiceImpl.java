package com.ongo.services;

import java.util.ArrayList;
import java.util.List;

import com.ongo.dao.UserDao;
import com.ongo.dao.UserDataDaoImpl;
import com.ongo.model.User;

public class LoginServiceImpl implements LoginService{

	private UserDao userDao = new UserDataDaoImpl();
	
	@Override
	public boolean addUser(User user) {
		boolean addUser;
		try {
			addUser = userDao.addUser(user);
		} catch (Exception e) {
			addUser = false;
		}
		return addUser;
	}

	@Override
	public boolean isValidUser(User user) {
		User userByName = null;
		try {
			userByName = userDao.getUserByName(user.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userByName == null){
			return false;
		}
		if(userByName.getPassword().equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User getUser(int id) {
		User userById = null;
		try {
			userById = userDao.getUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userById;
	}

	@Override
	public boolean deleteUserById(int id) {
		try {
			return userDao.deleteUser(id);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateUserById(int id, User user) {
		try {
			return userDao.updateUser(id, user);
		} catch (Exception e) {
			return false;
		}		
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		try {
			allUsers = userDao.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allUsers;
	}
}
