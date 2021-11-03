package com.revature.repos;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.utils.CollectionUtil;
import com.revature.utils.HibernateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketDAOImpl implements TicketDAO{

	public static Logger myLogger = LoggerFactory.getLogger("myLogger");
 
	public List<Ticket> findAllTickets() {

		try {
			Session session = HibernateUtil.getSession();
			return session.createQuery("FROM Ticket").list();

		}catch(HibernateException e){
			e.printStackTrace();

			return null;
		}
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

    @Override
    public List<Ticket> findAllByAuthor(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("FROM Ticket WHERE author_id = :author");
		query.setParameter("author", id);
		List<Ticket> tickets = CollectionUtil.castList(Ticket.class, query.getResultList());
		return tickets;
    }

	
	public Ticket findByName(String name) {
		Session session = HibernateUtil.getSession();
		return session.get(Ticket.class, name);
	}

	
	public boolean addTicket(Ticket ticket) {
		try {
			myLogger.info("In addTicket DAO");
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
		myLogger.info("in updateTicket() dao");
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

	@Override
	public Ticket findById(int id) {
		Session session = HibernateUtil.getSession();
		Ticket ticket = session.get(Ticket.class, id);
		HibernateUtil.closeSession();
		return ticket;
	}
}