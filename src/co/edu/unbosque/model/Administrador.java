package co.edu.unbosque.model;

import javax.persistence.*;


/**
 * The persistent class for the ADMINISTRADORES database table.
 * 
 */
@Entity
@Table(name = "ADMINISTRADORES")
public class Administrador  {
	
	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "SEDE")
	private String sede;
	
	

	public Administrador() {
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

}