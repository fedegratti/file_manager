package com.flux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flux.domain.User;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class UserDAOImpl implements DAO<User> {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public void save(User user) {
		try {
			session.saveOrUpdate(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public void delete(Long userId) {
		try {
			User user = (User) session.get(User.class, userId);
			session.delete(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> list() {
		List<User> courses = null;
		try {
			courses = session.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}

	public User getById(Long userId) {
		User user = null;
		try {
			user = (User) session.get(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
