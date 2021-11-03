package com.revature.controllers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.repos.UserDAO;
import com.revature.repos.UserDAOImpl;
import com.revature.services.TicketService;
import com.revature.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketController implements Controller{

	private static Logger myLogger = LoggerFactory.getLogger("myLogger");
	private TicketService ticketService = new TicketService();
	private UserService userService = new UserService();

	public Handler findAllTickets = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			myLogger.info("in TicketController:findAllTickets()");

			List<Ticket> list = ticketService.getAllTickets();
			myLogger.info(list.get(0).getAmount() + " first ticket and it's amount retrieved from DAO");
			myLogger.info(list.get(0).getAuthor().getUserName() + " user name of author of first ticket");
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	public Handler findAllTicketsByPending = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Ticket> list = ticketService.getAllTicketsByPending();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};
	public Handler findAllTicketsByApproved= (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Ticket> list = ticketService.getAllTicketsByApproved();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};
	public Handler findAllTicketsByDenied = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<Ticket> list = ticketService.getAllTicketsByDenied();
			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	public Handler findAllByAuthor= (ctx) -> {
        myLogger.info("TicketController: getting a ticket by id");
		
		if (ctx.req.getSession(false) != null) {
            User author = userService.getUserById(Integer.parseInt(ctx.pathParam("author_id")));
            List<Ticket> tickets = ticketService.getAllByAuthor(author.getId());
            ctx.json(tickets);
            ctx.status(200);
        } else {
            ctx.status(401);
        }
	};
	
	// public Handler getTicket = (ctx) -> {
	// 	if (ctx.req.getSession(false) != null) {
	// 		try {
	// 			String idString = ctx.pathParam("ticket");
	// 			int id = Integer.parseInt(idString);
	// 			Ticket ticket = ticketService.getTicketById(id);
	// 			ctx.json(ticket);
	// 			ctx.status(200);
	// 		} catch (NumberFormatException e) {
	// 			e.printStackTrace();
	// 			ctx.status(406);
	// 		}
	// 	} else {
	// 		ctx.status(401);
	// 	}
	// };
	
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
		myLogger.info("in updateTicket() controller");
		
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
		app.get("/ticketsByPending", this.findAllTicketsByPending);
		app.get("/ticketsByApproved", this.findAllTicketsByApproved);
		app.get("/ticketsByDenied", this.findAllTicketsByDenied);
		app.get("/tickets/author/:author_id", this.findAllByAuthor);
		// app.get("/tickets/:ticket", this.getTicket);
		app.post("/tickets", this.addTicket);
		app.put("/tickets", this.updateTicket);
		app.delete("/tickets/:id", this.deleteTicket);
	}
}