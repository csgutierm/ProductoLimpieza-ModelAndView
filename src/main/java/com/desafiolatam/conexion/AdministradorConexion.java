package com.desafiolatam.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdministradorConexion {

	protected static Connection conn;
	protected PreparedStatement pstm = null;// variable para preparar las query
	protected ResultSet rs = null;

	// metodo para conectarse a la base de datos sin SINGLETON
//	protected Connection generaConexion() {
//
//		if (conn == null) {
//
//			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "modulo5", "admin");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			System.out.println("conexion existente");
//			return conn;
//		}
//
//		return conn;
//	}

	// metodo que genera la conexion mediante un singleton, valida que la instancia
	// de Connection exista o genera una nueva
	protected Connection generaPoolConexion() {

		Context initContext; // el contexto es para establecer en que parte se encuentra el DataSource (datos
								// de la base de datos)

		if (conn == null) {// generamos una nueva

			try {

				initContext = new InitialContext();// instanciar un nuevo initialContext
				// especificando en que ruta se encuentra el recurso de conexión
				DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ConexionOracle");
				conn = ds.getConnection();
				System.out.println("Conexión generada con singleton");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("hay una conexion utilizando el singleton");
			return conn;
		}
		return conn;
	}
}
