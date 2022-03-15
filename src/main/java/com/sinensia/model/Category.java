package com.sinensia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity()
@Table(name="categories")
public class Category implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="categoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	
	@Column(name="categoryName")
	private String nombre;
	
	@OneToMany(mappedBy="categoria", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> productos;
	
	public Category() {
		
	}
	
	public Category(String nombre, List<Product> productos) {
		this.nombre = nombre;
		this.productos = productos;
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Product> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Product> productos) {
		this.productos = productos;
	}
	
}
