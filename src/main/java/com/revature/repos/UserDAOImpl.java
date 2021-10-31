package com.revature.repos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDAOImpl{

	public static Logger myLogger = LoggerFactory.getLogger("myLogger");

	
	public List<User> findAllUsers() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM User").list();
	}

	
	public User findById(int id) {
		Session session = HibernateUtil.getSession();
		return session.get(User.class, id);
	}

	
	public User findByName(String user_name) {
		// myLogger.info("UserDAOBefore:name: " + user_name);
		// Session session = HibernateUtil.getSession();
		// myLogger.info("UserDAOAfter:name: " + user_name);
		// return session.get(User.class, user_name);

		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("FROM User u WHERE u.userName =:user_name");
		query.setParameter("user_name", user_name);
		return (User) query.getSingleResult();
		
	}

	
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