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
	private static String[] opIniciales= {"Inicio", "Iniciar Sesion", "Resgistrate", "Ayuda"};
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

	private ArrayList<Vendedor> seVendedores =(ArrayList<Vendedor>) Dao.vendedores;
	private ArrayList<Vendedor> sedVendedores = null;

	private ArrayList<Producto> seProductosCategoria = null;

	private Producto seProducto = new Producto();
	private Producto seProductoS = new Producto();


	private ArrayList<Cliente> seClientes= (ArrayList<Cliente>)Dao.clientes;
	private ArrayList<Cliente> sedClientes =null;

	private String usuario, contraseña;
	private String tipoPagoSelecc, reservaSelec;

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
	private List<ArrayList<Producto>> seMatrizProductos;

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
		

		admin = Presistence.buscarAdministradorporUsuario(usuario);
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
						seVendedor = vend;
						mostrarOpciones();
						seProductos = new ArrayList<Producto>();
						for (int i = 0 ; i < vend.getProductos().size() ; i++) {
							seProductos.add(vend.getProductos().get(i));
						}
						seMatrizProductos = Ultilidades.generarMatrizProducto(vend.getSede());
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
							seMatrizProductos = Ultilidades.generarMatrizProducto(cli.getCiudad());

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
			carroCompras = null;
			mostrarOpciones();
			return "Principal";
		}
		if (opcionSeleccionada.equals("Carrito")) {
			return "FinalizarCarro";

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
		if(opcionSeleccionada.equals("Clientes")) {
			if(admin != null) {
				sedClientes=Presistence.busquedaClientes(admin.getSede());
				return "TablaClientes";
			}
			if(gere != null) {
				sedClientes= seClientes;
				return "TablaClientes";
			}
		}
		if(opcionSeleccionada.equals("Vendedores")) {
			if(admin != null) {
				sedVendedores=Presistence.busquedaVendedores(admin.getSede());
				return "TablaVendedor";
			}
			if(gere != null) {
				sedVendedores=seVendedores;
				return "TablaVendedor";
			}
		}
		return null;

	}
	//hace cositas
	public String catalogoCategoria() {

		seProductosCategoria = Presistence.buscarProductosPorCategoria(categoriaSeleccionada, seProductos);
		mensaje = "Ver Productos";

		return "CategoriaSeleccionada";
	}


	public void mostrarOpciones() {
		mTitulo = "Bienvenido ";
		if (cli != null) {
			mTitulo += cli.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Categorias", "Carrito",
					"Mi Perfil", "Mis compras",
					"Cerrar Sesión"
			};
		} else if (vend != null) { 
			mTitulo += vend.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Mi Perfil", "Historial Ventas", "Mis productos",
					"Cerrar Sesión"
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
					"Mi Perfil","Reportes", "Clientes",
					"Vendedores",  "Cerrar Sesión"
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
		seProductos.add(seProducto);
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
		return "Carro";
	}
	public String agregarProductoAlCarro() {
		String retorno = "Principal";
		System.out.println(proSelecc.toString());
		carroCompras.agregarProducto(proSelecc, cNumeroDeProductos);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Agregado al carro");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

	public String finalizarTransaccion() {
		String retorno = "Principal";
		String mensaje = "Transaccion Exitosa!";
		if (tipoPagoSelecc.equals("Tarjeta")) {
			if (reservaSelec.equals("Obvio bobis")) {
				carroCompras.realizarTransaccion(Ultilidades.fechaActual(), tipoPagoSelecc, true);

			}
			else{
				carroCompras.realizarTransaccion(Ultilidades.fechaActual(), tipoPagoSelecc, false);

			}

		} else {
			if (reservaSelec.equals("Obvio bobis")) {
				carroCompras.realizarTransaccion(Ultilidades.fechaActual(), tipoPagoSelecc, true);

			}
			else {
				carroCompras.realizarTransaccion(Ultilidades.fechaActual(), tipoPagoSelecc, false);

			}
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}

	public String quitarItemCarro() {
		carroCompras.retirarProducto(seProductoS, seProductoS.getCantidad());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "FinalizarCarro";
	}

	public String editarVendedor() {
		if (confirmaClave.equals(seVendedor.getContraseña())) {
			
		}
		Presistence.actualizarVendedor(seVendedor);
		vend = seVendedor;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Perfil editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
	}
	
	public String eliminarVendedor() {
		
		return "";
	}
	public String editarCliente() {
		Presistence.actualizarCliente(seCliente);
		seCliente = cli;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Perfil editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
	}
	public String eliminarCliente() {
		return "";
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




	public ArrayList<Cliente> getSedClientes() {
		return sedClientes;
	}

	public void setSedClientes(ArrayList<Cliente> sedCliente) {
		this.sedClientes = sedCliente;
	}

	public ArrayList<Vendedor> getSedVendedores() {
		return sedVendedores;
	}

	public void setSedVendedores(ArrayList<Vendedor> sedVendedores) {
		this.sedVendedores = sedVendedores;
	}

	public Carrito getCarroCompras() {
		return carroCompras;
	}

	public void setCarroCompras(Carrito carroCompras) {
		this.carroCompras = carroCompras;
	}

	public Producto getSeProductoS() {
		return seProductoS;
	}

	public void setSeProductoS(Producto seProductoS) {
		this.seProductoS = seProductoS;
	}

	public String getTipoPagoSelecc() {
		return tipoPagoSelecc;
	}

	public void setTipoPagoSelecc(String tipoPagoSelecc) {
		this.tipoPagoSelecc = tipoPagoSelecc;
	}

	public String getReservaSelec() {
		return reservaSelec;
	}

	public void setReservaSelec(String reservaSelec) {
		this.reservaSelec = reservaSelec;
	}
	

}
