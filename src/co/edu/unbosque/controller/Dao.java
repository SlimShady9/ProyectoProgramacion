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
import org.hibernate.result.NoMoreReturnsException;

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
	 * Método que abre una nueva sesión desde SessionFactory.
	 * @return Objeto de tipo Session.
	 */
	public static void abrirSession() {
		HibernateUtil.getSessionFactory();
	}


	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de administradores de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */

	public static void cargarAdministradores() {
		administradores = HibernateUtil.getSessionFactory().openSession().createCriteria(Administrador.class).list();
	}

	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de clientes de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */

	public static void cargarClientes() {
		clientes = HibernateUtil.getSessionFactory().openSession().createCriteria(Cliente.class).list();
	}

	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de gerentes de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	public static void cargarGerentes() {
		gerentes = HibernateUtil.getSessionFactory().openSession().createCriteria(Gerencia.class).list();
	}

	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de productos de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	public static void cargarProductos() {
		productos = HibernateUtil.getSessionFactory().openSession().createCriteria(Producto.class).list();
	}

	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de vendedores de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	public static void cargarVendedores() {
		vendedores = HibernateUtil.getSessionFactory().openSession().createCriteria(Vendedor.class).list();
	}

	/**
	 * Crea un objeto Criteria que devuelve instancias de la 
	 * clase del objeto de la lista de ventas de persistencia 
	 * cuando la aplicación ejecuta una consulta de criterios.
	 */
	public static void cargarVentas() {
		ventas = HibernateUtil.getSessionFactory().openSession().createCriteria(Ventas.class).list();
	}

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo cliente.
	 * @param cli que se metera a la base de datos
	 */
	public static void agregarCliente(Cliente cli) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

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

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar una nuva venta.
	 * @param vent que se metera a la base de datos
	 */

	public static void agregarVenta(Ventas vent) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()){

			tran = session.beginTransaction();
			session.save(vent);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo vendedor.
	 * @param vend que se metera a la base de datos
	 */

	public static void agregarVendedor(Vendedor vend) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

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

	/**
	 * Inicia un trasacción durante la sesión activa que permite mediante
	 * un parametro guardar un nuevo vendedor.
	 * @param vend que se metera a la base de datos
	 */

	public static void agregarAdmin(Administrador admin) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
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
	 * @param gen que se metera a la base de datos
	 */

	public static void agregarGerente(Gerencia gen) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

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
	 * @param pro que se metera a la base de datos
	 */

	public static void agregarProducto(Producto pro) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

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
		Session session = HibernateUtil.getSessionFactory().openSession();
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
	/**
	 * Actualiza la información del vendedor donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Vendedor.
	 * @param ven que se metera a la base de datos
	 * 
	 */	
	public static void actualizarVendedor(Vendedor ven) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
			buscar.setSede(ven.getSede());
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
	public static void actualizarVenta(Ventas vent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String id= vent.getArticulo();
		try {
			session.beginTransaction();
			Ventas buscar = (Ventas)session.get(Ventas.class, id);
			buscar.setUnidades(vent.getUnidades());
			buscar.setPrecio(vent.getPrecio());
			buscar.setReserva(vent.isReserva());
			buscar.setTipoPago(vent.getTipoPago());
			buscar.setSede(vent.getSede());
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
	public static void actualizarProducto(Producto prod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
	/**
	 * Actualiza la información del gerente donde se inicia una transacción 
	 * durante la sesión activa mediante un parametro de tipo Gerencia.
	 * @param ger
	 */
	public static void actualizarGerente(Gerencia ger) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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

	/**
	 * Elimina un cliente de la persistencia.
	 * @param cliente
	 */
	public static void eliminarCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(cliente);
		session.getTransaction().commit();


	}

	/**
	 * Elimina un vendedor de la persistencia.
	 * @param vendedor
	 */
	public static void eliminarVendedor(Vendedor vendedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(vendedor);
		session.getTransaction().commit();

	}

	/**
	 * Elimina un gerente de la persistencia.
	 * @param gerente
	 */
	public static void eliminarGerente(Gerencia gerente) {
		Session session = HibernateUtil.getHibernateSession();
		session.delete(gerente);
		session.getTransaction().commit();


	}

	/**
	 * Elimina un administrador de la persistencia.
	 * @param admin
	 */
	public static void eliminarAdministrador(Administrador admin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(admin);
		session.getTransaction().commit();


	}

	/**
	 * Elimina un producto de la persistencia.
	 * @param prod
	 */
	public static void eliminarProducto(Producto prod) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = session.beginTransaction();
		session.delete(prod);
		tr.commit();

	}
	/**
	 * Elimina un producto de la persistencia.
	 * @param venta
	 */
	public static void eliminarVenta(Ventas vent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(vent);
		session.getTransaction().commit();

	}
}
