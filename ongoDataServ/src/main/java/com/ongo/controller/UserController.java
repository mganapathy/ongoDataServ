package com.ongo.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ongo.model.Status;
import com.ongo.model.User;
import com.ongo.services.LoginService;
import com.ongo.services.LoginServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

	private LoginService loginService = new LoginServiceImpl();

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status insertUserData(@RequestBody User user) {
		boolean addUser = loginService.addUser(user);
		if (addUser) {
			return new Status(0, "User added Successfully !");
		} else {
			return new Status(1, "User already Exist !");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	User searchUserDataById(@PathVariable("id") int id) {

		return loginService.getUser(id);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	Status deleteUserDataById(@PathVariable("id") int id) {
		boolean deleteUserById = loginService.deleteUserById(id);
		if (deleteUserById) {
			return new Status(1, "User deleted Successfully !");
		} else {
			return new Status(0, "User delete Failed");
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status validateLoginData(@RequestBody User user) {
		boolean validUser = loginService.isValidUser(user);
		if (validUser) {
			return new Status(0, "User validated Successfully !");
		} else {
			return new Status(1, "Invalid Login ID or Password entered !");
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public @ResponseBody
	Status updateUserDataById(@PathVariable int id, @RequestBody User newValue) {
		if (loginService.updateUserById(id, newValue)) {
			return new Status(1, "User updated Successfully !");
		} else {
			return new Status(0, "User update Failed");
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getUser() {
		return loginService.getAllUsers();
	}
}
