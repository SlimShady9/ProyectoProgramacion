package co.edu.unbosque.controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Administrador;
import model.Cliente;
import model.Gerencia;
import model.Producto;
import model.Vendedor;


public class Dao {
	

	/**
	 * Esta clase tendra toda la persistencia del proyecto
	 * Asi como los arreglos traidos directos de la base de datos
	 * Esto para que el controlador maneje las secciones usando directamente esta clase
	 * 
	 */
	private static final String nombrePersistencia = "ProyectoProgramacion";
	private static EntityManagerFactory coneccion;
	protected static ArrayList<Administrador> administradores;
	protected static ArrayList<Producto> productos;
	protected static ArrayList<Cliente> clientes;
	protected static ArrayList<Gerencia> gerentes;
	protected static ArrayList<Vendedor> vendedores;
	
	public Dao() {
		productos = new ArrayList<Producto>();
		clientes = new ArrayList<Cliente>();
		gerentes = new ArrayList<Gerencia>();
		vendedores = new ArrayList<Vendedor>();
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (coneccion == null) {
			coneccion = Persistence.createEntityManagerFactory(nombrePersistencia);
			
		}
		return coneccion;
	}

	public static void cerrarConeccion() {
		if (coneccion != null) {
			coneccion.close();
		}
	}
	public static void cargarTablas() {
		EntityManager entityManager = getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery("SELECT v FROM Vendedor v");
		vendedores = (ArrayList<Vendedor>) query.getResultList();
		
	}
	public static void main (String args[]) {
		EntityManager entidad = Dao.getEntityManagerFactory().createEntityManager();
		
		Administrador juano = new Administrador();
		juano.setNombres("Juan David Alberto");
		juano.setApellidos("Quintero Gaona");
		juano.setUsuario("Jondo");
		juano.setContraseña("Rey123");
		entidad.getTransaction().begin();
		entidad.persist(juano);
		entidad.getTransaction().commit();
		entidad.close();
		Dao.cerrarConeccion();
	}
}
