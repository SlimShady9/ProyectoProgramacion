package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Administrador database table.
 * 
 */
@Entity
@NamedQuery(name="Administradores.findAll", query="SELECT a FROM Administradores a")
public class Administrador implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Lob
	@Column(name="Usuario")
	private String usuario;
	
	@Lob
	@Column(name="Apellidos")
	private String apellidos;

	@Lob
	@Column(name="Contrase�a")
	private String contrase�a;

	@Lob
	@Column(name="Nombres")
	private String nombres;

	

	public Administrador() {
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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

}