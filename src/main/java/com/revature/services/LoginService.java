package com.revature.services;

import com.revature.models._User;
import com.revature.models.UserDTO;

/**
 * Normally this would go to a DAO and then
 * the DAO would connect to the DB to my 
 * users table
 * 
 * I don't have a users table so I'm going to
 * fake that and hardcode creds into the login 
 * service aka "UserDAO"
 * 
 * DTO: data transfer object
 * exist to transfer data but not be persisted
 * 
 * check user creds (DTO) against the database
 */

public class LoginService {
	
	private UserDAO userDao = new UserDAO();
	
	public boolean login(UserDTO userDto) {
		_User user = userDao.getByUsername(userDto.username);
		
		if(user!=null && (userDto.password.hashCode()==user.getPassword())) {
			return true;
		}
		
		return false;
	}

}