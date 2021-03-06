package com.revature.services;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.repos.TicketDAOImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketService {
	private TicketDAOImpl ticketDao = new TicketDAOImpl();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");

	
	public List<Ticket> getAllTickets(){
		return ticketDao.findAllTickets();
	}

	public List<Ticket>getAllTicketsByPending(){
		return ticketDao.findAllTicketsByPending();
	}

	public List<Ticket>getAllTicketsByApproved(){
		return ticketDao.findAllTicketsByApproved();
	}

	public List<Ticket>getAllTicketsByDenied(){
		return ticketDao.findAllTicketsByDenied();
	}

	// public List<Ticket>getAllTicketsByUser(id){
	// 	return ticketDao.findAllTicketsByUser(id);
	// }

	public Ticket getTicketById(int id){
		Ticket ticket = ticketDao.findById(id);
		return ticket;
	}

	public List<Ticket> getAllByAuthor(int id) {
        
        return ticketDao.findAllByAuthor(id);
    }

	public Ticket getTicketByName(String name){
		Ticket ticket = ticketDao.findByName(name);
		if (ticket!=null) {
			return ticket;
		} else {
			return new Ticket();
		}
	}
	
	public boolean addTicket(Ticket ticket){
		return ticketDao.addTicket(ticket);
	}
	
	public boolean updateTicket(Ticket ticket){
		myLogger.info("in updateTicket() service");
		myLogger.info(ticket.toString());
		return ticketDao.updateTicket(ticket);
	}
	
	public boolean deleteTicket(int id){
		Ticket ticket = getTicketById(id);
		return ticketDao.deleteTicket(ticket);
	}
}