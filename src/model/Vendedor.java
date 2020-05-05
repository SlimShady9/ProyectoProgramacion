package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Vendedor database table.
 * 
 */
@Entity
@NamedQuery(name="Vendedores.findAll", query="SELECT v FROM Vendedores v")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name="Apellidos")
	private String apellidos;

	@Lob
	@Column(name="Contraseña")
	private String contraseña;

	@Lob
	@Column(name="Nombres")
	private String nombres;

	@Lob
	@Column(name="Sede")
	private String sede;
	@Id
	@Lob
	@Column(name="Usuario")
	private String usuario;

	public Vendedor() {
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

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}