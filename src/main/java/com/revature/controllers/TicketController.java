package com.revature.controllers;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.services.TicketService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class TicketController implements Controller{
    
	private TicketService ticketService = new TicketService();
	
	public Handler findAllTickets = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Ticket> list = ticketService.getAllTickets();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};
	
	public Handler getTicket = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("ticket");
				int id = Integer.parseInt(idString);
				Ticket ticket = ticketService.getTicketById(id);
				ctx.json(ticket);
				ctx.status(200);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler addTicket = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Ticket ticket = ctx.bodyAsClass(Ticket.class);
			if (ticketService.addTicket(ticket)) {
				ctx.status(201);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler updateTicket = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			Ticket ticket = ctx.bodyAsClass(Ticket.class);
			if (ticketService.updateTicket(ticket)) {
				ctx.status(200);
			} else {
				ctx.status(400);
			}
		} else {
			ctx.status(401);
		}
	};
	
	public Handler deleteTicket = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			String id = ctx.pathParam("ticket");
			try {
				int ticket = Integer.parseInt(id);
				if (ticketService.deleteTicket(ticket)) {
					ctx.status(200);
				} else {
					ctx.status(400);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				ctx.status(406);
			}
		} else {
			ctx.status(401);
		}
	};

	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/tickets", this.findAllTickets);
		app.get("/tickets/:ticket", this.getTicket);
		app.post("/tickets", this.addTicket);
		app.put("/tickets", this.updateTicket);
		app.delete("/tickets/:ticket", this.deleteTicket);
	}
}