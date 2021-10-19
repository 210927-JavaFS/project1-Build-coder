package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		// For many frameworks using JDBC or operating with JDBC it is necessary to
		// "register" the driver
		// you are using to make the framework aware of it.
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "javafs-210927.cqx6krx3pgbu.us-east-1.rds.amazonaws.com:5432/project1";
		String username = "postgres"; //It is possible to use env variables to hide this information
		String password = "password"; //you would access them with System.getenv("var-name");
		
		return DriverManager.getConnection(url, username, password);
	}
}