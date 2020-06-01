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
package co.edu.unbosque.model;

import javax.persistence.*;



/**
 * La clase de persistencia para la tabla de administradores de la base de datos. 
 */
@Entity
@Table(name = "ADMINISTRADORES")
public class Administrador  {
	/**
	 * Apellidos del administrador.
	 */
	@Column(name = "APELLIDOS")
	private String apellidos;
	/**
	 * Contraseña del administrador.
	 */
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	/**
	 * Nombres del administrador.
	 */
	@Column(name = "NOMBRES")
	private String nombres;
	/**
	 * Correo del administrador.
	 */
	@Column(name = "CORREO")
	private String correo;
	/**
	 * Usuario del administrador.
	 */
	@Column(name = "USUARIO")
	private String usuario;
	/**
	 * Sede a la que pertence el administrador.
	 */
	@Id
	@Column(name = "SEDE")
	private String sede;
	/**
	 * Identificación del administrador
	 */
	@Column (name = "IDENTIFICACION")
	private String identificacion;
	
	/**
	 * Constructor por defecto.
	 */
	public Administrador() {
	}

	/**
	 * Getter de los apellidos.
	 * @return apellidos
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * Setter de los apellidos.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Getter de la contraseña.
	 * @return contraseña
	 */
	public String getContraseña() {
		return this.contraseña;
	}

	/**
	 * Setter de la contraseña.
	 * @param contraseña
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * Getter de los nombres.
	 * @return nombres
	 */
	public String getNombres() {
		return this.nombres;
	}

	/**
	 * Setter de los nombres.
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Getter del usuario.
	 * @return usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Setter del usuario.
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Getter de la sede.
	 * @return sede
	 */
	public String getSede() {
		return sede;
	}
	
	/**
	 * Setter de la sede.
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}

	/**
	 * Getter del correo.
	 * @return correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Setter del correo.
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Getter de la identificación.
	 * @return identificación
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Setter de la identificación.
	 * @param identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	

}