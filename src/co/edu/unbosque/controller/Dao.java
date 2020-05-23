package co.edu.unbosque.controller;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import co.edu.unbosque.resources.HibernateUtil;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;


public class Dao {


	/**
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


	//Desde el sesscion factory
	private static Session abrirSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	public static void cargarAdministradores() {
		administradores = abrirSession().createCriteria(Administrador.class).list();
	}

	public static void cargarClientes() {
		clientes = abrirSession().createCriteria(Cliente.class).list();
	}

	public static void cargarGerentes() {
		gerentes = abrirSession().createCriteria(Gerencia.class).list();
	}

	public static void cargarProductos() {
		productos = abrirSession().createCriteria(Producto.class).list();
	}

	public static void cargarVendedores() {
		vendedores = abrirSession().createCriteria(Vendedor.class).list();
	}

	public static void agregarCliente(Cliente cli) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			tran = session.beginTransaction();
			session.save(cli);
			tran.commit();
			session.close();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}

	}

	public static void agregarVendedor(Vendedor vend) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			tran = session.beginTransaction();
			session.save(vend);
			tran.commit();
			session.close();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarAdmin(Administrador admin) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			tran = session.beginTransaction();
			session.save(admin);
			tran.commit();
			session.close();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarGerente(Gerencia gen) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			tran = session.beginTransaction();
			session.save(gen);
			tran.commit();
			session.close();

		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void agregarProducto(Producto pro) {
		Transaction tran = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			tran = session.beginTransaction();
			session.save(pro);
			tran.commit();
			session.close();

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
	public static void actualizarCliente(Cliente persona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
			session.update(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Rey hizo esto, si esta mal fue otro xd
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
			buscar.setIdentificacion(ven.getIdentificacion());
			buscar.setNombres(ven.getNombres());
			buscar.setProductos(ven.getProductos());
			buscar.setSede(ven.getSede());
			session.update(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
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
			session.update(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void actualizarAdministrador(Administrador admin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
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
			session.update(buscar);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void eliminarCliente(Cliente cliente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(cliente);
		session.getTransaction().commit();
		session.close();

	}

	public static void eliminarVendedor(Vendedor vendedor) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(vendedor);
		session.getTransaction().commit();
		session.close();

	}
	
	public static void eliminarGerente(Gerencia gerente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(gerente);
		session.getTransaction().commit();
		session.close();

	}
	
	public static void eliminarAdministrador(Administrador admin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.delete(admin);
		session.getTransaction().commit();
		session.close();

	}

	public static void main (String args[]) {


		//		Administrador juano = new Administrador();
		//		juano.setNombres("Juan David Alberto");
		//		juano.setApellidos("Quintero Gaona");
		//		juano.setUsuario("Juan");
		//		juano.setContraseña("Juan123");
		//		juano.setSede("Tu Cora <3");
		//
		//		Transaction transaccion = null;
		//		try (Session session = HibernateUtil.getSessionFactory().openSession()){
		//
		//			transaccion = session.beginTransaction();
		//			session.save(juano);
		//			transaccion.commit();
		//			session.close();
		//
		//		} catch (Exception e) {
		//			if (transaccion != null) {
		//				transaccion.rollback();
		//			}
		//			e.printStackTrace();
		//		}
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres("Pedro");
		vendedor.setApellidos("Alcachofa");
		vendedor.setCorreo("abc@gmail.com");
		vendedor.setSede("Mundial");
		vendedor.setUsuario("Pedio123");

		Producto pr1= new Producto();
		pr1.setCantidad(3);
		pr1.setCategoria("Tec");
		pr1.setNombre("Celucos");
		pr1.setNombre("Juajuei");
		pr1.setPrecio(5000);

		vendedor.getProductos().add(pr1);

		Dao.agregarVendedor(vendedor);

	}
}
