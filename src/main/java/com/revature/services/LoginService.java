package com.revature.services;

import com.revature.repos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;

public class LoginService {
	
	private UserDAOImpl userDaoImpl = new UserDAOImpl();

	
	public boolean login(UserDTO userDto) {

		User user = userDaoImpl.findByName(userDto.username); 

		if(user!=null && (userDto.password.hashCode()==user.getPassword())) {
			return true;
		}
		
		return false;
	}

}