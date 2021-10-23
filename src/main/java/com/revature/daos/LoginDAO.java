package com.revature.daos;

import com.revature.models.User;
import com.revature.models.User.Role;

public interface LoginDAO {
	
	public User getByUsername(String username, String password,
        String firstName, String lastName, String email, Enum<Role> role);
}
