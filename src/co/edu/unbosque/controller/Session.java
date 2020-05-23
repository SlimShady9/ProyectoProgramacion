package co.edu.unbosque.controller;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Vendedor;


@ManagedBean(name="seccion")
@ViewAccessScoped
public class Session {
	
	/**
	 * Esta clase es la que manejara todo lo que a secciones respecta
	 * Cuando alguien se regitre se activara cierta seccion
	 * Y de este modo se desplegaran las diferentes opciones
	 */
	
	private ArrayList<String> opciones;
	private Cliente seCliente = null;
	private Vendedor seVendedor = null;
	private Administrador seAdmin = null;
	private Gerencia seGerente = null;
	
	private String usuario, contraseña;
	
	private String menuTitulo;
	
	
	public String inicioSeccionCliente () {
			
		mostrarOpciones();
		return "Principal";
	}
	
	public String inicioSeccionVendedor() {
		
		mostrarOpciones();
		return "Principal";
	}
	
	public String inicioSeccionAdmin() {
		
		mostrarOpciones();
		return "Principal";
	}
	
	public void mostrarOpciones() {
		opciones = new ArrayList<String>();
		opciones.add("Menu");
		opciones.add("Inicio");
		opciones.add("Categorias");
		menuTitulo = "Bienvenido ";
		if (seCliente != null) {
			menuTitulo += seCliente.getUsuario();
			opciones.add("Perfil");
			opciones.add("Mis compras");
			opciones.add("Notificaciones");
			opciones.add("Cerrar Seccion");
		} else if (seVendedor != null) {
			menuTitulo += seVendedor.getUsuario();
			opciones.add("Perfil");
			opciones.add("Mis ventas");
			opciones.add("Mis productos");
			opciones.add("Notificaciones");
			opciones.add("Cerrar Seccion");
			opciones.add("Notificaciones");
			opciones.add("Cerrar Seccion");
		} else if (seAdmin != null) {
			menuTitulo += seAdmin.getUsuario();
			opciones.add("Perfil");
			opciones.add("Reportes");
			opciones.add("Clientes");
			opciones.add("Vendedores");
			opciones.add("Cerrar Seccion");
			
		} else if (seGerente != null) {
			menuTitulo += seGerente.getUsuario();
			opciones.add("Perfil");
			opciones.add("Cerrar Seccion");

		} else {
			menuTitulo += "<3";
			opciones.add("Iniciar Session");
			opciones.add("Registrate");
		}
		opciones.add("Ayuda");
	}
	

}
