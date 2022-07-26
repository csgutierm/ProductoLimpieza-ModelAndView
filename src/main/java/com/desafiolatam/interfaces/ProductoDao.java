package com.desafiolatam.interfaces;

import java.util.List;

import com.desafiolatam.model.Producto;

public interface ProductoDao {
	
	public Producto findById(int id);//buscarProducto
	public List<Producto> findAll();//listarProductos
	public Producto add(Producto producto);//agregarProducto
	public Producto update(Producto producto);//modificarProducto
	public boolean delete(int id);//eliminarProducto
	public int findLastId();//obtenerUltimoId	
}
