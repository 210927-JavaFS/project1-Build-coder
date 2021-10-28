package com.revature;

import com.revature.controllers.InvoiceController;
import com.revature.controllers.Controller;
import com.revature.controllers.UserController;
import com.revature.controllers._UserController;


import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
	
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create((config)->{
			config.addStaticFiles("/static", Location.CLASSPATH);
		});
		
		configure(new UserController(), new InvoiceController(), new _UserController());
		
		app.start(8081);

	}
	
	private static void configure(Controller... controllers) {
		for(Controller c:controllers) {
			c.addRoutes(app);
		}
	}

}