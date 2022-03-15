package com.sinensia.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sinensia.model.Category;
import com.sinensia.model.Product;

public class CategoyDaoTest {

	CategoryDao categoryDao = null;
	static Integer idUpdated = 0;
	static Integer idDeleted = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		List<Product> productosHerramientas = new ArrayList<Product>();
		List<Product> productosBorrados = new ArrayList<Product>();
		
		Category category = new Category();
		category.setNombre("Herramientas");
		
		Category category1 = new Category();
		category1.setNombre("Deleted");
		
		Product martillo = new Product();
		martillo.setNombre("martillo");
		martillo.setCategoria(category);
		productosHerramientas.add(martillo);
		
		Product borrado = new Product();
		borrado.setNombre("borrado");
		borrado.setCategoria(category1);
		productosBorrados.add(borrado);
		
		category.setProductos(productosHerramientas);
		category1.setProductos(productosBorrados);
		
		CategoryDao categoryDaoBeforeClass = new CategoryDao();
		
		idUpdated = categoryDaoBeforeClass.save(category);
		idDeleted = categoryDaoBeforeClass.save(category1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		categoryDao = new CategoryDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		List<Product> productos = new ArrayList<Product>();
		Category category = new Category();
		category.setNombre("Muebles");
		
		Product product1 = new Product();
		product1.setNombre("silla");
		product1.setCategoria(category);
		
		Product product2 = new Product();
		product2.setNombre("mesa");
		product2.setCategoria(category);
		
		productos.add(product1);
		productos.add(product2);
		category.setProductos(productos);
		
		assertTrue(categoryDao.save(category) > 0);
	}
	
	@Test(expected=HibernateException.class)
	public void testSaveHibernateExpectedException() {
		List<Product> productos = new ArrayList<Product>();
		Category category = new Category();
		category.setNombre("Comida");
		
		Product product1 = new Product();
		product1.setNombre("pollo");
		product1.setCategoria(category);
		
		Product product2 = new Product();
		//product2.setNombre("patata");
		product2.setCategoria(category);
		
		productos.add(product1);
		productos.add(product2);
		category.setProductos(productos);
		
		assertTrue(categoryDao.save(category) > 0);
	}
	
	@Test
	public void testFindAll() {
		assertTrue(categoryDao.findAll().size() > 0);
	}

	@Test
	public void testDelete() {
		assertTrue(categoryDao.delete(idDeleted));
	}

	@Test
	public void testMerge() {
		assertTrue(categoryDao.merge(idUpdated) != null);
	}

	@Test
	public void testFindById() {
		assertTrue(categoryDao.findById(idUpdated) != null);
	}

}
