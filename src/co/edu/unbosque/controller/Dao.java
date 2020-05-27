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

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.edu.unbosque.resources.HibernateUtil;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;

/**
 * Data Access Object.
 * Esta clase gestiona la persistencia de la aplicación web.
 * Así mismo, contiene los arreglos con los datos traidos directos de la base de datos.
 *
 */
public class Dao {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Administrador;
	 * Un administrador tiene nombres, apellidos, usuario, contraseña, correo,
	 * indentificación y sede.
	 */
	protected static List<Administrador> administradores;
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Producto;
	 * Un producto tiene categoría, cantidad, id, imagen, nombre, precio y vendedor.
	 */
	protected static List<Producto> productos;
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Cliente;
	 * Un cliente tiene apellidos, celular, ciudad, compras, contraseña, correo, 
	 * nombres, numero de documento, tarjeta de crédito, tipo de documento y usuario.
	 */
	protected static List<Cliente> clientes;
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Gerencia;
	 * Un gerente tiene apellidos, contraseña, correo, identificación, nombres y usuario.
	 */
	protected static List<Gerencia> gerentes;
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Vendedor;
	 * Un vendedor tiene apellidos, banco, contraseña, correo, identificación, nombres, productos,
	 * sede, usuario y ventas.
	 */
	protected static List<Vendedor> vendedores;
	
	/**
	 * Objeto de tipo lista que contiene objetos de tipo Ventas;
	 * Una venta tiene artículo, fecha, id, precio, sede y unidades.
	 */
	protected static List<Ventas> ventas;
	
	/**
	 * Objeto tipo Session donde se abre una nueva sesión desde SessionFactory.
	 */
	private static Session sesion = abrirSession();
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Método que abre una nueva sesión desde SessionFactory.
	 * @return Objeto de tipo Session.
	 */
	public static Session abrirSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de administradores de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public static void cargarAdministradores() {
		administradores = sesion.createCriteria(Administrador.class).list();
	}
	
	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de clientes de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public static void cargarClientes() {
		clientes = sesion.createCriteria(Cliente.class).list();
	}
	
	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de gerentes de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public static void cargarGerentes() {
		gerentes = sesion.createCriteria(Gerencia.class).list();
	}
	
	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de productos de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public static void cargarProductos() {
		productos = sesion.createCriteria(Producto.class).list();
	}
	
	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de vendedores de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional
	public static void cargarVendedores() {
		vendedores = sesion.createCriteria(Vendedor.class).list();
	}

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo cliente.
	 * @param cli
	 */
	public static void agregarCliente(Cliente cli) {
		Transaction tran = null;
		try (Session session = sesion){

			tran = session.beginTransaction();
			session.save(cli);
			tran.commit();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}

	}

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo vendedor.
	 * @param vend
	 */
	public static void agregarVendedor(Vendedor vend) {
		Transaction tran = null;
		try (Session session = sesion){

			tran = session.beginTransaction();
			session.save(vend);
			tran.commit();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo administrador.
	 * @param admin
	 */
	public static void agregarAdmin(Administrador admin) {
		Transaction tran = null;
		try (Session session = sesion){

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

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo gerente.
	 * @param gen
	 */
	public static void agregarGerente(Gerencia gen) {
		Transaction tran = null;
		try (Session session = sesion){

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

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo producto.
	 * @param pro
	 */
	public static void agregarProducto(Producto pro) {
		Transaction tran = null;
		try (Session session = sesion){

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
	 * Actualiza la información del cliente donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Cliente.
	 * @param persona
	 */
	@Transactional
	public static void actualizarCliente(Cliente persona) {
		Session session = sesion;
		String id= persona.getUsuario();

		try {
			session.beginTransaction();
			Cliente buscar = (Cliente)session.get(Cliente.class, id);
			buscar.setContraseña(persona.getContraseña());
			buscar.setApellidos(persona.getApellidos());
			buscar.setCelular(persona.getCelular());
			buscar.setCiudad(persona.getCiudad());
			buscar.setCorreo(persona.getCorreo());
			buscar.setNombres(persona.getNombres());
			buscar.setNumeroDocumento(persona.getNumeroDocumento());
			buscar.setProductos(persona.getProductos());
			buscar.setTarjetaCredito(persona.getTarjetaCredito());
			buscar.setTipoDocumento(persona.getTipoDocumento());
			session.merge(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza la información del vendedor donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Vendedor.
	 * @param ven
	 */
	@Transactional
	public static void actualizarVendedor(Vendedor ven) {
		Session session = sesion;
		String id= ven.getUsuario();
		try {
			session.beginTransaction();
			Vendedor buscar = (Vendedor)session.get(Vendedor.class, id);
			buscar.setApellidos(ven.getApellidos());
			buscar.setBanco(ven.getBanco());
			buscar.setContraseña(ven.getContraseña());
			buscar.setCorreo(ven.getCorreo());
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
	
	/**
	 * Actualiza la información del producto donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Producto.
	 * @param prod
	 */
	@Transactional
	public static void actualizarProducto(Producto prod) {
		Session session = sesion;
		long id= prod.getId();
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
	
	/**
	 * Actualiza la información del gerente donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Gerencia.
	 * @param ger
	 */
	@Transactional
	public static void actualizarGerente(Gerencia ger) {
		Session session = sesion;
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
	
	/**
	 * Actualiza la información del administrador donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Administrador.
	 * @param admin
	 */
	@Transactional
	public static void actualizarAdministrador(Administrador admin) {
		Session session = sesion;
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
	
	/**
	 * Elimina un cliente de la persistencia.
	 * @param cliente
	 */
	public static void eliminarCliente(Cliente cliente) {
		Session session = sesion;
		session.delete(cliente);
		session.getTransaction().commit();

	}

	/**
	 * Elimina un vendedor de la persistencia.
	 * @param vendedor
	 */
	public static void eliminarVendedor(Vendedor vendedor) {
		Session session = sesion;
		session.delete(vendedor);
		session.getTransaction().commit();
	}
	
	/**
	 * Elimina un gerente de la persistencia.
	 * @param gerente
	 */
	public static void eliminarGerente(Gerencia gerente) {
		Session session = sesion;
		session.delete(gerente);
		session.getTransaction().commit();

	}
	
	/**
	 * Elimina un administrador de la persistencia.
	 * @param admin
	 */
	public static void eliminarAdministrador(Administrador admin) {
		Session session = sesion;
		session.delete(admin);
		session.getTransaction().commit();

	}
	
	/**
	 * Elimina un producto de la persistencia.
	 * @param prod
	 */
	public static void eliminarProducto(Producto prod) {
		Session session = sesion;
		session.delete(prod);
		session.getTransaction().commit();
	}
}
