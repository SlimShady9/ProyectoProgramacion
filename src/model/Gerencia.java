package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Gerencia database table.
 * 
 */
@Entity
@NamedQuery(name="Gerencia.findAll", query="SELECT g FROM Gerencia g")
public class Gerencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Apellidos")
	private int apellidos;

	@Column(name="Contraseña")
	private int contraseña;

	@Column(name="Nombres")
	private int nombres;
	
	@Id
	@Column(name="Usuario")
	private int usuario;

	public Gerencia() {
	}

	public int getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(int apellidos) {
		this.apellidos = apellidos;
	}

	public int getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(int contraseña) {
		this.contraseña = contraseña;
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

}