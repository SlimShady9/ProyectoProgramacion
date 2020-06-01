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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * La clase de persistencia para la tabla de ventas de la base de datos.
 */
@Entity
@Table(name = "VENTAS")
public class Ventas {
	
	/**
	 * Vendedor de la venta.
	 */
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "VENDEDOR")
	private Vendedor vendedor;
/**
 * Comprador de la venta.
 */
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "COMPRADOR")
	private Cliente comprador;
	/**
	 * Estado de la venta.
	 */
	@Column (name = "RESERVA")
	private boolean reserva;
	/**
	 * Tipo de pago de la venta.
	 */
	@Column (name = "TIPODEPAGO")
	private String tipoPago;
	/**
	 * Sede de la venta.
	 */
	@Column(name = "SEDE")
	private String sede;
	/**
	 * Articulo de la venta.
	 */
	@Id
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
	 * Getter de la sede de la venta.
	 * @return sede.
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * Setter de la sede de la venta.
	 * @param sede
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * Getter del articulo de la venta.
	 * @return articulo
	 */
	public String getArticulo() {
		return articulo;
	}
	/**
	 * Setter del articulo de la venta.
	 * @param articulo
	 */
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	/**
	 * Getter de las unidades de la venta-
	 * @return unidades.
	 */
	public int getUnidades() {
		return unidades;
	}
	/**
	 * Setter de las unidades de la venta.
	 * @param unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	/**
	 * Getter del presio de la venta.
	 * @return precio.
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * Setter del precio de la venta.
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * Getter de la fecha de la venta.
	 * @return fecha.
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * Setter de la fecha de la venta.
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * Getter de estado de la venta.
	 * @return reserva, true si esta reservado, false si fue comprado.
	 */
	public boolean isReserva() {
		return reserva;
	}
	/**
	 * Setter de estado de la venta.
	 * @param reserva
	 */
	public void setReserva(boolean reserva) {
		this.reserva = reserva;
	}
	/**
	 * Getter del vendedor de la venta.
	 * @return vendedor.
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}
	/**
	 * Setter del vendedor de la venta.
	 * @param vendedor
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	/**
	 * Getter del comprador de la venta.
	 * @return comprador.
	 */
	public Cliente getComprador() {
		return comprador;
	}
	/**
	 * Setter del comprador de la venta.
	 * @param comprador
	 */
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	/**
	 * Getter del tipo de pago de la venta.
	 * @return tipoPago.
	 */
	public String getTipoPago() {
		return tipoPago;
	}
	/**
	 * Setter del tipo de pago de la venta.
	 * @param tipoPago
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	/**
	 * Método toString sobreescrito.
	 */
	@Override
	public String toString() {
		return "Ventas [vendedor=" + vendedor + ", comprador=" + comprador + ", reserva=" + reserva + ", tipoPago="
				+ tipoPago + ", sede=" + sede + ", articulo=" + articulo + ", unidades=" + unidades + ", precio="
				+ precio + ", fecha=" + fecha + "]";
	}
	
	
}