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

	public Presistence() {;
	}
	/**
	 * Carga todas las tablas de la base de datos.
	 */
	public static void cargarTablas() {

		Dao.abrirSession();
		Dao.cargarProductos();
		Dao.cargarClientes();
		Dao.cargarVendedores();
		Dao.cargarGerentes();
		Dao.cargarAdministradores();
		Dao.cargarVentas();

	}
	/**
	 * Modifica a un vendedor en la tabla de clientes de la base de datos.
	 * @param ven. 
	 * @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean modificarVendedorNOSQL(Vendedor ven) {
		Vendedor vende = buscarVendedor(ven.getUsuario());
		if (vende != null) {
			Dao.vendedores.set(Dao.vendedores.indexOf(vende), ven);
			return true;
		}
		return false;
	}
	/**
	 * Modifica a un cliente en la tabla de clientes de la base de datos.
	 * @param cli. 
	 * @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean modificarClienteNOSQL(Cliente cli) {
		Cliente vende = buscarCliente(cli.getUsuario());
		if (vende != null) {
			Dao.clientes.set(Dao.clientes.indexOf(vende), cli);
			return true;
		}
		return false;
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
	 * Agrega una nueva venta a la tabla de vendedores de la base de datos.
	 * @param vent. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarVenta(Ventas vent) {
		if (buscarVenta(vent.getArticulo()) == null) {
			Dao.agregarVenta(vent);
			Dao.ventas.add(vent);
			return true;
		}
		return false;
	}
	/**
	 * Agrega un nuevo producto a la tabla de vendedores de la base de datos.
	 * @param ven. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarProducto(Producto pro) {
		if (buscarProducto(pro.getNombre()) == null) {
			Dao.agregarProducto(pro);
			Dao.productos.add(pro);
			return true;
		}
		return false;
	}
	/**
	 * Agrega un nuevo gerente a la tabla de gerentes de la base de datos.
	 * @param ger. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarGerente(Gerencia ger) {
		if (buscarGerentes(ger.getUsuario()) == null) {
			Dao.agregarGerente(ger);
			Dao.gerentes.add(ger);
			return true;
		}
		return false;
	}
	/**
	 * Agrega un nuevo administrador a la tabla de administradores de la base de datos.
	 * @param cli. @return True si logra agregarlo, flase de lo contrario.
	 */
	public static boolean agregarAdministrador(Administrador admin) {
		if (buscarAdministrador(admin.getUsuario()) == null) {
			Dao.agregarAdmin(admin);
			Dao.administradores.add(admin);
			return true;
		}
		return false;
	}
	/**
	 * Busca un producto a partir de su usuario de la base de datos.
	 * @param nombre. @return el producto si logra encontrarlo, null de lo contrario.
	 */
	public static Producto buscarProducto(String nombre) {
		for (Producto i : Dao.productos) {
			if (i.getNombre().equals(nombre)) {
				return i;
			}
		}
		return null;
	}
	/**
	 * Busca una venta a partir de su usuario de la base de datos.
	 * @param articulo. @return la venta si logra encontrarlo, null de lo contrario.
	 */
	public static Ventas buscarVenta(String articulo) {
		for (Ventas i : Dao.ventas) {
			if (i.getArticulo().equals(articulo)) {
				return i;
			}
		}
		return null;
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
	public static Vendedor buscarVendedor (String usuario) {
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
	public static Administrador buscarAdministradorporUsuario (String usuario) {
		for (Administrador i : Dao.administradores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	/**
	 * Busca un administrador a partir de su sede de la base de datos.
	 * @param sede. @return el adminsitrador si logra encontrarlo, null de lo contrario.
	 */
	public static Administrador buscarAdministrador (String sede) {
		for (Administrador i : Dao.administradores) {
			if (i.getSede().equals(sede)) {
				return i;
			}
		}
		return null;
	}
	public static boolean actualizarProducto(Producto prod) {
		Producto producot = buscarProducto(prod.getNombre());
		if (producot != null) {
			Dao.productos.set(Dao.productos.indexOf(producot), prod);
			producot = prod;
			Dao.actualizarProducto(producot);
			return true;
		}
		return false;
	}
	/**
	 * Actualiza una venta a partir del nombre de articulo de la base de datos.
	 * @param vent. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarVenta(Ventas vent) {
		Ventas venta = buscarVenta(vent.getArticulo());
		if (venta != null) {
			Dao.ventas.set(Dao.productos.indexOf(venta), vent);
			venta = vent;
			Dao.actualizarVenta(venta);
			return true;
		}
		return false;
	}
	/**
	 * Actualiza un cliente a partir de su usuario de la base de datos.
	 * @param cli. @return true si logra actualizarlo, false de lo contrario.
	 */
	public static boolean actualizarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			Dao.clientes.set(Dao.clientes.indexOf(cliente), cli);
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
		Vendedor vendedor = buscarVendedor(ven.getUsuario());
		if (vendedor != null) {
			Dao.vendedores.set(Dao.vendedores.indexOf(vendedor), ven);
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
			Dao.gerentes.set(Dao.gerentes.indexOf(gerente), ger);
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
		Administrador adminis = buscarAdministrador(admin.getUsuario());
		if (adminis != null) {
			Dao.administradores.set(Dao.administradores.indexOf(adminis), admin);
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
	 * Elimina un producto de la base de datos.
	 * @param vendedor. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarProducto(Producto pro) {
		Producto prod = buscarProducto(pro.getNombre());
		if (prod != null) {
			Dao.eliminarProducto(prod);
			Dao.productos.remove(prod);
			return true;
		}
		return false;
	}
	/**
	 * Elimina una venta de la base de datos.
	 * @param vent. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarVenta(Ventas vent) {
		Ventas venta = buscarVenta(vent.getArticulo());
		if (venta != null) {
			Dao.eliminarVenta(venta);
			Dao.ventas.remove(venta);
			return true;
		}
		return false;
	}
	/**
	 * Elimina un vendedor de la base de datos.
	 * @param vendedor. @return true si logra eliminarlo, false de lo contrario.
	 */
	public static boolean eliminarVendedor(Vendedor vendedor) {
		Vendedor ven = buscarVendedor(vendedor.getUsuario());
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
		Administrador admin = buscarAdministrador(adminis.getUsuario());
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
	public static Administrador busquedaAdministradores(String sede) {		
		ArrayList<Administrador> sofixd = new ArrayList<Administrador>(Dao.administradores);
		Administrador admin = null ;
		try {	
			for(int j=0;j<sofixd.size();j++) {
				if(sofixd.get(j).getSede().equalsIgnoreCase(sede)){
					admin =sofixd.get(j);
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	/**
	 * Busca los clientes a partir de la ciudad.
	 * @param user
	 * @return un objeto tipo ArrayList con los clientes pertenecientes a la ciudad dada
	 * por párametro.
	 */
	public static ArrayList<Cliente> busquedaClientes(String user) {		
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
	public static ArrayList<Vendedor> busquedaVendedores(String user) {		
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
	public static ArrayList<Producto> busquedaProductos(ArrayList<Vendedor> user) {	
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
	public static ArrayList<Ventas> busquedaVentas(ArrayList<Vendedor> user) {	
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
	/**
	 * Busca los productos a partir de una categoria en la sede.
	 * @param categoria.
	 * @param proDeSede
	 * @return un objeto tipo ArrayList con los productos de una categora en la sede
	 */
	public static ArrayList<Producto> buscarProductosPorCategoria(String categoria, ArrayList<Producto> proDeSede){
		ArrayList<Producto> pro = new ArrayList<Producto>(proDeSede);
		try {	
			for(int j=0;j<pro.size();j++) {
				if(!pro.get(j).getCategoria().equalsIgnoreCase(categoria)){
					pro.remove(j);
					j--;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}
	/**
	 * Busca los productos a partir de un nombre en la sede.
	 * @param categoria.
	 * @param proDeSede
	 * @return un objeto tipo ArrayList con los productos que coincida con el nombre en la sede
	 */
	public static ArrayList<Producto> buscarProductosPorNombre(String nombre, ArrayList<Producto> proDeSede){
		ArrayList<Producto> pro = new ArrayList<Producto>(proDeSede);
		try {	
			for(int j=0;j<pro.size();j++) {
				if(!pro.get(j).getNombre().equalsIgnoreCase(nombre)){
					pro.remove(j);
					j--;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pro;
	}

}