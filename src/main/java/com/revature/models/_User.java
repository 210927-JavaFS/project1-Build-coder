package com.revature.models;

// this will be replaced by User

public class _User {
	
	private String username;
	private int password;
	private String role;
	
	public _User(String username, int password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getPassword() {
		return password;
	}
}