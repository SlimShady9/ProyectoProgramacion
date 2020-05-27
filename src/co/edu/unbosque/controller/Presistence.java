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


import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;

/**
 * Clase que gestiona el CRUD (Create, Read, Update, Delete) de la base de datos.
 */
public class Presistence {

	/**
	 * Constructor por defecto
	 */
	public Presistence() {
	}
	
	/**
	 * Carga todas las tablas de la base de datos.
	 */
	public static void cargarTablas() {

		Dao.cargarProductos();
		Dao.cargarClientes();
		Dao.cargarVendedores();
		Dao.cargarGerentes();
		Dao.cargarAdministradores();
	}
	
	/**
	 * Agrega un nuevo cliente a la tabla de clientes de la base de datos.
	 * @param cli. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarCliente(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			Dao.clientes.add(cli);
			return true;
		}
		return false;
	}

	/**
	 * Agrega un nuevo vendedor a la tabla de vendedores de la base de datos.
	 * @param ven. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarVendedor(Vendedor ven) {
		if (buscarCliente(ven.getUsuario()) == null) {
			Dao.agregarVendedor(ven);
			Dao.vendedores.add(ven);
			return true;
		}
		return false;
	}
	
	/**
	 * Agrega un nuevo gerente a la tabla de gerentes de la base de datos.
	 * @param cli. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarGerente(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			Dao.clientes.add(cli);
			return true;
		}
		return false;
	}
	
	/**
	 * Agrega un nuevo administrador a la tabla de administradores de la base de datos.
	 * @param cli. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarAdministrador(Cliente cli) {
		if (buscarCliente(cli.getUsuario()) == null) {
			Dao.agregarCliente(cli);
			Dao.clientes.add(cli);
			return true;
		}
		return false;
	}

	/**
	 * Busca un cliente a partir de su usuario de la base de datos.
	 * @param usuario. @return el cliente si logra encontrarlo, null de lo contrario.
	 */
	public static Cliente buscarCliente (String usuario) {
		for (Cliente i : Dao.clientes) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Busca un gerente a partir de su usuario de la base de datos.
	 * @param usuario. @return el geretne si logra encontrarlo, null de lo contrario.
	 */
	public static Gerencia buscarGerentes (String usuario) {
		for (Gerencia i : Dao.gerentes) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Busca un vendedor a partir de su usuario de la base de datos.
	 * @param usuario. @return el vendedor si logra encontrarlo, null de lo contrario.
	 */
	public static Vendedor buscarVendedores (String usuario) {
		for (Vendedor i : Dao.vendedores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Busca un administrador a partir de su usuario de la base de datos.
	 * @param usuario. @return el adminsitrador si logra encontrarlo, null de lo contrario.
	 */
	public static Administrador buscarAdministradores (String usuario) {
		for (Administrador i : Dao.administradores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Actualiza un cliente a partir de su usuario de la base de datos.
	 * @param cli. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			cliente = cli;
			Dao.actualizarCliente(cliente);
			return true;
		}
		return false;
	}
	
	/**
	 * Actualiza un vendedor a partir de su usuario de la base de datos.
	 * @param ven. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarVendedor(Vendedor ven) {
		Vendedor vendedor = buscarVendedores(ven.getUsuario());
		if (vendedor != null) {
			vendedor = ven;
			Dao.actualizarVendedor(vendedor);
			return true;
		}
		return false;
	}
	
	/**
	 * Actualiza un gerente a partir de su usuario de la base de datos.
	 * @param ger. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarGerente(Gerencia ger) {
		Gerencia gerente = buscarGerentes(ger.getUsuario());
		if (gerente != null) {
			gerente = ger;
			Dao.actualizarGerente(gerente);
			return true;
		}
		return false;
	}
	
	/**
	 * Actualiza un administrador a partir de su usuario de la base de datos.
	 * @param admin. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarAdmin(Administrador admin) {
		Administrador adminis = buscarAdministradores(admin.getUsuario());
		if (adminis != null) {
			adminis = admin;
			Dao.actualizarAdministrador(adminis);
			return true;
		}
		return false;
	}
	
	/**
	 * Elimina un cliente de la base de datos.
	 * @param cli. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			Dao.eliminarCliente(cliente);
			Dao.clientes.remove(cliente);
			return true;
		}
		return false;
	}
	
	/**
	 * Elimina un vendedor de la base de datos.
	 * @param vendedor. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarVendedor(Vendedor vendedor) {
		Vendedor ven = buscarVendedores(vendedor.getUsuario());
		if (ven != null) {
			Dao.eliminarVendedor(ven);
			Dao.vendedores.remove(ven);
			return true;
		}
		return false;

	}
	
	/**
	 * Elimina un gerente de la base de datos.
	 * @param gerencia. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarGerente(Gerencia gerencia) {
		Gerencia ger = buscarGerentes(gerencia.getUsuario());
		if (ger != null) {
			Dao.eliminarGerente(ger);
			Dao.gerentes.remove(ger);
			return true;
		}
		return false;

	}
	
	/**
	 * Elimina un administrador de la base de datos.
	 * @param adminis. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarAdministrador(Administrador adminis) {
		Administrador admin = buscarAdministradores(adminis.getUsuario());
		if (admin != null) {
			Dao.eliminarAdministrador(admin);
			Dao.administradores.remove(admin);
			return true;
		}
		return false;

	}
	
	/**
	 * Busca los administradores a partir de la sede.
	 * @param user
	 * @return un objeto tipo ArrayList con los administradores pertenecientes a la sede dada
	 * por párametro.
	 */
	public ArrayList<Administrador> busquedaAdmin(String user) {		
		ArrayList<Administrador> sofixd = new ArrayList<Administrador>(Dao.administradores);
		try {	
			for(int j=0;j<sofixd.size();j++) {
				if(!sofixd.get(j).getSede().equalsIgnoreCase(user)){
					sofixd.remove(j);
					j--;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sofixd;
	}
	
	/**
	 * Busca los clientes a partir de la ciudad.
	 * @param user
	 * @return un objeto tipo ArrayList con los clientes pertenecientes a la ciudad dada
	 * por párametro.
	 */
	public ArrayList<Cliente> busquedaCliente(String user) {		
		ArrayList<Cliente> sofixd = new ArrayList<Cliente>(Dao.clientes);
		try {	
			for(int j=0;j<sofixd.size();j++) {
				if(!sofixd.get(j).getCiudad().equalsIgnoreCase(user)){
					sofixd.remove(j);
					j--;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sofixd;
	}
	
	/**
	 * Busca los vendedores a partir de la sede.
	 * @param user
	 * @return un objeto tipo ArrayList con los vendedores pertenecientes a la sede dada
	 * por párametro.
	 */
	public ArrayList<Vendedor> busquedaVendedor(String user) {		
		ArrayList<Vendedor> sofixd = new ArrayList<Vendedor>(Dao.vendedores);
		try {	
			for(int j=0;j<sofixd.size();j++) {
				if(!sofixd.get(j).getSede().equalsIgnoreCase(user)){
					sofixd.remove(j);
					j--;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sofixd;
	}
	
	/**
	 * Busca los productos de los vendedores.
	 * @param user
	 * @return un objeto tipo ArrayList con los productos pertenecientes 
	 * a los vendedores dados por párametro.
	 */
	public ArrayList<Producto> busquedaProductos(ArrayList<Vendedor> user) {	
		ArrayList<Producto> sofixd = new ArrayList<Producto>();
		try {			
			for(int j =0;j<user.size();j++) {
				List<Producto> we = user.get(j).getProductos();
				for(int i=0;i<we.size();i++) {
					Producto xd = we.get(i);
					sofixd.add(xd);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sofixd;
	}
	
	/**
	 * Busca las ventas a partir de los vendedores.
	 * @param user
	 * @return un objeto tipo ArrayList con las ventas ertenecientes a 
	 * los vendedores dados por párametro.
	 */
	public ArrayList<Ventas> busquedaVentas(ArrayList<Vendedor> user) {	
		ArrayList<Ventas> sofixd = new ArrayList<Ventas>();
		try {			
			for(int j =0;j<user.size();j++) {
				List<Ventas> we = user.get(j).getVentas();
				for(int i=0;i<we.size();i++) {
					Ventas xd = we.get(i);
					sofixd.add(xd);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sofixd;
	}
}
