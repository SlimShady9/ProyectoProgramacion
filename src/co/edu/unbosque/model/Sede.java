package co.edu.unbosque.model;

import java.util.ArrayList;

public class Sede {

	/**
	 * Esta clase sede sera la contenedora de la informacion acerca de quien
	 * es el encargado, los clinetes y los vendedores respectivos
	 * 
	 */
	private Administrador admin;
	private ArrayList<Cliente> clientes;
	private ArrayList<Vendedor> vendedores;
	private int ventas;
	private String nombreSede;
	
	public Sede(){
		
	}


	public Administrador getAdmin() {
		return admin;
	}


	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}


	public ArrayList<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}


	public int getVentas() {
		return ventas;
	}


	public void setVentas(int ventas) {
		this.ventas = ventas;
	}


	public ArrayList<Vendedor> getVendedores() {
		return vendedores;
	}


	public void setVendedores(ArrayList<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	


	public String getNombreSede() {
		return nombreSede;
	}


	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}


	@Override
	public String toString() {
		return "Sede [admin=" + admin + ", clientes=" + clientes + ", vendedores=" + vendedores + ", ventas=" + ventas
				+ "]";
	}


	
	
}
