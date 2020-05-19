package co.edu.unbosque.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VENTAS")
public class Ventas {
	
	@Id
	@Column(name = "APELLIDOS")
	private int id;
	
	@Column(name = "SEDE")
	private String sede;
	
	@Column(name = "ARTICULO")
	private String articulo;
	
	@Column(name = "UNIDADES")
	private int unidades;
	
	@Column(name = "PRECIO")
	private double precio;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
}
