package co.edu.unbosque.model;


import java.util.ArrayList;
import java.util.List;

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
	
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Column(name = "SEDE")
	private String sede;
	
	@Column(name = "BANCO")
	private String banco;
	
	@Column (name = "IDENTIFICACION")
	private String identificacion;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "VENDEDOR" )
	private List<Producto> productos = new ArrayList<Producto>();
	
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn (name = "VENDEDOR")
	private List<Ventas> ventas = new ArrayList<Ventas>();
	
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
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Vendedor [apellidos=" + apellidos + ", contraseña=" + contraseña + ", nombres=" + nombres + ", correo="
				+ correo + ", sede=" + sede + ", banco=" + banco + ", identificacion=" + identificacion + ", usuario="
				+ usuario + "]";
	}

}