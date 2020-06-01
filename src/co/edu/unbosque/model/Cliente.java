/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Programación II
 * 
 * Profesor: Miguel Alejandro Feijoo García
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura María López Moreno
 * 	@author	Andrés Felipe Rey Pedraza
 * 	@author	Juan Camilo Díaz
 * 	@author	Camilo Andrés Romero Posada
 * 			
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;



/**
 * La clase de persistencia para la table de los clientes de la base de datos. * 
 */
@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {
	/**
	 * Apellidos del cliente.
	 */
	@Column(name = "APELLIDOS")
	private String apellidos;
	/**
	 * Celular del cliente.
	 */
	@Column(name = "CELULAR")
	private String celular;
	/**
	 * Ciudad y sede del cliente.
	 */
	@Column(name = "SEDE")
	private String ciudad;
	/**
	 * Contraseña del cliente.
	 */
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	/**
	 * Correo del cliente.
	 */
	@Column(name = "CORREO")
	private String correo;
	/**
	 * Nombres del cliente.
	 */
	@Column(name = "NOMBRES")
	private String nombres;
	/**
	 * Tipo de documento del cliente.
	 */
	@Column(name = "TIPODOCUMENTO")
	private String tipoDocumento;
	/**
	 * Número de documento de identificación del cliente.
	 */
	@Column(name = "NDOCUMENTO")
	private String numeroDocumento;
	/**
	 * Número de la tarjeta de crédito del cliente.
	 */
	@Column(name = "TARJETA")
	private String tarjetaCredito;
	/**
	 * Estado de registro del cliente.
	 */
	@Column(name = "ESTADO")
	private String estado;
	/**
	 * Usuario del cliente.
	 */
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	/**
	 * Compras del cliente.
	 */
	@OneToMany
	@JoinColumn (name = "COMPRADOR")
	private List<Ventas> compras = new ArrayList<Ventas>();

	/**
	 * Constructor del cliente por defecto.
	 */
	public Cliente() {
	}

	/**
	 * Getter de los apellidos del cliente.
	 * @return apellidos
	 */
	public String getApellidos() {
		return this.apellidos;
	}

	/**
	 * Setter de los apellidos del cliente.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Getter del número de celular del cliente
	 * @return celular
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * Setter del número de celular del cliente.
	 * @param celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Getter de la ciudad de residencia del cliente.
	 * @return ciudad
	 */
	public String getCiudad() {
		return this.ciudad;
	}

	/**
	 * Setter de la ciudad de residencia del cliente.
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Getter de la contraseña del cliente.
	 * @return contraseña
	 */
	public String getContraseña() {
		return this.contraseña;
	}

	/**
	 * Setter de la contraseña del cliente.
	 * @param contraseña
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	/**
	 * Getter del correo del cliente.
	 * @return correo
	 */
	public String getCorreo() {
		return this.correo;
	}

	/**
	 * Setter del correo del cliente.
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Getter de los nombres del cliente.
	 * @return nombres
	 */
	public String getNombres() {
		return this.nombres;
	}

	/**
	 * Setter de los nombres del cliente.
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Getter del número de documento de identificación del cliente.
	 * @return numeroDocumento
	 */
	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	/**
	 * Setter del número de documento de identificación del cliente.
	 * @param numeroDocumento
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Getter de la tarjeta de crédito del cliente.
	 * @return tarjetaCredito
	 */
	public String getTarjetaCredito() {
		return this.tarjetaCredito;
	}

	/**
	 * Setter de la tarjeta de crédito del cliente.
	 * @param tarjetaCredito
	 */
	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}

	/**
	 * Getter del usuario del cliente
	 * @return usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * Setter del usuario del cliente
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Getter del tipo de documento del cliente.
	 * @return tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Setter del tipo de documento del cliente.
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Getter de los productos del cliente.
	 * @return compras
	 */
	public List<Ventas> getProductos() {
		return compras;
	}

	/**
	 * Setter de los productos del cliente.
	 * @param compras
	 */
	public void setProductos(List<Ventas> compras) {
		this.compras = compras;
	}

	/**
	 * Getter de las compras del cliete.
	 * @return compras
	 */
	public List<Ventas> getCompras() {
		return compras;
	}

	/**
	 * Setter de las compras del cliente.
	 * @param compras
	 */
	public void setCompras(List<Ventas> compras) {
		this.compras = compras;
	}
	/**
	 * Getter del estado de registro del cliente.
	 * @return
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * Setter del estado de registro del cliente.
	 * @param estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * El método sobreescrito del toString().
	 */
	@Override
	public String toString() {
		return "Cliente [apellidos=" + apellidos + ", celular=" + celular + ", ciudad=" + ciudad + ", contraseña="
				+ contraseña + ", correo=" + correo + ", nombres=" + nombres + ", tipoDocumento=" + tipoDocumento
				+ ", numeroDocumento=" + numeroDocumento + ", tarjetaCredito=" + tarjetaCredito + ", usuario=" + usuario
				+ ", compras=" + compras + "]";
	}




}