package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Productos database table.
 * 
 */
@Entity
@Table(name="Productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="Cantidad")
	private int cantidad;

	@Lob
	@Column(name="Categoria")
	private String categoria;

	@Lob
	@Column(name="Nombre")
	private String nombre;

	@Column(name="Precio")
	private double precio;

	@Lob
	@Column(name="Sede")
	private String sede;

	public Producto() {
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

}