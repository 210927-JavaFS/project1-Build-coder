package com.revature.services;

import com.revature.models._User;
import com.revature.repos.UserDAOImpl;
import com.revature.models.User;
import com.revature.models.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private UserDAOImpl userDaoImpl = new UserDAOImpl();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");

	
	public boolean login(UserDTO userDto) {

		myLogger.debug("LoginService:username: " + userDto.username);
		myLogger.debug("LoginService:password: " + userDto.password);
		// _User _user = userDao.getByUsername(userDto.username);
		User user = userDaoImpl.findByName(userDto.username); //500 server error
		//Provided id of the wrong type for class com.revature.models.User. 
		// Expected: class java.lang.Integer, got class java.lang.String
		// myLogger.info("Retrieved from DB: " + user.getFirstName());
		// myLogger.info("Retrieved from DB: " + user.getPassword());

		
		// if(_user!=null && (userDto.password.hashCode()==_user.getPassword())) {
		// 	return true;
		// }
		
		// return false;


		if(user!=null && (userDto.password.hashCode()==user.getPassword())) {
			return true;
		}
		
		return false;
	}

}