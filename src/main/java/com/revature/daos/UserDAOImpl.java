package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public List<User> findAllUsers() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Users").list();
	}

	@Override
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, id);
	}

	@Override
	public User findByName(String name) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, name);
	}

	@Override
	public boolean addUser(User user) {
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
	public boolean updateUser(User user) {
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
	public boolean deleteUser(User user) {
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