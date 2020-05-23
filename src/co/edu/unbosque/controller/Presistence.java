package co.edu.unbosque.controller;


import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
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
			Dao.clientes.add(cli);
			return true;
		}
		return false;
	}

	public static boolean agregarVendedor(Vendedor ven) {
		if (buscarCliente(ven.getUsuario()) == null) {
			Dao.agregarVendedor(ven);
			Dao.vendedores.add(ven);
			return true;
		}
		return false;
	}
	public static boolean agregarGerente(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			Dao.clientes.add(cli);
			return true;
		}
		return false;
	}
	public static boolean agregarAdministrador(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			Dao.clientes.add(cli);
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
	public static boolean actualizarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			cliente = cli;
			Dao.actualizarCliente(cliente);
			return true;
		}
		return false;
	}
	public static boolean actualizarVendedor(Vendedor ven) {
		Vendedor vendedor = buscarVendedores(ven.getUsuario());
		if (vendedor != null) {
			vendedor = ven;
			Dao.actualizarVendedor(vendedor);
			return true;
		}
		return false;
	}
	public static boolean actualizarGerente(Gerencia ger) {
		Gerencia gerente = buscarGerentes(ger.getUsuario());
		if (gerente != null) {
			gerente = ger;
			Dao.actualizarGerente(gerente);
			return true;
		}
		return false;
	}
	public static boolean actualizarAdmin(Administrador admin) {
		Administrador adminis = buscarAdministradores(admin.getUsuario());
		if (adminis != null) {
			adminis = admin;
			Dao.actualizarAdministrador(adminis);
			return true;
		}
		return false;
	}
	public static boolean eliminarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			Dao.eliminarCliente(cliente);
			Dao.clientes.remove(cliente);
			return true;
		}
		return false;

	}
	public static boolean eliminarVendedor(Vendedor vendedor) {
		Vendedor ven = buscarVendedores(vendedor.getUsuario());
		if (ven != null) {
			Dao.eliminarVendedor(ven);
			Dao.vendedores.remove(ven);
			return true;
		}
		return false;

	}
	public static boolean eliminarGerente(Gerencia gerencia) {
		Gerencia ger = buscarGerentes(gerencia.getUsuario());
		if (ger != null) {
			Dao.eliminarGerente(ger);
			Dao.gerentes.remove(ger);
			return true;
		}
		return false;

	}
	public static boolean eliminarAdministrador(Administrador adminis) {
		Administrador admin = buscarAdministradores(adminis.getUsuario());
		if (admin != null) {
			Dao.eliminarAdministrador(admin);
			Dao.administradores.remove(admin);
			return true;
		}
		return false;

	}

}
