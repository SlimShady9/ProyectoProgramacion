package co.edu.unbosque.controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import co.edu.unbosaue.resources.HibernateUtil;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;


public class Dao {
	

	/**
	 * Esta clase tendra toda la persistencia del proyecto
	 * Asi como los arreglos traidos directos de la base de datos
	 * Esto para que el controlador maneje las secciones usando directamente esta clase
	 * 
	 */
	private Session session;
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
	public static void cargarTablas() {
		
	}
	public static void main (String args[]) {

		
		Administrador juano = new Administrador();
		juano.setNombres("Juan David Alberto");
		juano.setApellidos("Quintero Gaona");
		juano.setUsuario("Jondo");
		juano.setContraseña("Rey123");
		juano.setSede("Tu Cora <3");
		
		Transaction transaccion = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			
			transaccion = session.beginTransaction();
			session.save(juano);
			transaccion.commit();
			session.close();
			
		} catch (Exception e) {
            if (transaccion != null) {
            	transaccion.rollback();
            }
            e.printStackTrace();
		}
	}
}
