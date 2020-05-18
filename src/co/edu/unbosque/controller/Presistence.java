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

	/**
	 * Simple manejo del Crud
	 * 
	 */
	
	public Presistence() {;
	}
	public static void cargarTablas() {
		Dao.cargarProductos();
		Dao.cargarClientes();
		Dao.cargarVendedores();
		Dao.cargarGerentes();
		Dao.cargarAdministradores();
	}
	/**
	 * Metodos de insercion ala base de datos
	 */
	public static boolean agregarCliente(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			return true;
		}
		return false;
	}
	
	public static Cliente buscarCliente (String usuario) {
		for (Cliente i : Dao.clientes) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	public static Gerencia buscarGerentes (String id) {
		for (Gerencia i : Dao.gerentes) {
			if (i.getIdentificador().equals(id)) {
				return i;
			}
		}
		return null;
	}
	public static Vendedor buscarVendedores (String usuario) {
		for (Vendedor i : Dao.vendedores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	public static Administrador buscarAdministradores (String usuario) {
		for (Administrador i : Dao.administradores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}

}
