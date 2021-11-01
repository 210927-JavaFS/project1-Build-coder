package com.revature.services;

import com.revature.repos.UserDAOImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.models.UserDTO;

public class LoginService {
	
	private UserDAOImpl userDaoImpl = new UserDAOImpl();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");


	
	public User login(UserDTO userDto) {

		User user = userDaoImpl.findByName(userDto.username); 

		if(user!=null && (userDto.password.hashCode()==user.getPassword())) {
			return user;
		}
		
		return null;
	}

}