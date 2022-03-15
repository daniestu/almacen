package com.sinensia.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import com.sinensia.model.Product;

public class ProductDao{

	private static final Logger logger = Logger.getLogger(ProductDao.class.getName());
	
	public Integer save(Product producto, Session sesion) {
		logger.log(Level.INFO, "attaching Product instance");
		Integer productId = 0;
		try {
			productId = (Integer) sesion.save(producto);
			logger.log(Level.INFO, "attach succesfully");
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "attach failed");
			throw e;
		}

		return productId;
	}
	
	public boolean delete(Integer id, Session sesion) {
		logger.log(Level.INFO, "deleting Product instance");
		try {
			Product producto = (Product) sesion.get("com.sinensia.model.Product", id);
			sesion.delete(producto);
			logger.log(Level.INFO, "delete succesfully");
		} catch (RuntimeException e) {
			logger.log(Level.SEVERE, "delete failed");
			throw e;
		}
		return true;
	}
}
