/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Programación II
 * 
 * Profesor: Miguel Alejandro Feijoo García
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura María López Moreno
 * 	@author	Andrés Felipe Rey Pedraza
 * 	@author	Juan Camilo Díaz
 * 	@author	Camilo Andrés Romero Posada
 * 			
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package co.edu.unbosque.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;


/**
 * Servlet principal de la Aplicación
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Este constructor carga los datos de las tablas de la base de datos. 
     * Ademas de verificar si hay reservas co nfecha de expiracion <br>
     * <b>post: </b> La datos de la tablas de la base de datos se cargan.
     * <b> post: </b> Los productos en reserva con mas de tres dias se eliminaran
     * y el stock bolbera a los productos
     */
	
    public Controlador() {
//		DOMConfigurator.configure("src/log4j.xml");
        Presistence.cargarTablas();
        Ultilidades.verificarReservas();
    }

    /**
	 * Método que carga la página principal.xhtml despues de cargados los datos de las tablas de la base de datos.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher miDis = request.getRequestDispatcher("/Principal.xhtml");

		miDis.forward(request, response);
	}

	/**
	 * Método doPost que invoca el método doGet para cargar la página principal.xhtml. Se usa este método para evitar el envio
	 * y recepción de peticiones por la URL.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
