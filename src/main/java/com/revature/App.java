package com.revature;

import com.revature.controllers.*;

import io.javalin.Javalin;

public class App {
	
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create();
		
		configure(new EmployeeController(), new FinanceManagerController());
		
		app.start();

	}

	// ide says I need this method
	private static void configure(EmployeeController employeeController,
			FinanceManagerController financeManagerController) {
	}

	public static void configure(Controller... controllers) {
		for(Controller c: controllers) {
			c.addRoutes(app);
		}
	}
}