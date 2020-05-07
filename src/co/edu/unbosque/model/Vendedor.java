package co.edu.unbosque.model;


import javax.persistence.*;



/**
 * The persistent class for the Vendedor database table.
 * 
 */
@Entity
@Table(name = "VENDEDORES")
public class Vendedor  {

	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Column(name = "CONTRASE�A")
	private String contrase�a;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "SEDE")
	private String sede;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;

	public Vendedor() {
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