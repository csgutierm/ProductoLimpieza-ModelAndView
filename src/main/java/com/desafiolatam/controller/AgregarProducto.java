package com.desafiolatam.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.desafiolatam.dao.CategoriaDaoImp;
import com.desafiolatam.dao.ProductoDaoImp;
import com.desafiolatam.interfaces.CategoriaDao;
import com.desafiolatam.interfaces.ProductoDao;
import com.desafiolatam.model.Categoria;
import com.desafiolatam.model.Producto;
import com.desafiolatam.service.Facade;

/**
 * Servlet implementation class ModificarProducto
 */
@WebServlet("/agregarProducto")
public class AgregarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//test con 1
		int id = 1;
		

		ProductoDao productoDao = new ProductoDaoImp();
		CategoriaDao categoriaDao = new CategoriaDaoImp();

		Producto productoEncontrado = productoDao.findById(id);
		



		if (productoEncontrado.getId() != 0) {// si el productoEncontrdo su id es diferente de 0
			List<Categoria> listaCategorias = categoriaDao.findAll();// se buscan todas las categorias

			// se setean en el request los datos que se enviaran a la vista modificarProducto.jsp
			request.setAttribute("producto", null);
			request.setAttribute("categorias", listaCategorias);

			// se despacha hacia la vista modificarProducto.jsp
			request.getRequestDispatcher("agregarProducto.jsp").forward(request, response);
		} else {// si no
				// se despacha a una pagina de error para desplegar que ha sucedido un error
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	// metodo para recibir los datos del formulario de modificacion
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// obteniendo los datos del formulario
//		int id = Integer.parseInt(request.getParameter("id_producto"));
		int id = 1;
		String nombre = request.getParameter("nombre_producto");
		String descripcion = request.getParameter("descripcion_producto");
		int precio = Integer.parseInt(request.getParameter("precio_producto"));
		int idCategoria = Integer.parseInt(request.getParameter("id_categoria"));

		// seteamos un objeto para actualizar en la base de datos
		Producto producto = new Producto(id, nombre, descripcion, precio, idCategoria);

		// se invoca al Facade mediante una instancia para acceder al metodo de
		// actualizacion
		Facade facade = new Facade();
		Producto productoAdd = facade.addProducto(producto);// se actualiza y se recibe el producto actualizado

		if (productoAdd.getId() != 0) {// se verifica si se tiene un producto con id o retorno el metodo update un
											// new Producto()

			request.getRequestDispatcher("listarProductos").forward(request, response);

		} else {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
