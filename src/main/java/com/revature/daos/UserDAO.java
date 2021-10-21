package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO 
{
	public List<User> getAllUsers();
	public User getUserByID(int id);
	public boolean insert(User user);
	public boolean update(User user);
	public boolean delete(User user);
}