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
	
	private String usuario, contraseña;
	
	private static String mTitulo;
	private String menuTitulo = mTitulo, opcionSeleccionada;
	
	
	public String inicioSeccion() {
		admin = Presistence.buscarAdministradores(usuario);
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
				vend = Presistence.buscarVendedores(usuario);
				if (vend != null) {
					if (!Ultilidades.desencriptador(vend.getContraseña()).equals(contraseña)) {
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
		if (opcionSeleccionada.equals("Iniciar Session")) {
			return "Login";
		}
		if (opcionSeleccionada.equals("Resgistrate")) {
			return "RegistroComo";
		}
		return null;
		
	}
	
	public void mostrarOpciones() {
		mTitulo = "Bienvenido ";
		if (seCliente != null) {
			mTitulo += seCliente.getUsuario();
			opIniciales = new String[] {
					"Inicio", "Categorias",
					"Perfil", "Mis compras", "Notificaciones",
					"Cerrar Seccion", "Ayuda"
			};
		} else if (seVendedor != null) {
			mTitulo += seVendedor.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Perfil", "Mis ventas", "Mis productos",
					"Notificaciones", "Cerrar Seccion", "Ayuda"
			};
		} else if (seAdmin != null) {
			menuTitulo += seAdmin.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Perfil", "Reportes", "Clientes",
					"Vendedores", "Cerrar Seccion"
			};
			
		} else if (seGerente != null) {
			mTitulo += seGerente.getUsuario();
			opIniciales = new String[] {
					"Inicio",
					"Perfil",  "Cerrar Seccion"
			};

		} else {
			mTitulo += "<3";
		}
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
