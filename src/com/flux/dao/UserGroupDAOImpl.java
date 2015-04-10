package com.flux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flux.domain.UserGroup;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class UserGroupDAOImpl implements DAO<UserGroup> {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public void save(UserGroup user) {
		try {
			session.saveOrUpdate(user);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public void delete(Long userGroupId) {
		try {
			UserGroup userGroup = (UserGroup) session.get(UserGroup.class,
					userGroupId);
			session.delete(userGroup);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserGroup> list() {
		List<UserGroup> userGroups = null;
		try {
			userGroups = session.createQuery("from UserGroup").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroups;
	}

	public UserGroup getById(Long userGroupId) {
		UserGroup userGroup = null;
		try {
			userGroup = (UserGroup) session.get(UserGroup.class, userGroupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroup;
	}

}
