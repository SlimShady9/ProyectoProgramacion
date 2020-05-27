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
package co.edu.unbosque.model;

import java.util.ArrayList;

/**
 * Esta clase sede sera la contenedora de la informacion acerca de quien
 * es el encargado, los clinetes y los vendedores respectivos.
 */
public class Sede {

	/**
	 * Administrador de la sede.
	 */
	@SuppressWarnings("unused")
	private Administrador admin;
	
	/**
	 * Clientes de la sede.
	 */
	@SuppressWarnings("unused")
	private ArrayList<Cliente> clientes;
	
	/**
	 * Vendedores de la sede.
	 */
	@SuppressWarnings("unused")
	private ArrayList<Vendedor> vendedores;
	
	/**
	 * Constructor por defecto.
	 */
	public Sede(){
		
	}
}
