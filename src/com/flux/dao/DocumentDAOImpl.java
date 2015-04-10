package com.flux.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flux.domain.Document;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public class DocumentDAOImpl implements DAO<Document> {

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	public void save(Document document) {
		try {
			session.saveOrUpdate(document);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	public void delete(Long documentId) {
		try {
			Document document = (Document) session.get(Document.class,
					documentId);
			session.delete(document);
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Document> list() {
		List<Document> documents = null;
		try {
			documents = session.createQuery("from Document").list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documents;
	}

	public Document getById(Long documentId) {
		Document document = null;
		try {
			document = (Document) session.get(Document.class, documentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	public List<Document> searchByName(String sec) {
		List<Document> documents = new ArrayList<Document>();

		String queryString = "from Document d where d.name like :sec";
		Query query = session.createQuery(queryString).setString("sec",
				"%" + sec + "%");

		documents = query.list();
		return documents;
	}

	@SuppressWarnings("unchecked")
	public List<Document> searchByOwner(String sec) {
		System.out.println(sec);

		List<Document> documents = new ArrayList<Document>();

		String queryString = "SELECT d from Document d INNER JOIN d.owner o WHERE o.name like :sec";
		Query query = session.createQuery(queryString).setString("sec",
				"%" + sec + "%");

		documents = query.list();
		return documents;
	}

	@SuppressWarnings("unchecked")
	public List<Document> searchByDate(String sec) {
		List<Document> documents = null;

		String queryString = "SELECT d from Document d where DATE(d.uploadDate) = :sec";

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha;
		try {
			fecha = df.parse(sec);
		} catch (ParseException e1) {
			fecha = new Date();
		}

		Query query = session.createQuery(queryString).setDate("sec", fecha);
		documents = query.list();
		return documents;
	}
}
