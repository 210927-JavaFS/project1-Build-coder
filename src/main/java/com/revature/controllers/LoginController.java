package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.UserDTO;
import com.revature.services.LoginService;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

// import org.eclipse.jetty.server.Authentication.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller{
	
	private LoginService loginService = new LoginService();
	private UserService userService = new UserService();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");


	private Handler loginAttempt = (ctx) -> {
		myLogger.info("in LoginController:loginAttempt");
		UserDTO userDto = ctx.bodyAsClass(UserDTO.class);
		User loginUser = new User();
		loginUser = (User) loginService.login(userDto);
		myLogger.info(loginUser.toString());

		if(loginUser!=null) {
			ctx.req.getSession(); 
			ctx.json(loginUser);
			ctx.status(200);
		}else {
			ctx.req.getSession().invalidate();// invalidates any open session tracking the client.
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", this.loginAttempt);
	}
}