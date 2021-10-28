package com.revature.controllers;

import java.util.List;

import com.revature.models.User;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller{

	private UserService userService = new UserService();
	
	public Handler getAllUsers = (ctx) -> {
		if (ctx.req.getSession(false)!=null) {
			List<User> list = userService.getAllUsers();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};
	
	public Handler getUser = (ctx) -> {
		if(ctx.req.getSession(false)!=null){
			User user = userService.getUserByName(ctx.pathParam("user"));
			ctx.json(user);
			ctx.status(200);
		}else{
			ctx.status(401);
		}
	};
	
	public Handler addUser = (ctx) -> {
		if(ctx.req.getSession(false)!=null){
			User user = ctx.bodyAsClass(User.class);
			if (userService.addUser(user)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		}else{
			ctx.status(401);
		}
	};
	
	public Handler updateUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			User user = ctx.bodyAsClass(User.class);
			if (userService.updateUser(user)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler deleteUser = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String user = ctx.pathParam("user");
			if (userService.deleteUser(user)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/users", this.getAllUsers);
		app.get("/users/:user", this.getUser);
		app.post("/users", this.addUser);
		app.put("/users", this.updateUser);
		app.delete("/users/:user", this.deleteUser);
	}
}