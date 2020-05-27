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
package co.edu.unbosque.model;

import javax.persistence.*;

/**
 * La clase de persistencia para la tabla de Gerencia de la base de datos.
 */
@Entity
@Table(name = "GERENTES")
public class Gerencia {

	/**
	 * Apellidos del gerente.
	 */
	@Column(name = "APELLIDOS")
	private int apellidos;
	
	/**
	 * Contrase�a del gerente.
	 */
	@Column(name = "CONTRASE�A")
	private String contrase�a;
	
	/**
	 * Nombres del gerente.
	 */
	@Column(name = "NOMBRES")
	private int nombres;
	
	/**
	 * Correo del gerente.
	 */
	@Column(name = "CORREO")
	private String correo;
	
	/**
	 * Usuario del gerente.
	 */
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	
	/**
	 * Identificaci�n del gerente.
	 */
	@Column (name = "IDENTIFICACION")
	private String identificacion;

	/**
	 * Constructor por defecto.
	 */
	public Gerencia() {
	}

	/**
	 * Getter de los Apellidos del gerente.
	 * @return apellidos
	 */
	public int getApellidos() {
		return this.apellidos;
	}

	/**
	 * Setter de los apellidos.
	 * @param apellidos
	 */
	public void setApellidos(int apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Getter de la contrase�a.
	 * @return contrase�a
	 */
	public String getContrase�a() {
		return this.contrase�a;
	}

	/**
	 * Setter de la contrase�a.
	 * @param contrase�a
	 */
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	/**
	 * Getter de los nombres.
 	 * @return nombres
	 */
	public int getNombres() {
		return this.nombres;
	}

	/**
	 * Setter de los nombres.
	 * @param nombres
	 */
	public void setNombres(int nombres) {
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
	 * Getter de la identificaci�n.
	 * @return identificaci�n
	 */
	public String getIdentificador() {
		return identificacion;
	}

	/**
	 * Setter de la identificaci�n.
	 * @param identificador
	 */
	public void setIdentificador(String identificador) {
		this.identificacion = identificador;
	}
	

}