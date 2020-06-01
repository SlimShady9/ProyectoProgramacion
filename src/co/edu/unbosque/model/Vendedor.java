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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



/**
 * La clase de persistencia para la tabla de vendedor de la base de datos. 
 */
@Entity
@Table(name = "VENDEDORES")
public class Vendedor  {
	/**
	 * Apellidos del vendedor.
	 */
	@Column(name = "APELLIDOS")
	private String apellidos;
	/**
	 * Contraseña del vendedor.
	 */
	@Column(name = "CONTRASEÑA")
	private String contraseña;
	/**
	 * Nombres del vendedor.
	 */
	@Column(name = "NOMBRES")
	private String nombres;
	/**
	 * Correo del vendedor.
	 */
	@Column(name = "CORREO")
	private String correo;
	/**
	 * Sede del vendedor.
	 */
	@Column(name = "SEDE")
	private String sede;
	/**
	 * Banco del vendedor.
	 */
	@Column(name = "BANCO")
	private String banco;
	/**
	 * Identificación del vendedor.
	 */
	@Column (name = "IDENTIFICACION")
	private String identificacion;
	/**
	 * Estado del vendedor.
	 */
	@Column (name = "Estado")
	private String estado;
	/**
	 * Usuario del vendedor.
	 */
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	/**
	 * Productos del vendedor.
	 */
	@OneToMany
	@JoinColumn (name = "VENDEDOR" )
	private List<Producto> productos = new ArrayList<Producto>();
	/**
	 * Ventas del vendedor.
	 */
	@OneToMany
	@JoinColumn (name = "VENDEDOR")
	private List<Ventas> ventas = new ArrayList<Ventas>();
	/**
	 * Constructor por defecto.
	 */
	public Vendedor() {
	}
	/**
	 * Getter de los apellidos.
	 * @return apellidos
	 */
	public String getApellidos() {
		return this.apellidos;
	}
	/**
	 * Setter de los apellidos.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Getter de la contraseña.
	 * @return contraseña
	 */
	public String getContraseña() {
		return this.contraseña;
	}
	/**
	 * Setter de la contraseña.
	 * @param contraseña
	 */
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	/**
	 * Getter de los nombres.
	 * @return nombres
	 */
	public String getNombres() {
		return this.nombres;
	}
	/**
	 * Setter de los nombres.
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * Getter de la sede.
	 * @return sede
	 */
	public String getSede() {
		return this.sede;
	}
	/**
	 * Setter de la sede.
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * Getter del usuario.
	 * @return usuario
	 */
	public String getUsuario() {
		return this.usuario;
	}
	/**
	 * Setter del usuario.
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * Getter de los productos.
	 * @return productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}
	/**
	 * Setter de los productos.
	 * @param productos
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	/**
	 * Getter del correo.
	 * @return
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * Setter del correo.
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * Getter del banco.
	 * @return banco
	 */
	public String getBanco() {
		return banco;
	}
	/**
	 * Setter del banco.
	 * @param banco
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}
	/**
	 * Getter de la identificación.
	 * @return identificación
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	/**
	 * Setter de la identificación.
	 * @param identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	/**
	 * Getter de las ventas.
	 * @return ventas
	 */
	public List<Ventas> getVentas() {
		return ventas;
	}
	/**
	 * Setter de la ventas.
	 * @param ventas
	 */
	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}
	/**
	 * Getter del estado del vendedor.
	 * @return estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * Setter del estado del vendedor.
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Método toString sobreescrito.
	 */


	@Override
	public String toString() {
		return "Vendedor [apellidos=" + apellidos + ", contraseña=" + contraseña + ", nombres=" + nombres + ", correo="
				+ correo + ", sede=" + sede + ", banco=" + banco + ", identificacion=" + identificacion + ", usuario="
				+ usuario + "]";
	}

}