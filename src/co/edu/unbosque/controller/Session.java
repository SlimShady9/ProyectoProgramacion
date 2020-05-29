package co.edu.unbosque.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.model.file.UploadedFile;



import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Vendedor;



@ManagedBean(name="seccion")
@SessionScoped
public class Session {

	/**
	 * Esta clase es la que manejara todo lo que a secciones respecta
	 * Cuando alguien se regitre se activara cierta seccion
	 * Y de este modo se desplegaran las diferentes opciones
	 */
	private static String[] opIniciales= {"Inicio", "Categorias", "Iniciar Sesion", "Resgistrate", "Ayuda"};
	private ArrayList<String> opciones = new ArrayList<String>(Arrays.asList(opIniciales));

	private Cliente seCliente = null;
	private static Cliente cli;
	private Vendedor seVendedor = null;
	private static Vendedor vend;
	private Administrador seAdmin = null;
	private static Administrador admin;
	private Gerencia seGerente = null;
	private static Gerencia gere;

	private ArrayList<Producto> seProductos = (ArrayList<Producto>) Dao.productos;

	private Producto seProducto = new Producto();

	private String usuario, contraseña;

	private static String mTitulo = "Bienvenido <3";
	private String menuTitulo = mTitulo, opcionSeleccionada;

	private static String mensaje;
	private String message = mensaje;

	private UploadedFile imagen;
	
	/**
	 * Esta matriz es ideal para un algorito de ordenamiento por popularidad o algo asi
	 * Este algoritmo iria en el metodo de cargar matrizProductos
	 * Lo pondre en utilidades para no satudad esta clase
	 */
	private List<ArrayList<Producto>> seMatrizProductos = Ultilidades.generarMatrizProducto();
	
	// Este producto es el seleccionado
	
	private Producto proSelecc;
	private static String nombre, precio, categoria, vendedor;


	public String inicioSeccion() {
		admin = Presistence.buscarAdministrador(usuario);
		String retorno;
		if (admin != null) {
			if (!Ultilidades.desencriptador(admin.getContraseña()).equals(contraseña)) {
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
				if (!Ultilidades.desencriptador(gere.getContraseña()).equals(contraseña)) {
					gere = null;
					retorno = "Login";
				}
				else {
					mostrarOpciones();
					retorno = "Principal";
				}
			}
			else {
				vend = Presistence.buscarVendedor(usuario);
				if (vend != null) {
					if (!Ultilidades.desencriptador(vend.getContraseña()).equals(contraseña)) {
						vend = null;
						retorno = "Login";
					}
					else {
						mostrarOpciones();
						seProductos = new ArrayList<Producto>();
						for (int i = 0 ; i < vend.getProductos().size() ; i++) {
							vend.getProductos().get(i).getImagen();
							seProductos.add(vend.getProductos().get(i));
						}
						retorno = "Principal";
					}
				}
				else {
					cli = Presistence.buscarCliente(usuario);
					if (cli != null) {
						if (!Ultilidades.desencriptador(cli.getContraseña()).equals(contraseña)) {
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
		if (opcionSeleccionada.equals("Cerrar Sesión")) {
			cli = null;
			vend = null;
			admin = null;
			gere = null;
			mostrarOpciones();
			return "Principal";
		}
		if (opcionSeleccionada.equals("Mis productos")) {
			mensaje = "Tus Productos En Venta";
			seVendedor = vend;
			if (vend.getProductos().size() < 1) {
				mensaje = "Registra tu primer Producto";
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

	public void mostrarOpciones() {
		mTitulo = "Bienvenido ";
		if (cli != null) {
			mTitulo += cli.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Categorias",
					"Ver Perfil", "Mis compras", "Notificaciones",
					"Cerrar Sesión", "Ayuda"
			};
		} else if (vend != null) {
			mTitulo += vend.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Mi Perfil", "Historial Ventas", "Mis productos",
					"Notificaciones", "Cerrar Sesión", "Ayuda"
			};
		} else if (admin != null) {
			menuTitulo += admin.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Reportes", "Clientes",
					"Vendedores", "Cerrar Sesión"
			};

		} else if (gere != null) {
			mTitulo += gere.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Perfil",  "Cerrar Sesión"
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

	public void guardarProducto() {
		byte[] content = imagen.getContent();
		seProducto.setImagen(content);
		imagen = null;
	}

	public String registrarProducto() {

		String retorno = "Principal";
		guardarProducto();
		vend.getProductos().add(seProducto);
		seProductos.add(seProducto);
		Presistence.actualizarVendedor(vend);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Registrado Exitosamente!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Producto getSeProducto() {
		return seProducto;
	}

	public void setSeProducto(Producto seProducto) {
		this.seProducto = seProducto;
	}

	public UploadedFile getImagen() {
		return imagen;
	}

	public void setImagen(UploadedFile imagen) {
		this.imagen = imagen;
	}

	public ArrayList<Producto> getSeProductos() {
		return seProductos;
	}

	public void setSeProductos(ArrayList<Producto> seProductos) {
		this.seProductos = seProductos;
	}

	public List<ArrayList<Producto>> getseMatrizProductos() {
		return seMatrizProductos;
	}

	public void setMatrizProductos(List<ArrayList<Producto>> matrizProductos) {
		this.seMatrizProductos = matrizProductos;
	}

	public Producto getProSelecc() {
		return proSelecc;
	}

	public void setProSelecc(Producto proSelecc) {
		this.proSelecc = proSelecc;
	}

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Session.nombre = nombre;
	}

	public static String getPrecio() {
		return precio;
	}

	public static void setPrecio(String precio) {
		Session.precio = precio;
	}

	public static String getVendedor() {
		return vendedor;
	}

	public static void setVendedor(String vendedor) {
		Session.vendedor = vendedor;
	}

	public static String getCategoria() {
		return categoria;
	}

	public static void setCategoria(String categoria) {
		Session.categoria = categoria;
	}
	
	
	

}
