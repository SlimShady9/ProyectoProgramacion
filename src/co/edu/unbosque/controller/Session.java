package co.edu.unbosque.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
	private String[] opIniciales= {"Inicio", "Categorias", "Iniciar Sesion", "Resgistrate", "Ayuda"};
	private ArrayList<String> opciones = new ArrayList<String>(Arrays.asList(opIniciales));
	
	private Cliente seCliente = null;
	private Vendedor seVendedor = null;
	private Administrador seAdmin = null;
	private Gerencia seGerente = null;
	
	private String usuario, contraseña;
	
	private String menuTitulo, opcionSeleccionada;
	
	
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
	
	public String seccionOpcionMenu() {
		
	}
	
	public void mostrarOpciones() {
		opciones = new ArrayList<String>();
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

	public ArrayList<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}

	public Cliente getSeCliente() {
		return seCliente;
	}

	public void setSeCliente(Cliente seCliente) {
		this.seCliente = seCliente;
	}

	public Vendedor getSeVendedor() {
		return seVendedor;
	}

	public void setSeVendedor(Vendedor seVendedor) {
		this.seVendedor = seVendedor;
	}

	public Administrador getSeAdmin() {
		return seAdmin;
	}

	public void setSeAdmin(Administrador seAdmin) {
		this.seAdmin = seAdmin;
	}

	public Gerencia getSeGerente() {
		return seGerente;
	}

	public void setSeGerente(Gerencia seGerente) {
		this.seGerente = seGerente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getMenuTitulo() {
		return menuTitulo;
	}

	public void setMenuTitulo(String menuTitulo) {
		this.menuTitulo = menuTitulo;
	}

	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}
	
	

}
