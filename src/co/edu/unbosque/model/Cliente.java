package co.edu.unbosque.model;

import javax.persistence.*;

;


/**
 * The persistent class for the Clientes database table.
 * 
 */
@Entity
@Table(name = "CLIENTES")
public class Cliente  {

	@Column(name = "Apellidos")
	private String apellidos;
	
	@Column(name = "CELULAR")
	private String celular;
	
	@Column(name = "CIUDAD")
	private String ciudad;
	
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "NDOCUMENTO")
	private String numeroDocumento;
	
	@Column(name = "TARJETA")
	private String tarjetaCredito;
	
	@Column(name = "TIPODOCUMENTO")
	private int tipoDocumento;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;

	public Cliente() {
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTarjetaCredito() {
		return this.tarjetaCredito;
	}

	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	public int getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}