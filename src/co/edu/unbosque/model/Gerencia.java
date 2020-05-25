package co.edu.unbosque.model;

import javax.persistence.*;


/**
 * The persistent class for the Gerencia database table.
 * 
 */
@Entity
@Table(name = "GERENTES")
public class Gerencia {

	@Column(name = "APELLIDOS")
	private int apellidos;
	
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	
	@Column(name = "NOMBRES")
	private int nombres;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column (name = "IDENTIFICACION")
	private String identificacion;

	public Gerencia() {
	}

	public int getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(int apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getNombres() {
		return this.nombres;
	}

	public void setNombres(int nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getIdentificador() {
		return identificacion;
	}

	public void setIdentificador(String identificador) {
		this.identificacion = identificador;
	}
	

}