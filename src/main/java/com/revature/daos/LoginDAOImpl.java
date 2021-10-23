package com.revature.daos;

import com.revature.models.User;
import com.revature.models.User.Role;

public class LoginDAOImpl implements LoginDAO{

    @Override
    public User getByUsername(String username, String password, 
        String firstName, String lastName, String email, Enum<Role> role) {
        if(username.equals("phil") && (password.equals("pass"))) {
            return new User(username, password, "phil", "wood", 
            "me@gmail.com", null);
        }
        return null;
    }
}
