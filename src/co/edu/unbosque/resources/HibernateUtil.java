/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogot� - Colombia)
 * Programa de Ingenier�a de Sistemas
 * Programaci�n II
 * 
 * Profesor: Miguel Alejandro Feijoo Garc�a
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura Mar�a L�pez Moreno
 * 	@author	Andr�s Felipe Rey Pedraza
 * 	@author	Juan Camilo D�az
 * 	@author	Camilo Andr�s Romero Posada
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
import org.hibernate.cfg.Configuration;

/**
 * Clase de Hibernate para realizar el proceso de gestion de sesiones.
 */
public class HibernateUtil {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Registro.
	 */
    private static StandardServiceRegistry registry;

    /**
     * Objeto de SessionFactory.
     */
    private static SessionFactory sessionFactory;

    /**
     * Construye la sessionFactoty.
     * @return una sessionFactory
     */
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            try {

                // Crea el registro.

                registry = new StandardServiceRegistryBuilder().configure().build();

                // Crea el MetadataSources

                MetadataSources sources = new MetadataSources(registry);

                // Crea el Metadata

                Metadata metadata = sources.getMetadataBuilder().build();

                // Crea el SessionFactory

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
     * Devuelve la sesi�n del hibernate.
     * @return session
     */
    public static Session getHibernateSession() {

        final SessionFactory sf = new Configuration()
            .configure("hibernate.cfg.xml").buildSessionFactory();

        // factory = new Configuration().configure().buildSessionFactory();
        final Session session = sf.getCurrentSession();
        return session;
        }

    public static void shutdown() {

        if (registry != null) {

            StandardServiceRegistryBuilder.destroy(registry);

        }

    }

}