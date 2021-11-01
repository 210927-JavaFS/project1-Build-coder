package com.revature.services;

import java.util.List;

import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;

public class UserService {

	private UserDAO userDao = new UserDAOImpl();
	
	public List<User> getAllUsers(){
		return userDao.findAllUsers();
	}
	
	public User getUserById(int id){
		User user = userDao.findById(id);
		if (user!=null) {
			return user;
		} else {
			return new User();
		}
	}

	public User getUserByName(String name){
		User user = userDao.findByName(name);
		if (user!=null) {
			return user;
		} else {
			return new User();
		}
	}
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}
	
	public boolean updateUser(User user){
		return userDao.updateUser(user);
	}
	
	public boolean deleteUser(int id){
		User user = getUserById(id);
		return userDao.deleteUser(user);
	}

	public boolean deleteUser(String name){
		User user = getUserByName(name);
		return userDao.deleteUser(user);
	}
}