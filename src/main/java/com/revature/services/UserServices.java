package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserServices {

	private UserDAO userDAO = new UserDAOImpl();
	
	public List<User> getAllUsers(){
		return userDAO.getAllUsers();
	}
	
	public User getUser(int id){
		User user = userDAO.getUserByID(id);
		return user;
	}
	
	public boolean addUser(User user){
		return userDAO.insert(user);
	}
	
	public boolean updateUser(User user){
		return userDAO.update(user);
	}
	
	public boolean deleteUser(int id){
		User user = getUser(id);
		if(user == null) return false;
		return userDAO.delete(user);
	}
}