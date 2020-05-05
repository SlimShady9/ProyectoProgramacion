package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RegistroClientes database table.
 * 
 */
@Entity
@Table(name="Clientes")
@NamedQuery(name="Clientes.findAll", query="SELECT r FROM Cliente r")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name="Apellidos")
	private String apellidos;

	@Lob
	@Column(name="Celular")
	private String celular;

	@Lob
	@Column(name="Ciudad")
	private String ciudad;

	@Lob
	@Column(name="Contraseña")
	private String contraseña;

	@Lob
	@Column(name="Correo")
	private String correo;

	@Lob
	@Column(name="Nombres")
	private String nombres;

	@Lob
	@Column(name="NumeroDocumento")
	private String numeroDocumento;

	@Lob
	@Column(name="TarjetaCredito")
	private String tarjetaCredito;

	@Column(name="TipoDocumento")
	private int tipoDocumento;
	
	@Id
	@Lob
	@Column(name="Usuario")
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