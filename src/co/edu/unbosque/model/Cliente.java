package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

;


/**
 * The persistent class for the Clientes database table.
 * 
 */
@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Column(name = "CELULAR")
	private String celular;
	
	@Column(name = "SEDE")
	private String ciudad;
	
	@Column(name = "CONTRASE�A")
	private String contrase�a;
	
	@Column(name = "CORREO")
	private String correo;
	
	@Column(name = "NOMBRES")
	private String nombres;
	
	@Column(name = "TIPODOCUMENTO")
	private String tipoDocumento;
	
	@Column(name = "NDOCUMENTO")
	private String numeroDocumento;
	
	@Column(name = "TARJETA")
	private String tarjetaCredito;

	@Id
	@Column(name = "USUARIO")
	private String usuario;
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn (name = "COMPRADOR")
	private List<Ventas> compras = new ArrayList<Ventas>();

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

	public String getContrase�a() {
		return this.contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<Ventas> getProductos() {
		return compras;
	}

	public void setProductos(List<Ventas> compras) {
		this.compras = compras;
	}

	public List<Ventas> getCompras() {
		return compras;
	}

	public void setCompras(List<Ventas> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return "Cliente [apellidos=" + apellidos + ", celular=" + celular + ", ciudad=" + ciudad + ", contrase�a="
				+ contrase�a + ", correo=" + correo + ", nombres=" + nombres + ", tipoDocumento=" + tipoDocumento
				+ ", numeroDocumento=" + numeroDocumento + ", tarjetaCredito=" + tarjetaCredito + "]";
	}
	

}