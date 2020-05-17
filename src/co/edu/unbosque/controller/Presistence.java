package co.edu.unbosque.controller;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import co.edu.unbosque.resources.HibernateUtil;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;

public class Presistence {

	protected static ArrayList<Administrador> administradores;
	protected static ArrayList<Producto> productos;
	protected static ArrayList<Cliente> clientes;
	protected static ArrayList<Gerencia> gerentes;
	protected static ArrayList<Vendedor> vendedores;
	
	public Presistence() {
		productos = new ArrayList<Producto>();
		clientes = new ArrayList<Cliente>();
		gerentes = new ArrayList<Gerencia>();
		vendedores = new ArrayList<Vendedor>();
	}
	public static void cargarTablas() {
		Dao.cargarProductos();
		
	}

}
