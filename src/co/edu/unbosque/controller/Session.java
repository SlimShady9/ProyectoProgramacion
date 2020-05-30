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
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Sede;
import co.edu.unbosque.model.Vendedor;



@ManagedBean(name="seccion")
@SessionScoped
public class Session {

	/**
	 * Esta clase es la que manejara todo lo que a secciones respecta
	 * Cuando alguien se regitre se activara cierta seccion
	 * Y de este modo se desplegaran las diferentes opciones
	 */
	private Ultilidades utilidades = new Ultilidades();
	private static String[] opIniciales= {"Inicio", "Categorias", "Iniciar Sesion", "Resgistrate", "Ayuda"};
	private static String[] categoriasDisponibles = {"Mujeres", "Niños", "Vestimenta", "Hogar", "Tecnologia", "Hombres", "Mascotas", "Deprtes", "Otro"};
	private String categoriaSeleccionada;
	private int numeroDeProductos;
	private ArrayList<String> opciones = new ArrayList<String>(Arrays.asList(opIniciales));
	private ArrayList<String> caterogirasD = new ArrayList<String>(Arrays.asList(categoriasDisponibles));

	private Cliente seCliente = null;
	private static Cliente cli;
	
	private Vendedor seVendedor = null;
	private static Vendedor vend;
	private Administrador seAdmin = null;
	private static Administrador admin;
	private Gerencia seGerente = null;
	private static Gerencia gere;

	private ArrayList<Producto> seProductos = (ArrayList<Producto>) Dao.productos;
	private ArrayList<Producto> seProductosCategoria = null;

	private Producto seProducto = new Producto();


	private ArrayList<Cliente> seClientes= (ArrayList<Cliente>)Dao.clientes;


	private String usuario, contraseña;

	private static String mTitulo = "Bienvenido <3";
	private String menuTitulo = mTitulo, opcionSeleccionada, confirmaClave, clave;

	private static String mensaje;
	private String message = mensaje;

	private UploadedFile imagen;

	/**
	 * Esta matriz es ideal para un algorito de ordenamiento por popularidad o algo asi
	 * Este algoritmo iria en el metodo de cargar matrizProductos
	 * Lo pondre en utilidades para no satudad esta clase
	 */
	private List<ArrayList<Producto>> seMatrizProductos = Ultilidades.generarMatrizProducto();

	/**
	 * Arreglo con las sedes ornenadas
	 * 
	 */

	private List<Sede> sedes = utilidades.topSedeVentas();
	private String sedeSeleccionada;
	// Este producto es el seleccionado
	private int cNumeroDeProductos;
	private Producto proSelecc;
	private static String nombre, precio, categoria, vendedor;

	private Carrito carroCompras;

	public String inicioSeccion() {
		admin = Presistence.buscarAdministrador(usuario);
		String retorno = null;
		if (admin != null) {
			if (!admin.getContraseña().equals(contraseña)) {
				admin = null;
				retorno = "Login";
			}
			else {
				seAdmin = admin;
				mostrarOpciones();
				retorno = "PerfilesAdministrador";
			}
		} else {
			gere = Presistence.buscarGerentes(usuario);
			if (gere != null) {
				if (!gere.getContraseña().equals(contraseña)) {
					gere = null;
				}
				else {
					seGerente = gere;
					mostrarOpciones();
					retorno = "PerfilGerente";
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
							seProductos = Presistence.busquedaProductos(Presistence.busquedaVendedores(cli.getCiudad()));
							if (cli.getEstado().equals("Inactivo")) {
								retorno = "ConfirmarClave";
							}
							else {
								retorno = "Principal";
							}
							seCliente = cli;
							carroCompras = new Carrito(seCliente);
							mostrarOpciones();
							
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
		if (opcionSeleccionada.equals("Categorias")) {
			return "Categorias";
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
			if (admin != null){
				return "PerfilAdministrador";
			}
			if (gere != null){
				return "PerfilGerente";
			}
			if (vend != null){
				return "PerfilVendedor";
			}
			if (cli != null){
				return "PerfilUsuario";
			}
		}
		return null;

	}
	
	public String catalogoCategoria() {

		seProductosCategoria = Presistence.buscarProductosPorCategoria(categoriaSeleccionada, seProductos);
		mensaje = "Ver Productos";
		
		return "CategoriaSeleccionada";
	}
	
//	public ()

	public void mostrarOpciones() {
		mTitulo = "Bienvenido ";
		if (cli != null) {
			mTitulo += cli.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Categorias",
					"Mi Perfil", "Mis compras", "Notificaciones",
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
					"Inicio","Mi Perfil", "Reportes", "Clientes",
					"Vendedores", "Cerrar Sesión"
			};

		} else if (gere != null) {
			mTitulo += gere.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Mi Perfil",  "Cerrar Sesión"
			};

		} else {
			opIniciales= new String[]{"Inicio",
					"Iniciar Sesion",
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
		seProducto.setVendedor(vend);
		vend.getProductos().add(seProducto);
		Presistence.agregarProducto(seProducto);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Registrado Exitosamente!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

	public String activarUsuario() {
		if (clave.equals(confirmaClave)) {
			boolean validacion = false;
			for (int i = 0 ; i < clave.length() ; i++) {
				if (Character.isDigit(clave.charAt(i))) {
					validacion = true;
					break;
				}
			}
			if (clave.length() >= 8 && clave.length() <= 5) {
				validacion = false;

			}
			if (validacion) {
				seCliente.setContraseña(Ultilidades.encriptador(clave));
				seCliente.setEstado("Activo");
				Presistence.actualizarCliente(seCliente);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Proceso Finalizado");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "Principal";
			}
			else {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La clave debe tener al menos un carater numerico y longitud entre 5 a 8");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return "ValidacionTarjeta";
			}
		}
		else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Clave no coincide");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "ValidacionTarjeta";
		}
	}
	
	public String seleciconarProducto() {
		System.out.println(proSelecc.getCantidadToArray().size());
		return "Carro";
	}
	public String agregarProductoAlCarro() {
		String retorno = "Principal";
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

	public String getSedeSeleccionada() {
		return sedeSeleccionada;
	}

	public void setSedeSeleccionada(String sedeSeleccionada) {
		this.sedeSeleccionada = sedeSeleccionada;
	}
	public ArrayList<Cliente> getSeClientes() {
		return seClientes;
	}
	public void setSeClientes(ArrayList<Cliente> seClientes) {
		this.seClientes = seClientes;
	}

	public String getConfirmaClave() {
		return confirmaClave;
	}

	public void setConfirmaClave(String confirmaClave) {
		this.confirmaClave = confirmaClave;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public ArrayList<String> getCaterogirasD() {
		return caterogirasD;
	}

	public void setCaterogirasD(ArrayList<String> caterogirasD) {
		this.caterogirasD = caterogirasD;
	}

	public String getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(String categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public ArrayList<Producto> getSeProductosCategoria() {
		return seProductosCategoria;
	}

	public void setSeProductosCategoria(ArrayList<Producto> seProductosCategoria) {
		this.seProductosCategoria = seProductosCategoria;
	}

	public int getNumeroDeProductos() {
		return numeroDeProductos;
	}

	public void setNumeroDeProductos(int numeroDeProductos) {
		this.numeroDeProductos = numeroDeProductos;
	}

	public int getcNumeroDeProductos() {
		return cNumeroDeProductos;
	}

	public void setcNumeroDeProductos(int cNumeroDeProductos) {
		this.cNumeroDeProductos = cNumeroDeProductos;
	}

	


}
