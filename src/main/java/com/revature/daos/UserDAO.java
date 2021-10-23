package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO 
{
	public List<User> findAllUsers();
	public User findById(int id);
	public User findByName(String name);
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
}