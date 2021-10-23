package com.revature.services;

import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class LoginService {
    private UserDAOImpl userDao = new UserDAOImpl();
	
	public boolean login(String name, String password) {
		User user = userDao.findByName(name);
		
		if(user!=null && (user.getPassword().equals(password))){
			return true;
		}
		
		return false;
	}
}
