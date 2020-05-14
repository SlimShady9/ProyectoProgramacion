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
	
	@Column(name = "CONTRASE�A")
	private int contrase�a;
	
	@Column(name = "NOMBRES")
	private int nombres;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Id
	@Column(name = "USUARIO")
	private int usuario;

	public Gerencia() {
	}

	public int getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(int apellidos) {
		this.apellidos = apellidos;
	}

	public int getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(int contrase�a) {
		this.contrase�a = contrase�a;
	}

	public int getNombres() {
		return this.nombres;
	}

	public void setNombres(int nombres) {
		this.nombres = nombres;
	}

	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}