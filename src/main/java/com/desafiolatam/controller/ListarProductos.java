package com.desafiolatam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.dto.ProductoCategoria;
import com.desafiolatam.service.Facade;

/**
 * Servlet implementation class ListarProductos
 */
@WebServlet("/listarProductos")
public class ListarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Facade facade = new Facade();
		
		List<ProductoCategoria> listaProductoCategoria = facade.findAllProductoCategoria();
		
		request.setAttribute("productos", listaProductoCategoria);
		
		request.getRequestDispatcher("listarProductos.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
