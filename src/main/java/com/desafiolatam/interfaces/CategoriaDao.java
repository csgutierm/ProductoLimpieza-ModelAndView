package com.desafiolatam.interfaces;

import java.util.List;

import com.desafiolatam.model.Categoria;

public interface CategoriaDao {
	
	public Categoria findById(int id);//buscarCategoria
	public List<Categoria> findAll();//listarCategorias
}
