package com.desafiolatam.dao;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.conexion.AdministradorConexion;
import com.desafiolatam.dto.ProductoCategoria;
import com.desafiolatam.interfaces.CategoriaDao;
import com.desafiolatam.interfaces.ProductoCategoriaDao;
import com.desafiolatam.interfaces.ProductoDao;
import com.desafiolatam.model.Categoria;
import com.desafiolatam.model.Producto;

public class ProductoCategoriaDaoImp extends AdministradorConexion implements ProductoCategoriaDao {

	@Override
	public List<ProductoCategoria> findAll() {

		ProductoDao productoDao = new ProductoDaoImp();
		CategoriaDao categoriaDao = new CategoriaDaoImp();

		List<ProductoCategoria> listaProductoCategoria = new ArrayList<ProductoCategoria>();

		for (Producto productoTemp : productoDao.findAll()) {

			//se crea producto en blanco para traspasar datos de acuerdo a una categoria 
			ProductoCategoria productoCategoria = new ProductoCategoria();

			// buscando la categoria de un producto de la lista recibida en el "for"
			// para adherirla luego
			Categoria categoria = categoriaDao.findById(productoTemp.getIdCategoria());

			productoCategoria.setProducto(productoTemp);// se setea el producto en productoCategoria
			productoCategoria.setCategoria(categoria);

			listaProductoCategoria.add(productoCategoria);
		}
		//otra forma de hacerlo
//		try {
//			pstm = conn.prepareStatement("SELECT * FROM PRODUCTO PRO INNER JOIN CATEGORIA CAT ON CAT.ID_CATEGORIA = PRO.ID_CATEGORIA");
//			rs = pstm.executeQuery();
//			
//			while (rs.next()) {
//				ProductoCategoria productoCategoria = new ProductoCategoria();//productoCategoria para agregarlo a la lista
//				Producto producto = new Producto();//producto para agregarlo a productoCategoria
//				Categoria categoria = new Categoria();//categoria para agregarla a productoCategoria
//				
////				productoCategoria.getProducto().setId(rs.getInt("id_producto"));
//				
//				producto.setId(rs.getInt("id_producto"));
//				producto.setNombre(rs.getString("nombre_producto"));
//				producto.setPrecio(rs.getInt("precio_producto"));
//				producto.setDescripcion(rs.getString("descripcion_producto"));
//				producto.setIdCategoria(rs.getInt("id_categoria"));
//				
//				categoria.setId(rs.getInt("id_categoria_1"));
//				categoria.setNombre(rs.getString("nombre_categoria"));
//				
//				productoCategoria.setProducto(producto);
//				productoCategoria.setCategoria(categoria);
//				
//				listaProductoCategoria.add(productoCategoria);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return listaProductoCategoria;
	}

}
