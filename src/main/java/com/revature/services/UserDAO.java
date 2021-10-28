package com.revature.services;

import com.revature.models._User;

public class UserDAO {
	
	public _User getByUsername(String username) {
		if(username.equals("pcoulson")) {
			return new _User("pcoulson", "capRocks".hashCode(), "Agent");
		}
		return null;
	}

}