package co.edu.unbosque.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.sun.mail.util.MailConnectException;

import co.edu.unbosque.controller.Presistence;

public class Carrito {

	private static ArrayList<Producto> productosdecarrito;
	private Cliente cliente;
	private boolean pagoTarjeta = false;
	private Date fecha;

	public Carrito(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Agrega un producto al carrito
	 * @param pro
	 * @param cantidad
	 */

	public void agregarProducto(Producto pro, int cantidad) {
	Vendedor vend = pro.getVendedor();
		List<Producto> productosvendedor= vend.getProductos();
		for(int i=0;i<productosvendedor.size();i++) {
			if(productosvendedor.get(i).equals(pro)) {
				productosvendedor.get(i).setCantidad(productosvendedor.get(i).getCantidad()-cantidad);
				break;
			}
		}
		vend.setProductos(productosvendedor);
		Presistence.actualizarVendedor(vend);
		Producto agregar = pro;
		agregar.setCantidad(cantidad);
		productosdecarrito.add(agregar);
	}
	//Este metodo regista la compra en la base de datos, agregando el producto a compras en cliente y
	// al respectivo vendedor  agregandolo a ventas.
	public void comprarProducto(Producto produc, Cliente cliente, int Nproductos, Date fecha, boolean reserva, String tipoDePago ) {
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
		cliente.setCompras(ventascliente);
		Presistence.actualizarCliente(cliente);
		
		List<Ventas> ventasvendedor= vend.getVentas();
		ventasvendedor.add(venta);
		vend.setVentas(ventasvendedor);
		Presistence.actualizarVendedor(vend);
	}

	/**
	 * Este metodo finaliza una transaccion y guarda los cambios en la base de datos
	 * @param fecha
	 * @param tipoPago
	 * @param reserva
	 */
	public void realizarTransaccion(Date fecha, String tipoPago, boolean reserva, String tipoDePago) {

		for(int i=0; i<productosdecarrito.size();i++) {
			comprarProducto(productosdecarrito.get(i), cliente, productosdecarrito.get(i).getCantidad(), fecha, reserva,tipoDePago);
		}
	}


	/**
	 * Metodo que retira un producto del carro, por ende restaura los valores
	 * anteriores antes de agregarse al carro
	 * 
	 */
	public void retirarProducto(Producto pro, int cantidad) {
		Vendedor vend = pro.getVendedor();
		List<Producto> productosvendedor= vend.getProductos();
		for(int i=0;i<productosvendedor.size();i++) {
			if(productosvendedor.get(i).equals(pro)) {
				productosvendedor.get(i).setCantidad(productosvendedor.get(i).getCantidad()+cantidad);
				break;
			}
		}
		vend.setProductos(productosvendedor);
		Presistence.actualizarVendedor(vend);
		
	}


	public boolean isPagoTarjeta() {
		return pagoTarjeta;
	}

	public void setPagoTarjeta(boolean pagoTarjeta) {
		this.pagoTarjeta = pagoTarjeta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



}
