package com.revature.services;

import com.revature.models._User;
import com.revature.models.UserDTO;

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