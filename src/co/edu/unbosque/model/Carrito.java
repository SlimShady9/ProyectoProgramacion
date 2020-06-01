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
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import com.sun.mail.util.MailConnectException;
import co.edu.unbosque.controller.Presistence;
import co.edu.unbosque.controller.Ultilidades;

public class Carrito {
	/**
	 * Clase encargada de manejar todas las gestiones de compras del cliente.
	 */

	private static ArrayList<Producto> productosdecarrito = new ArrayList<Producto>();
	private ArrayList<Producto> seProuctosDeCarrito;
	private Cliente cliente;
	private boolean pagoTarjeta = false;
	private Date fecha;
	/**
	 * Constructor de la clase .
	 * @param cliente, el cliente al que se le asigna el producto.
	 */
	public Carrito(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Agrega un producto al ArrayList productosdecarrito.
	 * @param pro.
	 * @param cantidad.
	 */

	public void agregarProducto(Producto pro, int cantidad) {
		Vendedor vend = pro.getVendedor();
		int cont = 0;

		List<Producto> productosvendedor= vend.getProductos();
		for(int i=0;i<productosvendedor.size();i++) {
			if(productosvendedor.get(i).equals(pro)) {
				productosvendedor.get(i).setCantidad(productosvendedor.get(i).getCantidad()-cantidad);
				cont = i;
				break;
			}
		}
		vend.setProductos(productosvendedor);
		Presistence.actualizarProducto(productosvendedor.get(cont));
		Producto agregar = pro;
		agregar.setCantidad(cantidad);
		productosdecarrito.add(agregar);
	}
	/**
	 * Actializa las ventas del cliente en el Arreglo que se maneja en el sistema.
	 * @param produc.
	 * @param cliente.
	 * @param Nproductos.
	 * @param fecha.
	 * @param reserva.
	 * @param tipoDePago.
	 */
	public void actualizarVentasCliente(Producto produc, Cliente cliente, int Nproductos,Date fecha, boolean reserva, String tipoDePago) {
		List<Ventas> ventascliente= cliente.getCompras();
		Ventas venta = new Ventas();
		Vendedor vend = produc.getVendedor();
		venta.setArticulo(produc.getNombre());
		venta.setComprador(cliente);
		venta.setVendedor(vend);
		venta.setPrecio(produc.getPrecio());
		venta.setSede(produc.getVendedor().getSede());
		venta.setUnidades(Nproductos);
		venta.setFecha(fecha);
		venta.setTipoPago(tipoDePago);
		venta.setReserva(reserva);
		ventascliente.add(venta);
		for (Ventas i : ventascliente) {
			Presistence.agregarVenta(i);
		}
		cliente.setCompras(ventascliente);	
		Presistence.modificarClienteNOSQL(cliente);
		try {
			if(!reserva) {
				Ultilidades.SendMailComprar(cliente, produc, Nproductos);
				Ultilidades.SendMailVentas(produc.getVendedor(), produc,Nproductos);
			}else{
				Ultilidades.SendMailReserva(produc.getVendedor(), produc,Nproductos);
				Ultilidades.SendMailReservar(cliente, produc, Nproductos);
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Este metodo finaliza una transaccion y guarda los cambios en la base de datos.
	 * @param fecha.
	 * @param tipoPago.
	 * @param reserva.
	 */
	public void realizarTransaccion(Date fecha, String tipoPago, boolean reserva) {

		for(int i=0; i<productosdecarrito.size();i++) {
			actualizarVentasCliente(productosdecarrito.get(i), cliente, productosdecarrito.get(i).getCantidad(), fecha, reserva, tipoPago);
		}
		productosdecarrito = new ArrayList<Producto>();
	}


	/**
	 * Metodo que retira un producto del carro, por ende restaura los valores.
	 * anteriores antes de agregarse al carro.
	 * @param pro
	 * @param cantidad
	 */
	public void retirarProducto(Producto pro, int cantidad) {
		Vendedor vend = pro.getVendedor();
		List<Producto> productosvendedor= vend.getProductos();
		for(int i=0;i<productosvendedor.size();i++) {
			if(productosvendedor.get(i).equals(pro)) {
				productosvendedor.get(i).setCantidad(productosvendedor.get(i).getCantidad()+cantidad);
				Presistence.actualizarProducto(productosvendedor.get(i));
				break;
			}
		}
		for(int i=0; i<productosdecarrito.size();i++) {
			if(productosdecarrito.get(i).equals(pro)) {
				productosdecarrito.remove(i);
				break;
			}
		}
		vend.setProductos(productosvendedor);
		Presistence.modificarVendedorNOSQL(vend);

	}

	/**
	 * Getter del tipo de pago.
	 * @return pagoTarjeta, true si lo pagan con tarjeta, false si es por efectivo.
	 */
	public boolean isPagoTarjeta() {
		return pagoTarjeta;
	}
	/**
	 * Setter del tipo de pago.
	 * @param pagoTarjeta.
	 */
	public void setPagoTarjeta(boolean pagoTarjeta) {
		this.pagoTarjeta = pagoTarjeta;
	}
	/**
	 * Getter de fecha en la que s registra el producto.
	 * @return fecha.
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * Setter de fecha en la que s registra el producto.
	 * @param fecha.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * Getter de los productos del carrito.
	 * @return productosdecarrito.
	 */
	public static ArrayList<Producto> getProductosdecarrito() {
		return productosdecarrito;
	}
	/**
	 * Setter de los productos del carrito.
	 * @param productosdecarrito.
	 */
	public static void setProductosdecarrito(ArrayList<Producto> productosdecarrito) {
		Carrito.productosdecarrito = productosdecarrito;
	}
	/**
	 * Getter del cliente que hace la compra.
	 * @return cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * Setter del cliente que hace la compra.
	 * @param cliente,
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * Getter de los productos ingresados en el carrito.
	 * @return seProuctosDeCarrito
	 */
	public ArrayList<Producto> getSeProuctosDeCarrito() {
		seProuctosDeCarrito = productosdecarrito;
		return seProuctosDeCarrito;
	}
	/**
	 * Setter de los productos ingresados en el carrito.
	 * @param seProuctosDeCarrito
	 */
	public void setSeProuctosDeCarrito(ArrayList<Producto> seProuctosDeCarrito) {
		this.seProuctosDeCarrito = seProuctosDeCarrito;
		productosdecarrito = seProuctosDeCarrito;
	}

}