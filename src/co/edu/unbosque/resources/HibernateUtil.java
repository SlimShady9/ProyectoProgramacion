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

package co.edu.unbosque.resources;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *  Esta clase es la encargada de manejar las secciones de hibernate
 */
public class HibernateUtil {
	
	

	/**
	 *  Este atributo estatico es la configuracion estandar de Hibernate
	 */
	private static StandardServiceRegistry registry;

	/**
	 *  Este atributo estatico el encagagado a generar la secionnes por entidad
	 */
	private static SessionFactory sessionFactory;

	/**
	 *  Este atributo estatico crea la configuracion de hibernate y abre un generador de sessiones
	 *  @return SeccionFacotry el generador de sessiones
	 */
	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null || sessionFactory.isClosed()) {

			try {

				// Create registry

				registry = new StandardServiceRegistryBuilder().configure().build();

				// Create MetadataSources

				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata

				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory

				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {

				e.printStackTrace();

				if (registry != null) {

					StandardServiceRegistryBuilder.destroy(registry);

				}

			}

		}

		return sessionFactory;

	}
	
	/**
	 *  Este atributo estatico retorna una seccion de seccionfacotry
	 *  @return session de hibernate factory
	 */

	public static Session getHibernateSession() {

		return sessionFactory.getCurrentSession();
	}
	
	/**
	 *  Este atributo cierra el hibernate factory 
	 */

	public static void shutdown() {

		if (registry != null) {

			StandardServiceRegistryBuilder.destroy(registry);

		}

	}

}