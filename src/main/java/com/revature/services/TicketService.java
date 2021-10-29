package com.revature.services;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.repos.TicketDAOImpl;

public class TicketService {
	private TicketDAOImpl ticketDao = new TicketDAOImpl();
	
	public List<Ticket> getAllTickets(){
		return ticketDao.findAllTickets();
	}

	public Ticket getTicketById(int id){
		Ticket ticket = ticketDao.findById(id);
		return ticket;
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
		return ticketDao.updateTicket(ticket);
	}
	
	public boolean deleteTicket(int id){
		Ticket ticket = getTicketById(id);
		return ticketDao.deleteTicket(ticket);
	}
}