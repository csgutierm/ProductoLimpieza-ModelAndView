package com.desafiolatam.dao;

import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.conexion.AdministradorConexion;
import com.desafiolatam.interfaces.CategoriaDao;
import com.desafiolatam.model.Categoria;

public class CategoriaDaoImp extends AdministradorConexion implements CategoriaDao {
	
	public CategoriaDaoImp() {
		conn = generaPoolConexion();
	}

	@Override
	public Categoria findById(int id) {

		Categoria categoria = new Categoria();

		try {
			pstm = conn.prepareStatement("SELECT * FROM categoria WHERE id_categoria=?");
			// los indice comienzan desde 1
			pstm.setInt(1, id);// seteamos en la columna 1
			rs = pstm.executeQuery();// ejecutando la query previamente preparada

			if (rs.next()) {
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setNombre(rs.getString("nombre_categoria"));
			}

			return categoria;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Categoria> findAll() {

		List<Categoria> listaCategorias = new ArrayList<Categoria>();


		try {

			pstm = conn.prepareStatement("SELECT * FROM categoria");

			rs = pstm.executeQuery();// ejecutando la query previamente preparada

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id_categoria"));
				categoria.setNombre(rs.getString("nombre_categoria"));

				listaCategorias.add(categoria);

			}
			return listaCategorias;

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Categoria>();
		}
	}

}
