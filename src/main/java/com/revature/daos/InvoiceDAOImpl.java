package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Invoice;
import com.revature.utils.HibernateUtil;

public class InvoiceDAOImpl implements InvoiceDAO{

	@Override
	public List<Invoice> findAllInvoices() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Invoice").list();
	}

	@Override
	public Invoice findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(Invoice.class, id);
	}

	@Override
	public Invoice findByName(String name) {
		Session session = HibernateUtil.getSession();
		return session.get(Invoice.class, name);
	}

	@Override
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

	@Override
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

	@Override
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