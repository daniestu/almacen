package com.sinensia.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sinensia.dao.contracts.IDao;
import com.sinensia.model.Category;

public class CategoryDao implements IDao<Category>{

	private static final Logger logger = Logger.getLogger(CategoryDao.class.getName());
	private final SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory();

	@Override
	public Integer save(Category category) {
		logger.log(Level.INFO, "attaching Category instance");
		Integer categoryId = 0;
		Session sessionObj = null;
		Transaction tx = null;
		try {
			sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			categoryId = (Integer) sessionObj.save(category);
			
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "attach succesfully");
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "attach failed");
			tx.rollback();
			e.printStackTrace();
			throw e;
		}

		return categoryId;
	}

	@Override
	public List<Category> findAll() {
		logger.log(Level.INFO, "finding Category instance by example");

		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			sessionObj.beginTransaction();
			Query query = sessionObj.createQuery("FROM Category");
			@SuppressWarnings("unchecked")
			List<Category> categoryList = query.getResultList();
			sessionObj.close();

			return categoryList;
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "find by example failed");
			throw e;
		}
	}

	@Override
	public boolean delete(Integer id) {
		logger.log(Level.INFO, "deleting Category instance");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);
			
			sessionObj.delete(instance);
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "delete succesfully");
		} catch (RuntimeException e) {
			tx.rollback();
			logger.log(Level.SEVERE, "delete failed");
			throw e;
		}
		return true;
	}

	@Override
	public Category merge(Integer id) {
		logger.log(Level.INFO, "merging Category instance");
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			Transaction tx = sessionObj.beginTransaction();
			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);
			instance.setNombre("Merged");
			Category result = (Category) sessionObj.merge(instance);
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "merge succesfully");
			return result;
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "merge failed");
			throw e;
		}
	}

	@Override
	public Category findById(Integer id) {
		logger.log(Level.INFO, "getting Category instance with id: " + id);

		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			Transaction tx = sessionObj.beginTransaction();
			Category instance = (Category) sessionObj.get("com.sinensia.model.Category", id);
			if (instance == null) {
				logger.log(Level.INFO, "get succesfully, no instance found");
			}else {
				logger.log(Level.INFO, "get succesfully, instance found");
			}
			tx.commit();
			sessionObj.close();

			return instance;
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "get failed");
			throw e;
		}
	}


}
