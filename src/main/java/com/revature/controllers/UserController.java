package com.revature.controllers;

import java.util.List;

import com.revature.models.User;
import com.revature.services.UserServices;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class UserController implements Controller{

	private UserServices UserService = new UserServices();
	
	public Handler getAllUsers = (ctx) -> {
		List<User> list = UserService.getAllUsers();
		ctx.json(list);
		ctx.status(200);
	};
	
	public Handler getUser = (ctx) -> {
		try {
			String idString = ctx.pathParam("Users");
			int id = Integer.parseInt(idString);
			User User = UserService.getUser(id);
			ctx.json(User);
			ctx.status(200);
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
			ctx.status(406);
		}
	};
	
	public Handler addUser = (ctx) -> {
		User User = ctx.bodyAsClass(User.class);
		if (UserService.addUser(User)) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};
	
	public Handler updateUser = (ctx) -> {
		User User = ctx.bodyAsClass(User.class);
		if (UserService.updateUser(User)) {
			ctx.status(200);
		} else {
			ctx.status(400);
		}
	};
	
	public Handler deleteUser = (ctx) -> {
		String id = ctx.pathParam("Users");
		try {
			int User = Integer.parseInt(id);
			if (UserService.deleteUser(User)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ctx.status(406);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/Users", this.getAllUsers);
		app.get("/Users/:User", this.getUser);
		app.post("/Users", this.addUser);
		app.put("/Users", this.updateUser);
		app.delete("/Users/:User", this.deleteUser);
	}
}