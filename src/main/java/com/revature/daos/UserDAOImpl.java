package com.revature.daos;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public List<User> getAllUsers() {
		Session session = HibernateUtil.getSession();
		List<User> users;
		CriteriaBuilder critBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = critBuilder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		CriteriaQuery<User> allUsers = query.select(root);
		TypedQuery<User> typed = session.createQuery(allUsers);
		
		users = typed.getResultList();
		return users;
	}

	@Override
	public User getUserByID(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, id);
	}

	@Override
	public boolean insert(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean update(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}	
	}

	@Override
	public boolean delete(User user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}
}