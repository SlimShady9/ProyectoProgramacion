package model;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Dao {
	

	/**
	 * Esta clase tendra toda la persistencia del proyecto
	 * Asi como los arreglos traidos directos de la persistencia
	 * Esto para que el controlador maneje las secciones usando directamente esta clase
	 * 
	 */
	private static final String nombrePersistencia = "ProyectoProgramacion";
	private static EntityManagerFactory coneccion;
	protected ArrayList<Administrador> administradores;
	protected ArrayList<Producto> productos;
//	protected ArrayList<Cl>
	
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
