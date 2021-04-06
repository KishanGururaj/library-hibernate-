package com.wolken.wolkenapp.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.wolken.wolkenapp.HibernateSessionFactory;
import com.wolken.wolkenapp.LibraryException;
import com.wolken.wolkenapp.dto.LibraryDTO;

public class LibraryDAOImpl implements LibraryDAO {
	Logger logger = Logger.getLogger("LibraryDAOImpl");

	@Override
	public boolean save(LibraryDTO libraryDTO) {
		// TODO Auto-generated method stub
		logger.info("inside save method in dao");
		Session session = HibernateSessionFactory.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(libraryDTO);
		transaction.commit();
		session.close();
		logger.info("complete  save method in dao");

		return true;

	}

	@Override
	public boolean updateByName(String name, String author) {
		// TODO Auto-generated method stub
		logger.info("inside update method in dao");
		// System.out.println("inside update ");
		Session session = HibernateSessionFactory.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.getNamedQuery("updateByName");
		query.setParameter("bookAuthor", author);
		query.setParameter("bookName", name);
		query.executeUpdate();
		session.close();
		logger.info("completed update method in dao");

		return true;

	}

	@Override
	public boolean deleteByName(String name) throws LibraryException {
		// TODO Auto-generated method stub
		logger.info("inside delete method in dao");
		if (name != null) {
			Session session = HibernateSessionFactory.getFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery("delete from LibraryDTO lr where lr.bookName=:bookName");
			query.setParameter("bookName", name);
			query.executeUpdate();
			session.close();
			logger.info("complete  delete method in dao");

			return true;
		} else {
			logger.error("custom exception shown");
			throw new LibraryException();
		}
	}

	@Override
	public List<LibraryDTO> getAll() {
		// TODO Auto-generated method stub
		logger.info("inside getall method in dao");
		Session session = HibernateSessionFactory.getFactory().openSession();
		Query query = session.getNamedQuery("selectAll");
		List<LibraryDTO> dtos = query.list();
		session.close();
		return dtos;
	}

}
