package com.revature.repos;

import com.revature.models.Ticket;
import com.revature.models.User;

import java.util.List;

public interface TicketDAO {
    public List<Ticket> findAllTickets();

    public List<Ticket> findAllTicketsByPending();


    public List<Ticket> findAllTicketsByApproved();

    public List<Ticket> findAllTicketsByDenied();

    public Ticket findById(int id);

    public Ticket findByName(String name);

    public boolean addTicket(Ticket ticket);

    public boolean updateTicket(Ticket ticket);

    public boolean deleteTicket(Ticket ticket);

    public List<Ticket> findAllByAuthor(int id);
}
