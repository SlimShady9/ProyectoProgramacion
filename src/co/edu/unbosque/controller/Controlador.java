/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogot� - Colombia)
 * Programa de Ingenier�a de Sistemas
 * Programaci�n II
 * 
 * Profesor: Miguel Alejandro Feijoo Garc�a
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura Mar�a L�pez Moreno
 * 	@author	Andr�s Felipe Rey Pedraza
 * 	@author	Juan Camilo D�az
 * 	@author	Camilo Andr�s Romero Posada
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

/**
 * Servlet principal de la Aplicaci�n
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Este constructor carga los datos de las tablas de la base de datos. <br>
     * <b>post: </b> La datos de la tablas de la base de datos se cargan.
     */
    public Controlador() {
        Presistence.cargarTablas();
    }

	/**
	 * M�todo que carga la p�gina principal.xhtml despues de cargados los datos de las tablas de la base de datos.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response).
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher miDis = request.getRequestDispatcher("/Principal.xhtml");
		miDis.forward(request, response);
	}

	/**
	 * M�todo doPost que invoca el m�todo doGet para cargar la p�gina principal.xhtml. Se usa este m�todo para evitar el envio
	 * y recepci�n de peticiones por la URL.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
