
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
 * 			
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package co.edu.unbosque.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.file.UploadedFile;
import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Sede;
import co.edu.unbosque.model.Vendedor;


/**
 * Esta clase es la que manejara todo lo que a secciones respecta.
 * Cuando alguien se regitre se activara cierta seccion
 * Y de este modo se desplegaran las diferentes opciones.
 */

@ManagedBean(name="seccion")
@SessionScoped
public class Session {

	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Arreglo de Strings representando las opciones iniciales del menu.
	 */
	private static String[] opIniciales= {"Inicio", "Iniciar Sesion", "Resgistrate", "Ayuda"};

	/**
	 * Arreglo de String con las categorias disponibles
	 */
	private static String[] categoriasDisponibles = {"Mujeres", "Niños", "Vestimenta", "Hogar", "Tecnologia", "Hombres", "Mascotas", "Deprtes", "Otro"};
	/**
	 * Esta clase es la que manejara todo lo que a secciones respecta
	 * Cuando alguien se regitre se activara cierta seccion
	 * Y de este modo se desplegaran las diferentes opciones
	 */
	private ArrayList<String> seCategoriasDisponibles = new ArrayList<>(Arrays.asList(categoriasDisponibles));
	/**
	 * Este String es el encargado de escuchar la categoria seleciconada por el bean 
	 */
	private String categoriaSeleccionada;
	/**
	 * Este int es el encargado de mostar el numero de productos disponibles de un producto en especifico 
	 */
	private int numeroDeProductos;
	
	/**
	 * Arreglo de String con las opciones disponibles
	 */
	private ArrayList<String> opciones = new ArrayList<String>(Arrays.asList(opIniciales));
	
	/**
	 * ArrayListo de String con las categorias disponibles por un porducto en especifico
	 */
	private ArrayList<String> caterogirasD = new ArrayList<String>(Arrays.asList(categoriasDisponibles));

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
	 * Clase estática del vendedor.
	 */
	private static Vendedor vend;
	/**
	 * Clase que representa al administrador inicializado en null.
	 */
	private Administrador seAdmin = null;
	/**
	 * Clase estática del administrador.
	 */
	private static Administrador admin;
	/**
	 * Clase que representa al gerente inicializado en null.
	 */
	private Gerencia seGerente = null;
	/**
	 * Clase estática del gerente.
	 */
	private static Gerencia gere;
	/**
	 * Clase que representa a un producto.
	 */

	private ArrayList<Producto> seProductos = (ArrayList<Producto>) Dao.productos;
	/**
	 * Clase que representa al producto temportal proveniente de un bean.
	 */
	private ArrayList<Producto> sedProductos = null;
	
	/**
	 * Clase que representa a un vendedor temporal de un bean.
	 */


	private ArrayList<Vendedor> seVendedores =(ArrayList<Vendedor>) Dao.vendedores;
	
	/**
	 * Clase que representa a un vendedor temporal de un bean.
	 */

	private ArrayList<Vendedor> sedVendedores = null;

	/**
	 * Clase que representa al producto por categoria.
	 */
	private ArrayList<Producto> seProductosCategoria = null;

	/**
	 * Clase que representa al producto por categoria.
	 */
	private Producto seProducto = new Producto();
	/**
	 * Clase que representa al producto seleccionado de una tabla
	 */
	private Producto seProductoS = new Producto();

	/**
	 * Clase que representa a todos los clientes de la base de datos.
	 */
	private ArrayList<Cliente> seClientes= (ArrayList<Cliente>)Dao.clientes;
	
	/**
	 * Clase que representa a un cliente temporal proveniente de un bean.
	 */
	private ArrayList<Cliente> sedClientes =null;

	/**
	 * Cadenas de caracteres para el usuario y la contraseña.
	 */
	private String usuario, contraseña;
	
	/**
	 * Clase que representa al producto por categoria.
	 */
	private String tipoPagoSelecc, reservaSelec;

	/**
	 * Título de bienvendia al menu.
	 */
	private static String mTitulo = "Bienvenido <3";
	/**
	 * Arreglo del menu que contiene el título de bienvenida y las
	 * posibles opciones de inicio.
	 */
	private String menuTitulo = mTitulo, opcionSeleccionada, confirmaClave, clave;

	/**
	 * Cadena de caracteres estática de un mensaje.
	 */
	private static String mensaje;
	/**
	 * Cadena de caracteres representando el mensaje estático.
	 */
	private String message = mensaje;

	/**
	 * Objeto de tipo UploadedFile para representar imagenes.
	 */
	private UploadedFile imagen;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas de vendedores
	 *  de acuerdo a la sede que pertenece un administrador
	 */
	
	private BarChartModel barChartModel;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  clientes con mayor numero de ventas a la sede que pertenece un administrador
	 */
	
	private BarChartModel barChartModel2;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  productos con mayor numero de ventas a la sede que pertenece un administrador
	 */
	
	private BarChartModel barChartModel3;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  vendedores con mayor numero de ventas entre todas las sedes
	 */
	
	private BarChartModel barChartModel4;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  clientes con mayor numero de ventas entre todas las sedes
	 */
	
	private BarChartModel barChartModel5;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  productos con mayor numero de ventas entre todas las sedes
	 */
	private BarChartModel barChartModel6;
	
	/**
	 *  Objeto del Barchart Model que representa las graficas estadisticas del top
	 *  productos con mayor numero de ventas entre todas las sedes
	 */
	/**
	 * Esta matriz es ideal para un algorito de ordenamiento por popularidad o algo asi
	 * Este algoritmo iria en el metodo de cargar matrizProductos
	 * Lo pondre en utilidades para no satudad esta clase
	 */
	
	private BarChartModel barChartModel7;

	/**
	 * Esta matriz de productos es la que se vera en la pragina principal cuando se
	 * carga los productos, si se pas aun null cargara todos los productos de todas la sedes
	 * 
	 */
	
	private List<ArrayList<Producto>> seMatrizProductos = Ultilidades.generarMatrizProducto(null);

	/**
	 * Arreglo con las sedes ornenadas
	 */

	private List<Sede> sedes = Ultilidades.topSedeVentas();
	/**
	 * Esta variable escuchara la sede seleccionada
	 */
	
	private String sedeSeleccionada;
	/**
	 * Esta variable escuchara la cantudad de numero de productos que un cliente selecicone para
	 * llevar al carro de compras
	 */
	private int cNumeroDeProductos;
	/**
	 * Instancia de producto que recoge un valor equivalente a un producto
	 *  seleccionado en un beean we
	 */
	private Producto proSelecc;
	
	/**
	 * Variables de tipo String que representar valores obenidoes por los bean
	 */
	
	private static String nombre, precio, categoria, vendedor;
	
	/**
	 *  Instancia de carro compras la cual se asociara a un cliente para que 
	 *  pueda realizar sus compras
	 */

	private Carrito carroCompras;
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Verifica el usuario y contraseña ingresados en el login buscando entre los
	 * administradores, gerentes, vendedores y clientes. Si el proces es exitoso 
	 * se envia a la página principal, en caso contrario, la de login.
	 * @return cadena de caracteres representado la página de principal o de login.
	 */

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
					if (!vend.getContraseña().equals(Ultilidades.encriptador(contraseña))) {
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
						retorno = "Principal";
					}
				}
				else {
					cli = Presistence.buscarCliente(usuario);
					if (cli != null) {
						if (!cli.getContraseña().equals((Ultilidades.encriptador(contraseña)))) {
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

	/**
	 * Método que realiza las acciones correspondientes de acuerdo
	 * a la opción seleccionada del menu.
	 * @return las páginas correspondiente a la opción seleccionada
	 * del menu.
	 */
	
	public String seccionOpcionMenu() {
		if (opcionSeleccionada.equals("Inicio")) {
			if (admin != null){
				return "PerfilAdministrador";
			}
			if (gere != null){
				return "PerfilGerente";
			}
			if (vend != null){
				return "Principal";
			}
			if (cli != null){
				return "Principal";
			}
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
		if (opcionSeleccionada.equals("Historial Ventas")) {
			return "MisVentas";
		}
		if (opcionSeleccionada.equals("Mis compras")) {
			return "MisCompras";
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
		if(opcionSeleccionada.equals("Productos")) {
			if(admin != null) {
				ArrayList<Vendedor> x = Presistence.busquedaVendedores(admin.getSede());
				sedProductos= Presistence.busquedaProductos(x);
				return "TablasProductos";
			}if(gere != null) {
				sedProductos= seProductos;
				return "TablasProductos";
			}
		}
		return null;

	}
	
	/**
	 * Este metodo busca los productos ordenados por la categoria seleccionada en el atrivuto
	 * categoriaSeleccionada
	 * @return String la pagina de categoria seleccionada con el filtro de la categoria 
	 */
	public String catalogoCategoria() {

		seProductosCategoria = Presistence.buscarProductosPorCategoria(categoriaSeleccionada, seProductos);
		mensaje = "Ver Productos";

		return "CategoriaSeleccionada";
	}

	/**
	 * Dependiendo del tipo de usuario se muestras las respectivas opciones de menu.
	 */
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
					"Inicio","Clientes",
					"Vendedores","Productos", "Cerrar Sesión"
			};

		} else if (gere != null) {
			mTitulo += gere.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Clientes",
					"Vendedores","Productos", "Cerrar Sesión"
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

	/**
	 * Guarda una imagen dada en un formato sobre un array de bytess
	 * ademas ese array se le asiga al proucto seleccionado sobre el proceso
	 * de registrar producto
	 */
	public void guardarProducto() {
		byte[] content = imagen.getContent();
		seProducto.setImagen(content);
		imagen = null;
	}

	/**
	 * Permite registrar un nuevo producto en la base de datos y el arreglo de Presistencia.
	 * @return la página principal.
	 */
	public String registrarProducto() {

		String retorno = "Principal";
		guardarProducto();
		seProducto.setVendedor(vend);
		vend.getProductos().add(seProducto);
		Presistence.agregarProducto(seProducto);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Registrado Exitosamente!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		seProductos.add(seProducto);	
		seProducto = new Producto();
		seMatrizProductos = Ultilidades.generarMatrizProducto(null);
		return retorno;
	}
	
	/**
	 * Este metodo recoge los valores de la contraseña dada por el bean de confirmarContraseña
	 * y verifica que consida con los requerimientos de la empresa para asi agregarla al cliente
	 * y subirla a la base de datos encriptada
	 * @return Stirng de la pagina principal
	 */

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
	
	/**
	 * Este metodo es llamdado por el bean con el objetivo de conseguir el valor guardado
	 * y redirigirlo a la pagina xhtml de Carro para continuar con la transaccion
	 * @return La pagina xhtml de carro
	 */

	public String seleciconarProducto() {
		return "Carro";
	}
	
	/**
	 * Este metodo agrega un producto a la instancia del carro y el numero de productos
	 * seleccionados por el cliente
	 * @return String la pagina principal
	 */
	public String agregarProductoAlCarro() {
		String retorno = "Principal";
		carroCompras.agregarProducto(proSelecc, cNumeroDeProductos);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Agregado al carro");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
	}
	
	/**
	 * Este metodo es llamdado por el bean con el objetivo de conseguir el valor guardado
	 * y redirigirlo a la pagina xhtml de Carro para terminar la transaccion
	 * @return String de la pagina finalizar Transaccion
	 */

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
	
	/**
	 * Este metodo quita un item de carro y manda un mensaje avisando que el producto se retiro
	 * @return String de la pagina xhtml de finalizarCarro
	 */

	public String quitarItemCarro() {
		carroCompras.retirarProducto(seProductoS, seProductoS.getCantidad());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "FinalizarCarro";
	}

	/**
	 * Este metodo se encarga de editar los valores de un vendedor y se guardan estos cambios
	 * tanto en la base de datos como en el arreglo pertenciente a DAO
	 * @return la paginaPrincipal
	 */
	public String editarVendedor() {
		seVendedor.setContraseña(Ultilidades.encriptador(seVendedor.getContraseña()));
		Presistence.actualizarVendedor(seVendedor);
		vend = seVendedor;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Perfil editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
	}
	
	/**
	 * Este metodo se encarga de editar todos los valores de productos del vendedor en session
	 * en la base de datos y el arreglo de DAO
	 * @return la paginaPrincipal
	 */
	
	public String realizarCambiosProductosVendedor() {
		for (Producto i : vend.getProductos()) {
			Presistence.actualizarProducto(i);
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Productos editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
	}
	
	/**
	 * Este metodo se encarga de eliminar un vendedor de la base de datos
	 * @return un string que devuelvew a una pagina dependiendo del exito del proceso de eliminacion
	 */
	
	public String eliminarVendedor() {
		Presistence.eliminarVendedor(seVendedor);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Eliminacion Exitosa");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		if (gere != null) {
			return "PerfilGerente";
		} else if (admin != null) {
			return "PerfilesAdministrador";
		}
		return "Principal";
	}
	
	/**
	 * Este metodo se encarga de eliminar un vendedor de la base de datos
	 * @return un string que devuelvew a una pagina dependiendo del exito del proceso de eliminacion
	 */
	
	public String editarCliente() {
		seCliente.setContraseña(seCliente.getContraseña());
		Presistence.actualizarCliente(seCliente);
		seCliente = cli;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Perfil editado exitosamente");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
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
	 * Getter de contraseña.
	 * @return contraseña
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * Setter de contraseña.
	 * @param contraseña
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * Getter del titulo de menu.
	 * @return menuTitulo
	 */
	public String getMenuTitulo() {
		return menuTitulo;
	}

	/**
	 * Setter del título de menu.
	 * @param menuTitulo
	 */
	public void setMenuTitulo(String menuTitulo) {
		this.menuTitulo = menuTitulo;
	}

	/**
	 * Getter de la opción seleccionada.
	 * @return opcionSeleccionada
	 */
	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	/**
	 * Setter de la opción seleccionada.
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

	public ArrayList<Producto> getSedProductos() {
		return sedProductos;
	}

	public void setSedProductos(ArrayList<Producto> sedProductos) {
		this.sedProductos = sedProductos;
	}

	public ArrayList<String> getSeCategoriasDisponibles() {
		return seCategoriasDisponibles;
	}

	public void setSeCategoriasDisponibles(ArrayList<String> seCategoriasDisponibles) {
		this.seCategoriasDisponibles = seCategoriasDisponibles;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas de vendedores
	 *  de acuerdo a la sede que pertenece un administrador
	 */
	public BarChartModel getBarChartModel() {
		barChartModel = Ultilidades.GrafiquitasVendedores(admin);
		return barChartModel;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  clientes con mayor numero de ventas a la sede que pertenece un administrador
	 */
	public BarChartModel getBarChartModel2() {
		barChartModel2 = Ultilidades.GrafiquitasTopClientes(admin);
		return barChartModel2;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  productos con mayor numero de ventas a la sede que pertenece un administrador
	 */
	public BarChartModel getBarChartModel3() {
		barChartModel3 = Ultilidades.GrafiquitasTopProductos(admin);
		return barChartModel3;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  vendedores con mayor numero de ventas entre todas las sedes
	 */
	public BarChartModel getBarChartModel4() {
		barChartModel4 = Ultilidades.GrafiquitasVendedoresGerencia();
		return barChartModel4;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  clientes con mayor numero de ventas entre todas las sedes
	 */
	public BarChartModel getBarChartModel5() {
		barChartModel5 = Ultilidades.GrafiquitasTopClientes();
		return barChartModel5;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  productos con mayor numero de ventas entre todas las sedes
	 */
	public BarChartModel getBarChartModel6() {
		barChartModel6 = Ultilidades.GrafiquitasTopProductosGerente();
		return barChartModel6;
	}
	
	/**
	 *  Getter del Barchart Model que representa las graficas estadisticas del top
	 *  sedes con mayor numero de ventas entre todas las sedes
	 */
	public BarChartModel getBarChartModel7() {
		barChartModel7= Ultilidades.GrafiquitasTopSedes();
		return barChartModel7;
	}

}
