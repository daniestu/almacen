package com.sinensia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity()
@Table(name="products")
public class Product implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="productId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Column(name="productName")
	private String nombre;
	
	@ManyToOne
    @JoinColumn(name="categoryId", nullable=false)
	private Category categoria;
	
	public Product() {
		
	}
	
	public Product(String nombre, Category categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	public Integer getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Category getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Category categoria) {
		this.categoria = categoria;
	}
	
}
