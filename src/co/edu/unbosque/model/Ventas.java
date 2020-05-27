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

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * La clase de persistencia para la tabla de ventas de la base de datos.
 */
@Entity
@Table(name = "VENTAS")
public class Ventas {	
	
	/**
	 * Id de la venta.
	 */
	@Id
	@Column(name = "APELLIDOS")
	private int id;
	
	/**
	 * Sede de la venta.
	 */
	@Column(name = "SEDE")
	private String sede;
	
	/**
	 * Artículo de la venta.
	 */
	@Column(name = "ARTICULO")
	private String articulo;
	
	/**
	 * Unidades de la venta.
	 */
	@Column(name = "UNIDADES")
	private int unidades;
	
	/**
	 * Precio de la venta.
	 */
	@Column(name = "PRECIO")
	private double precio;
	
	/**
	 * Fecha de la venta.
	 */
	@Column(name = "FECHA")
	private Date fecha;
	
	/**
	 * Getter del Id.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter del Id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter de la sede.
	 * @return sede
	 */
	public String getSede() {
		return sede;
	}
	
	/**
	 * Setter de la sede.
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	
	/**
	 * Getter del artículo.
	 * @return articulo
	 */
	public String getArticulo() {
		return articulo;
	}
	
	/**
	 * Setter del artículo.
	 * @param articulo
	 */
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
	/**
	 * Getter de las unidades.
	 * @return unidades
	 */
	public int getUnidades() {
		return unidades;
	}
	
	/**
	 * Setter de las unidades.
	 * @param unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	/**
	 * Getter del precio.
	 * @return precio
	 */
	public double getPrecio() {
		return precio;
	}
	
	/**
	 * Setter del precio.
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
	 * Getter de la fecha.
	 * @return fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Setter de la fecha.
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
