package co.edu.unbosque.model;

import javax.persistence.*;
import org.omnifaces.cdi.GraphicImageBean;

/**
 * The persistent class for the Productos database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
@GraphicImageBean
public class Producto {



	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "VENDEDOR")
	private Vendedor vendedor;

	@Column(name = "CANTIDAD")
	private int cantidad;

	@Column(name = "CATEGORIA")
	private String categoria;

	@Id
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "PRECIO")
	private double precio;

	@Column (name = "IMAGEN")
	private byte[] imagen;

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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}


	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	


	@Override
	public String toString() {
		return "Producto [cantidad=" + cantidad + ", categoria=" + categoria + ", nombre=" + nombre + ", precio="
				+ precio + "]";
	}

	

}