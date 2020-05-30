package co.edu.unbosque.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.result.NoMoreReturnsException;

import co.edu.unbosque.resources.HibernateUtil;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;


public class Dao {


	/**
	 * lalalala
	 * Esta clase tendra toda la persistencia del proyecto
	 * Asi como los arreglos traidos directos de la base de datos
	 * Esto para que el controlador maneje las secciones usando directamente esta clase
	 * 
	 */
	protected static List<Administrador> administradores;
	protected static List<Producto> productos;
	protected static List<Cliente> clientes;
	protected static List<Gerencia> gerentes;
	protected static List<Vendedor> vendedores;
	protected static List<Ventas> ventas;
	private static Session sesion = abrirSession();


	//Desde el sesscion factory
	public static Session abrirSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	
	public static void cargarAdministradores() {
		administradores = sesion.createCriteria(Administrador.class).list();
	}
	
	public static void cargarClientes() {
		clientes = sesion.createCriteria(Cliente.class).list();
	}
	
	public static void cargarGerentes() {
		gerentes = sesion.createCriteria(Gerencia.class).list();
	}
	
	public static void cargarProductos() {
		productos = sesion.createCriteria(Producto.class).list();
	}
	
	public static void cargarVendedores() {
		vendedores = sesion.createCriteria(Vendedor.class).list();
	}
	
	public static void cargarVentas() {
		ventas = sesion.createCriteria(Ventas.class).list();
	}

	public static void agregarCliente(Cliente cli) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getHibernateSession()){

			tran = session.beginTransaction();
			cli.setEstado("Inactivo");
			session.save(cli);
			tran.commit();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}

	}

	public static void agregarVendedor(Vendedor vend) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getHibernateSession()){

			tran = session.beginTransaction();
			vend.setEstado("Activo");
			session.save(vend);
			tran.commit();
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarAdmin(Administrador admin) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getHibernateSession()){
			tran = session.beginTransaction();
			session.save(admin);
			tran.commit();
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarGerente(Gerencia gen) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getHibernateSession()){

			tran = session.beginTransaction();
			session.save(gen);
			tran.commit();
			
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarProducto(Producto pro) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getHibernateSession()){

			tran = session.beginTransaction();
			session.save(pro);
			tran.commit();
			
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Estre trabajo de modificar se lo dejare a mi gran amigo @MiRey
	 * Confio en sus abilidades, por ahora no es vital para esta fase del proyecto
	 * Link: https://stackoverflow.com/questions/13102792/hibernate-update-with-entitymanager
	 * @param ven
	 */
	//Rey hizo esto, si esta mal fue otro xd
	@Transactional
	public static void actualizarCliente(Cliente persona) {
		Session session = HibernateUtil.getHibernateSession();
		String id= persona.getUsuario();

		try {
			Transaction tr = session.beginTransaction();
			Cliente buscar = (Cliente)session.get(Cliente.class, id);
			buscar.setContraseña(persona.getContraseña());
			buscar.setApellidos(persona.getApellidos());
			buscar.setCelular(persona.getCelular());
			buscar.setCiudad(persona.getCiudad());
			buscar.setEstado("Activo");
			buscar.setCorreo(persona.getCorreo());
			buscar.setNombres(persona.getNombres());
			buscar.setNumeroDocumento(persona.getNumeroDocumento());
			buscar.setTarjetaCredito(persona.getTarjetaCredito());
			buscar.setTipoDocumento(persona.getTipoDocumento());
			session.merge(buscar);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Rey hizo esto, si esta mal fue otro xd
	public static void actualizarVendedor(Vendedor ven) {
		Session session = HibernateUtil.getHibernateSession();
		System.out.println();
		System.out.println(ven.toString());

		String id= ven.getUsuario();
		try {
			session.beginTransaction();
			Vendedor buscar = (Vendedor)session.get(Vendedor.class, id);
			buscar.setApellidos(ven.getApellidos());
			buscar.setBanco(ven.getBanco());
			buscar.setContraseña(ven.getContraseña());
			buscar.setCorreo(ven.getCorreo());
			buscar.setEstado("Activo");
			buscar.setIdentificacion(ven.getIdentificacion());
			buscar.setNombres(ven.getNombres());
			buscar.setProductos(ven.getProductos());
			buscar.setSede(ven.getSede());
			session.merge(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void actualizarProducto(Producto prod) {
		Session session = HibernateUtil.getHibernateSession();
		String id= prod.getNombre();
		try {
			session.beginTransaction();
			Producto buscar = (Producto)session.get(Producto.class, id);
			buscar.setCantidad(prod.getCantidad());
			buscar.setCategoria(prod.getCategoria());
			buscar.setImagen(prod.getImagen());
			buscar.setPrecio(buscar.getPrecio());
			buscar.setNombre(prod.getNombre());
			session.merge(buscar);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void actualizarGerente(Gerencia ger) {
		Session session = HibernateUtil.getHibernateSession();
		String id= ger.getUsuario();
		try {
			session.beginTransaction();
			Gerencia buscar = (Gerencia)session.get(Gerencia.class, id);
			buscar.setApellidos(ger.getApellidos());
			buscar.setContraseña(ger.getContraseña());
			buscar.setCorreo(ger.getCorreo());
			buscar.setIdentificador(ger.getIdentificador());
			buscar.setNombres(ger.getNombres());
			session.merge(buscar);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void actualizarAdministrador(Administrador admin) {
		Session session = HibernateUtil.getHibernateSession();
		String id= admin.getUsuario();
		try {
			session.beginTransaction();
			Administrador buscar = (Administrador)session.get(Administrador.class, id);
			buscar.setApellidos(admin.getApellidos());
			buscar.setContraseña(admin.getContraseña());
			buscar.setCorreo(admin.getCorreo());
			buscar.setIdentificacion(admin.getIdentificacion());
			buscar.setNombres(admin.getNombres());
			buscar.setSede(admin.getSede());
			session.merge(buscar);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarCliente(Cliente cliente) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(cliente);
		session.getTransaction().commit();
		

	}

	public static void eliminarVendedor(Vendedor vendedor) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(vendedor);
		session.getTransaction().commit();
		
	}
	
	public static void eliminarGerente(Gerencia gerente) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(gerente);
		session.getTransaction().commit();
		

	}
	
	public static void eliminarAdministrador(Administrador admin) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(admin);
		session.getTransaction().commit();
		

	}
	
	public static void eliminarProducto(Producto prod) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(prod);
		session.getTransaction().commit();
		
	}
}
