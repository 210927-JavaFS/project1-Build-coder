package com.revature.repos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Ticket;
import com.revature.utils.HibernateUtil;

public class TicketDAOImpl{

	
	public List<Ticket> findAllTickets() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Ticket").list();
	}

	public List<Ticket> findAllTicketsByPending() {
		// String status = "PENDING";
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Ticket t WHERE t.status = 'PENDING'").list();
	}

	public List<Ticket> findAllTicketsByApproved() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Ticket t WHERE t.status = 'APPROVED'").list();
	}

	public List<Ticket> findAllTicketsByDenied() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Ticket t WHERE t.status = 'DENIED'").list();
	}
	
	public Ticket findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(Ticket.class, id);
	}

	
	public Ticket findByName(String name) {
		Session session = HibernateUtil.getSession();
		return session.get(Ticket.class, name);
	}

	
	public boolean addTicket(Ticket ticket) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(ticket);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean updateTicket(Ticket ticket) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(ticket);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteTicket(Ticket ticket) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(ticket);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}