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
package co.edu.unbosque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;

/**
 * Esta clase es la que manejara todo lo que a secciones respecta.
 * Cuando alguien se regitre se activara cierta seccion
 * Y de este modo se desplegaran las diferentes opciones.
 */
@ManagedBean(name="seccion")
@RequestScoped
@ApplicationScoped
public class Session {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Arreglo de Strings representando las opciones iniciales del menu.
	 */
	private static String[] opIniciales= {"Inicio", "Categorias", "Iniciar Sesion", "Resgistrate", "Ayuda"};
	
	/**
	 * Arraylist resultado de la conversi�n del arreglo de opIniciales.
	 */
	private ArrayList<String> opciones = new ArrayList<String>(Arrays.asList(opIniciales));

	/**
	 * Clase que representa al cliente inicializado en null.
	 */
	private Cliente seCliente = null;
	
	/**
	 * Clase estatica del cliente.
	 */
	private static Cliente cli;
	
	/**
	 * Clase que representa al vendedor inicializado en null.
	 */
	private Vendedor seVendedor = null;
	
	/**
	 * Clase est�tica del vendedor.
	 */
	private static Vendedor vend;
	
	/**
	 * Clase que representa al administrador inicializado en null.
	 */
	private Administrador seAdmin = null;
	
	/**
	 * Clase est�tica del administrador.
	 */
	private static Administrador admin;
	
	/**
	 * Clase que representa al gerente inicializado en null.
	 */
	private Gerencia seGerente = null;
	
	/**
	 * Clase est�tica del gerente.
	 */
	private static Gerencia gere;

	/**
	 * Clase que representa al producto.
	 */
	private Producto seProducto = new Producto();

	/**
	 * Cadenas de caracteres para el usuario y la contrase�a.
	 */
	private String usuario, contrase�a;

	/**
	 * T�tulo de bienvendia al menu.
	 */
	private static String mTitulo = "Bienvenido <3";
	
	/**
	 * Arreglo del menu que contiene el t�tulo de bienvenida y las
	 * posibles opciones de inicio.
	 */
	private String menuTitulo = mTitulo, opcionSeleccionada;

	/**
	 * Cadena de caracteres est�tica de un mensaje.
	 */
	private static String mensaje;
	
	/**
	 * Cadena de caracteres representando el mensaje est�tico.
	 */
	private String message = mensaje;

	/**
	 * Objeto de tipo UploadedFile para representar imagenes.
	 */
	private UploadedFile imagen;

	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	/**
	 * Verifica el usuario y contrase�a ingresados en el login buscando entre los
	 * administradores, gerentes, vendedores y clientes. Si el proces es exitoso 
	 * se envia a la p�gina principal, en caso contrario, la de login.
	 * @return cadena de caracteres representado la p�gina de principal o de login.
	 */
	public String inicioSeccion() {
		admin = Presistence.buscarAdministradores(usuario);
		String retorno;
		if (admin != null) {
			if (!Ultilidades.desencriptador(admin.getContrase�a()).equals(contrase�a)) {
				admin = null;
				retorno = "Login";
			}
			else {
				mostrarOpciones();
				retorno = "Principal";
			}
		} else {
			gere = Presistence.buscarGerentes(usuario);
			if (gere != null) {
				if (!Ultilidades.desencriptador(gere.getContrase�a()).equals(contrase�a)) {
					gere = null;
					retorno = "Login";
				}
				else {
					mostrarOpciones();
					retorno = "Principal";
				}
			}
			else {
				vend = Presistence.buscarVendedores(usuario);
				if (vend != null) {
					if (!Ultilidades.desencriptador(vend.getContrase�a()).equals(contrase�a)) {
						vend = null;
						retorno = "Login";
					}
					else {
						mostrarOpciones();
						retorno = "Principal";
					}
				}
				else {
					cli = Presistence.buscarCliente(usuario);
					if (cli != null) {
						if (!Ultilidades.desencriptador(cli.getContrase�a()).equals(contrase�a)) {
							cli = null;
							retorno = "Login";
						}
						else {
							mostrarOpciones();
							retorno = "Principal";
						}
					}
					else {
						retorno = "Login";
					}
				}
			}
		}
		return retorno;

	}

	/**
	 * M�todo que realiza las acciones correspondientes de acuerdo
	 * a la opci�n seleccionada del menu.
	 * @return las p�ginas correspondiente a la opci�n seleccionada
	 * del menu.
	 */
	public String seccionOpcionMenu() {
		if (opcionSeleccionada.equals("Inicio")) {
			return "Principal";
		}
		if (opcionSeleccionada.equals("Iniciar Sesion")) {
			return "Login";
		}
		if (opcionSeleccionada.equals("Resgistrate")) {
			return "RegistroComo";
		}
		if (opcionSeleccionada.equals("Cerrar Sesi�n")) {
			cli = null;
			vend = null;
			admin = null;
			gere = null;
			mostrarOpciones();
			return "Principal";
		}
		if (opcionSeleccionada.equals("Mis productos")) {
			mensaje = "Tus Productos En Ventas";
			seVendedor = vend;
			if (vend.getProductos().size() < 1) {
				mensaje = "Registra tu primer producto";
			}
			message = mensaje;
			return "MisProductos";
		}
		if (opcionSeleccionada.equals("Mis Ventas")) {

		}
		if (opcionSeleccionada.equals("Mi Perfil")) {

		}
		return null;

	}

	/**
	 * Dependiendo del tipo de usuario se muestras las respectivas opciones de menu.
	 */
	public void mostrarOpciones() {
		mTitulo = "Bienvenido ";
		if (cli != null) {
			mTitulo += cli.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Categorias",
					"Ver Perfil", "Mis compras", "Notificaciones",
					"Cerrar Sesi�n", "Ayuda"
			};
		} else if (vend != null) {
			mTitulo += vend.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Mi Perfil", "Historial Ventas", "Mis productos",
					"Notificaciones", "Cerrar Sesi�n", "Ayuda"
			};
		} else if (admin != null) {
			menuTitulo += admin.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Reportes", "Clientes",
					"Vendedores", "Cerrar Sesi�n"
			};

		} else if (gere != null) {
			mTitulo += gere.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Perfil",  "Cerrar Sesi�n"
			};

		} else {
			opIniciales= new String[]{"Inicio",
					"Categorias", "Iniciar Sesion",
					"Resgistrate", "Ayuda"};
			mTitulo += "<3";
		}
		opciones = new ArrayList<String>(Arrays.asList(opIniciales));
		menuTitulo = mTitulo;
	}

	/**
	 * Guarda un nuevo producto.
	 */
	public void guardarProducto() {
		byte[] content = imagen.getContent();
		seProducto.setImagen(content);
		seProducto.setVendedor(vend);

	}

	/**
	 * Permite registrar un nuevo producto.
	 * @return la p�gina principal.
	 */
	public String registrarProducto() {

		String retorno = "Principal";
		guardarProducto();
		vend.getProductos().add(seProducto);
		Presistence.actualizarVendedor(vend);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Registrado Exitosamente!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

	/**
	 * Getter de opciones.
	 * @return opciones.
	 */
	public ArrayList<String> getOpciones() {
		return opciones;
	}

	/**
	 * Setter de opciones.
	 * @param opciones
	 */
	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}

	/**
	 * Getter de cliente.
	 * @return seCliente
	 */
	public Cliente getSeCliente() {
		return seCliente;
	}

	/**
	 * Setter de cliente.
	 * @param seCliente
	 */
	public void setSeCliente(Cliente seCliente) {
		this.seCliente = seCliente;
	}

	/**
	 * Getter de vendedor.
	 * @return seVendedor
	 */
	public Vendedor getSeVendedor() {
		return seVendedor;
	}

	/**
	 * Setter de vedendedor.
	 * @param seVendedor
	 */
	public void setSeVendedor(Vendedor seVendedor) {
		this.seVendedor = seVendedor;
	}

	/**
	 * Getter del administrador.
	 * @return seAdmin
	 */
	public Administrador getSeAdmin() {
		return seAdmin;
	}

	/**
	 * Setter del administrador.
	 * @param seAdmin
	 */
	public void setSeAdmin(Administrador seAdmin) {
		this.seAdmin = seAdmin;
	}

	/**
	 * Getter de gerencia.
	 * @return seGerente
	 */
	public Gerencia getSeGerente() {
		return seGerente;
	}

	/**
	 * Setter de gerencia.
	 * @param seGerente
	 */
	public void setSeGerente(Gerencia seGerente) {
		this.seGerente = seGerente;
	}

	/**
	 * Getter de usuario.
	 * @return usuario.
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Setter de usuario.
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Getter de contrase�a.
	 * @return contrase�a
	 */
	public String getContrase�a() {
		return contrase�a;
	}

	/**
	 * Setter de contrase�a.
	 * @param contrase�a
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	/**
	 * Getter del titulo de menu.
	 * @return menuTitulo
	 */
	public String getMenuTitulo() {
		return menuTitulo;
	}

	/**
	 * Setter del t�tulo de menu.
	 * @param menuTitulo
	 */
	public void setMenuTitulo(String menuTitulo) {
		this.menuTitulo = menuTitulo;
	}

	/**
	 * Getter de la opci�n seleccionada.
	 * @return opcionSeleccionada
	 */
	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	/**
	 * Setter de la opci�n seleccionada.
	 * @param opcionSeleccionada
	 */
	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	/**
	 * Getter de message.
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter de message.
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter de producto.
	 * @return seProducto
	 */
	public Producto getSeProducto() {
		return seProducto;
	}

	/**
	 * Setter de producto.
	 * @param seProducto
	 */
	public void setSeProducto(Producto seProducto) {
		this.seProducto = seProducto;
	}

	/**
	 * Getter de la imagen.
	 * @return imagen
	 */
	public UploadedFile getImagen() {
		return imagen;
	}

	/**
	 * Setter de la imagen.
	 * @param imagen
	 */
	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}


}
