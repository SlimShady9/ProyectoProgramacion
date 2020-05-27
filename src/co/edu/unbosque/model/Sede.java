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
