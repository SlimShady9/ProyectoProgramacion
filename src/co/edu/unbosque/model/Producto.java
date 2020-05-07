package co.edu.unbosque.model;

import javax.persistence.*;

/**
 * The persistent class for the Productos database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
public class Producto {

	@Id
    @Column(name = "id", updatable = false, nullable = false)
	private long id;
	private int cantidad;
	private String categoria;
	private String nombre;
	private double precio;

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