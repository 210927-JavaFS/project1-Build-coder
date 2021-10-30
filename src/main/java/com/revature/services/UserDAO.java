package com.revature.services;

import com.revature.models._User;


/**
 * fake the database
 * now i'm gonna grab the object persisted in the DB
 * this is gonna be a class cause i don't feel
 * like creating a DAO
 * 
 * normally this user would come back from the DB
 * I'd run a getById() or whatever but w/e to get the user
 * 
 * return this user to LoginService
 */


public class UserDAO {
	
	public _User getByUsername(String username) {
		if(username.equals("phil")) {
			return new _User("phil", "pass".hashCode(), "MANAGER");
		}
		return null;
	}

}