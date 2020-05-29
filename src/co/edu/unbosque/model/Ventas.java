package co.edu.unbosque.model;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VENTAS")
public class Ventas {
	
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "VENDEDOR")
	private Vendedor vendedor;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "COMPRADOR")
	private Cliente comprador;
	
	@Column (name = "RESERVA")
	private boolean reserva;
	
	@Column (name = "TIPODEPAGO")
	private String tipoPago;
	
	@Column(name = "SEDE")
	private String sede;
	
	@Column(name = "ARTICULO")
	private String articulo;
	
	@Column(name = "UNIDADES")
	private int unidades;
	
	@Column(name = "PRECIO")
	private double precio;
	
	@Id
	@Column(name = "FECHA")
	private Date fecha;
	
	
	public String getSede() {
		return sede;
	}
	public void setSede(String sede) {
		this.sede = sede;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isReserva() {
		return reserva;
	}
	public void setReserva(boolean reserva) {
		this.reserva = reserva;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public Cliente getComprador() {
		return comprador;
	}
	public void setComprador(Cliente comprador) {
		this.comprador = comprador;
	}
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	
}
