
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
	private Administrador admin;
	/**
	 * Clientes de la sede.
	 */
	private ArrayList<Cliente> clientes;

	/**
	 * Vendedores de la sede.
	 */
	private ArrayList<Vendedor> vendedores;
	/**
	 * Ventas de la sede.
	 */
	private int ventas;
	/**
	 * Nombre de la sede.
	 */
	private String nombreSede;
	/**
	 * Constructor por defecto.
	 */
	public Sede(){

	}

	/**
	 * Getter del administrador de la sede.
	 * @return admin.
	 */
	public Administrador getAdmin() {
		return admin;
	}
	/**
	 * Setter del administrador de la sede.
	 * @param admin.
	 */

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}
	/**
	 * Getter de los clientes de la sede.
	 * @return clientes.
	 */

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	/**
	 * Setter de los clientes de la sede.
	 * @param clientes.
	 */


	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * Getter de las ventas de la sede.
	 * @return ventas.
	 */
	public int getVentas() {
		return ventas;
	}
	/**
	 * Setter de las ventas de la sede.
	 * @param ventas.
	 */

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	/**
	 * Getter de los vendedores de la sede.
	 * @return vendedores.
	 */
	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}

	/**
	 * Setter de los vendedores de la sede.
	 * @param vendedores.
	 */
	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	/**
	 * Getter del nombre de la sede.
	 * @return nombreSede.
	 */

	public String getNombreSede() {
		return nombreSede;
	}

	/**
	 * Setter del nombre de la sede.
	 * @param nombreSede.
	 */
	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	/**
	 * Método toString() sobreescrito.
	 */
	@Override
	public String toString() {
		return "Sede [admin=" + admin + ", clientes=" + clientes + ", vendedores=" + vendedores + ", ventas=" + ventas
				+ "]";
	}




}