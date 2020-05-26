package co.edu.unbosque.model;

import javax.persistence.*;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the Productos database table.
 * 
 */
@Entity
@Table(name = "PRODUCTOS")
public class Producto {

	@Id
	@GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "VENDEDOR")
	private Vendedor vendedor;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "CATEGORIA")
	private String categoria;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "PRECIO")
	private double precio;
	
	@Lob
	@Column (name = "IMAGEN", columnDefinition = "LONGVARBINARY")
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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