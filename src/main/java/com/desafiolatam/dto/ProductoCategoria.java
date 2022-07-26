package com.desafiolatam.dto;

import com.desafiolatam.model.Categoria;
import com.desafiolatam.model.Producto;

public class ProductoCategoria {
	
	private Producto producto;
	private Categoria categoria;
	
	public ProductoCategoria() {
	}

	public ProductoCategoria(Producto producto, Categoria categoria) {
		super();
		this.producto = producto;
		this.categoria = categoria;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "ProductoCategoria [producto=" + producto + ", categoria=" + categoria + "]";
	}	

}
