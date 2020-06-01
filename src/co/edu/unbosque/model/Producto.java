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

import javax.persistence.*;
import org.omnifaces.cdi.GraphicImageBean;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

/**
 * La clase de persistencia de la tabla de productos de la base de datos.
 */
@Entity
@Table(name = "PRODUCTOS")
@GraphicImageBean
public class Producto {
	/**
	 * Vendedor del producto.
	 */
	@ManyToOne
	@JoinColumn (name = "VENDEDOR")
	private Vendedor vendedor;
	/**
	 * Cantidad del producto.
	 */
	@Column(name = "CANTIDAD")
	private int cantidad;
	/**
	 * Categoria del producto.
	 */
	@Column(name = "CATEGORIA")
	private String categoria;
	/**
	 * Nombre del producto.
	 */
	@Id
	@Column(name = "NOMBRE")
	private String nombre;
	/**
	 * Precio del producto.
	 */
	@Column(name = "PRECIO")
	private double precio;
	/**
	 * Imagen del producto.
	 */
	@Column (name = "IMAGEN")
	private byte[] imagen;
	/**
	 * Unidades del producto.
	 */
	@Transient
	private ArrayList<Integer> cantidadToArray = new ArrayList<>();
	/**
	 * Constructor por defecto.
	 */
	public Producto() {
	}
	/**
	 * Getter de la cantidad.
	 * @return cantidad
	 */
	public int getCantidad() {
		return this.cantidad;
	}
	/**
	 * Setter de la cantidad.
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * Getter de la categoría.
	 * @return categoria
	 */
	public String getCategoria() {
		return this.categoria;
	}
	/**
	 * Setter de la categoria.
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**
	 * Getter del nombre.
	 * @return nombre
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Setter del nombre.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Getter del precio.
	 * @return precio
	 */
	public double getPrecio() {
		return this.precio;
	}
	/**
	 * Setter del precio.
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * Getter de la imagen.
	 * @return imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}
	/**
	 * Setter de la imagen.
	 * @param imagen
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	/**
	 * Getter del vendedor.
	 * @return vendedor
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}
	/**
	 * Setter del vendedor.
	 * @param vendedor
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	/**
	 * Carga el arreglo de las unidades del producto.
	 */
	public void cargarLista() {
		cantidadToArray = new ArrayList<Integer>();
		for (int i = 1 ; i <= cantidad ; i++) {
			cantidadToArray.add(i);
		}
	}
	/**
	 * Getter de la cantidad del producto.
	 * @return cantidadToArray.
	 */
	public ArrayList<Integer> getCantidadToArray() {
		cargarLista();
		return cantidadToArray;
	}
	/**
	 * Setter de la cantidad del producto.
	 * @param cantidadToArray.
	 */
	public void setCantidadToArray(ArrayList<Integer> cantidadToArray) {
		this.cantidadToArray = cantidadToArray;
	}
	/**
	 * Método toString() sobreescrito.
	 */

	@Override
	public String toString() {
		return "Producto [cantidad=" + cantidad + ", categoria=" + categoria + ", nombre=" + nombre + ", precio="
				+ precio + "]";
	}



}