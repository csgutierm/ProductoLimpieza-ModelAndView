package com.desafiolatam.service;

import java.util.List;

import com.desafiolatam.dao.CategoriaDaoImp;
import com.desafiolatam.dao.ProductoCategoriaDaoImp;
import com.desafiolatam.dao.ProductoDaoImp;
import com.desafiolatam.dto.ProductoCategoria;
import com.desafiolatam.interfaces.CategoriaDao;
import com.desafiolatam.interfaces.ProductoCategoriaDao;
import com.desafiolatam.interfaces.ProductoDao;
import com.desafiolatam.model.Categoria;
import com.desafiolatam.model.Producto;

public class Facade {

	// instancias para acceder a los metodos que existen en los data access object
	// (DAO)
	private ProductoDao productoDao = new ProductoDaoImp();
	private CategoriaDao categoriaDao = new CategoriaDaoImp();
	private ProductoCategoriaDao productoCategoriaDao = new ProductoCategoriaDaoImp();

	//metodo encargado de buscar el id accediendo al ProductoDao, accesador a la base de datos
	public Producto findProductoById(int id) {// buscarProducto
		return productoDao.findById(id);
	}

	//metodo encargado de listar los productos accediendo al ProductoDao, accesador a la base de datos
	public List<Producto> findProductoAll() {// listarProductos
		return productoDao.findAll();
	}

	//metodo encargado de añadir un producto accediendo al ProductoDao, accesador a la base de datos
	public Producto addProducto(Producto producto) {// agregarProducto
		return productoDao.add(producto);
	}

	//metodo encargado de actualizar un producto accediendo al ProductoDao, accesador a la base de datos
	public Producto updateProducto(Producto producto){// modificarProducto
		return productoDao.update(producto);
	}

	public boolean deleteProducto(int id){// eliminarProducto
		return productoDao.delete(id);
	}

	public int findLastIdProducto(){// obtenerUltimoId
		return productoDao.findLastId();
	}
	
	//------ CATEGORIA
	
	public Categoria findCategoriaById(int id) {
		return categoriaDao.findById(id);
	}
	
	public List<Categoria> findAllCategoria(){
		return categoriaDao.findAll();
	}
	
	//--- PRODUCTO CATEGORIA
	
	public List<ProductoCategoria> findAllProductoCategoria() {
		return productoCategoriaDao.findAll();
	}

}
