package com.desafiolatam.dao;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.conexion.AdministradorConexion;
import com.desafiolatam.interfaces.ProductoDao;
import com.desafiolatam.model.Producto;

public class ProductoDaoImp extends AdministradorConexion implements ProductoDao {

	public ProductoDaoImp() {
		conn = generaPoolConexion();
	}

	@Override
	public Producto findById(int id) {

		Producto producto = new Producto();

		try {
			pstm = conn.prepareStatement("SELECT * FROM producto WHERE id_producto=?");
			// los indice comienzan desde 1
			pstm.setInt(1, id);// seteamos en la columna 1

			rs = pstm.executeQuery();// ejecutando la query previamente preparada

			if (rs.next()) {
				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setIdCategoria(rs.getInt("id_Categoria"));
			}

			return producto;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Producto> findAll() {

		List<Producto> listaProductos = new ArrayList<Producto>();


		try {

			pstm = conn.prepareStatement("SELECT * FROM producto");

			rs = pstm.executeQuery();// ejecutando la query previamente preparada
			
			while (rs.next()) {
				Producto producto = new Producto();
				producto.setId(rs.getInt("id_producto"));
				producto.setNombre(rs.getString("nombre_producto"));
				producto.setDescripcion(rs.getString("descripcion_producto"));
				producto.setPrecio(rs.getInt("precio_producto"));
				producto.setIdCategoria(rs.getInt("id_Categoria"));

				listaProductos.add(producto);
			}
			return listaProductos;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Producto>();
		}

	}

	@Override
	public Producto add(Producto producto) {

		try {
			pstm = conn.prepareStatement("INSERT INTO producto VALUES (PRODUCTO_SEC.NEXTVAL,?,?,?,?)");
			// MYSQL la secuencia es null o cero (NULL,?,?,?,?)
			// orden de ingreso importa (de los pstm) -> por orden de insercion
			pstm.setString(1, producto.getNombre());
			pstm.setInt(2, producto.getPrecio());
			pstm.setString(3, producto.getDescripcion());
			pstm.setInt(4, producto.getIdCategoria());

			if (pstm.executeUpdate() == 1) {
				return producto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Producto();// devuelve producto vacio
	}

	@Override
	public Producto update(Producto producto) {

		try {
			pstm = conn.prepareStatement(
					"UPDATE producto SET nombre_producto=?, precio_producto=?, descripcion_producto=?, id_categoria=? WHERE id_producto=?");
			pstm.setString(1, producto.getNombre());
			pstm.setInt(2, producto.getPrecio());
			pstm.setString(3, producto.getDescripcion());
			pstm.setInt(4, producto.getIdCategoria());
			pstm.setInt(5, producto.getId());// revisar por qué posición 5

			if (pstm.executeUpdate() == 1) {
				return producto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Producto();
	}

	@Override
	public boolean delete(int id) {

		try {
			pstm = conn.prepareStatement("DELETE FROM producto WHERE id_producto=?");
			pstm.setInt(1, id);

			if (pstm.executeUpdate() == 1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int findLastId() {

		int maxId = -1;

		try {

			pstm = conn.prepareStatement("SELECT MAX(id_producto) AS max_id FROM producto");
			rs = pstm.executeQuery();

			if (rs.next()) {
				return maxId = rs.getInt(maxId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxId;
	}

}
