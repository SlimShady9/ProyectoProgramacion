package co.edu.unbosque.controller;


import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;
public class Presistence {

	/**
	 * Simple manejo del Crud
	 * 
	 */

	public Presistence() {;
	}
	public static void cargarTablas() {

		Dao.abrirSession();
		Dao.cargarProductos();
		Dao.cargarClientes();
		Dao.cargarVendedores();
		Dao.cargarGerentes();
		Dao.cargarAdministradores();
		Dao.cargarVentas();

	}
	
	public static boolean modificarVendedorNOSQL(Vendedor ven) {
		Vendedor vende = buscarVendedor(ven.getUsuario());
		if (vende != null) {
			Dao.vendedores.set(Dao.vendedores.indexOf(vende), ven);
			return true;
		}
		return false;
	}
	public static boolean modificarClienteNOSQL(Cliente cli) {
		Cliente vende = buscarCliente(cli.getUsuario());
		if (vende != null) {
			Dao.clientes.set(Dao.clientes.indexOf(vende), cli);
			return true;
		}
		return false;
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
	public static boolean agregarVenta(Ventas vent) {
		if (buscarVenta(vent.getArticulo()) == null) {
			Dao.agregarVenta(vent);
			Dao.ventas.add(vent);
			return true;
		}
		return false;
	}
	public static boolean agregarProducto(Producto pro) {
		if (buscarProducto(pro.getNombre()) == null) {
			Dao.agregarProducto(pro);
			Dao.productos.add(pro);
			return true;
		}
		return false;
	}
	public static boolean agregarGerente(Gerencia ger) {
		if (buscarGerentes(ger.getUsuario()) == null) {
			Dao.agregarGerente(ger);
			Dao.gerentes.add(ger);
			return true;
		}
		return false;
	}
	public static boolean agregarAdministrador(Administrador admin) {
		if (buscarAdministrador(admin.getUsuario()) == null) {
			Dao.agregarAdmin(admin);
			Dao.administradores.add(admin);
			return true;
		}
		return false;
	}
	public static Producto buscarProducto(String nombre) {
		for (Producto i : Dao.productos) {
			if (i.getNombre().equals(nombre)) {
				return i;
			}
		}
		return null;
	}
	public static Ventas buscarVenta(String articulo) {
		for (Ventas i : Dao.ventas) {
			if (i.getArticulo().equals(articulo)) {
				return i;
			}
		}
		return null;
	}
	public static Cliente buscarCliente (String usuario) {
		for (Cliente i : Dao.clientes) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	public static Gerencia buscarGerentes (String usuario) {
		for (Gerencia i : Dao.gerentes) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	public static Vendedor buscarVendedor (String usuario) {
		for (Vendedor i : Dao.vendedores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}
	
	public static Administrador buscarAdministradorporUsuario (String usuario) {
		for (Administrador i : Dao.administradores) {
			if (i.getUsuario().equals(usuario)) {
				return i;
			}
		}
		return null;
	}

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
	public static boolean eliminarCliente(Cliente cli) {
		Cliente cliente = buscarCliente(cli.getUsuario());
		if (cliente != null) {
			Dao.eliminarCliente(cliente);
			Dao.clientes.remove(cliente);
			return true;
		}
		return false;

	}
	public static boolean eliminarProducto(Producto pro) {
		Producto prod = buscarProducto(pro.getNombre());
		if (prod != null) {
			Dao.eliminarProducto(prod);
			Dao.productos.remove(prod);
			return true;
		}
		return false;
	}
	public static boolean eliminarVenta(Ventas vent) {
		Ventas venta = buscarVenta(vent.getArticulo());
		if (venta != null) {
			Dao.eliminarVenta(venta);
			Dao.ventas.remove(venta);
			return true;
		}
		return false;
	}
	public static boolean eliminarVendedor(Vendedor vendedor) {
		Vendedor ven = buscarVendedor(vendedor.getUsuario());
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
		Administrador admin = buscarAdministrador(adminis.getUsuario());
		if (admin != null) {
			Dao.eliminarAdministrador(admin);
			Dao.administradores.remove(admin);
			return true;
		}
		return false;

	}
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
