package com.revature.repos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.utils.HibernateUtil;

public class InvoiceDAOImpl{

	
	public List<Invoice> findAllInvoices() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Invoice").list();
	}

	
	public Invoice findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(Invoice.class, id);
	}

	
	public Invoice findByName(String name) {
		Session session = HibernateUtil.getSession();
		return session.get(Invoice.class, name);
	}

	
	public boolean addInvoice(Invoice invoice) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(invoice);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean updateInvoice(Invoice invoice) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(invoice);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteInvoice(Invoice invoice) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(invoice);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}