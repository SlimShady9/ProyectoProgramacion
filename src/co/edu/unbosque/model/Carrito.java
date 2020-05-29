package co.edu.unbosque.model;

import java.sql.Date;
import java.util.ArrayList;

import co.edu.unbosque.controller.Presistence;

public class Carrito {
	
	private static ArrayList<Ventas> ventas;
	private ArrayList<Vendedor> vendedores;
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
		pro.setCantidad(pro.getCantidad() - cantidad);
		vendedores.add(pro.getVendedor());
		
		Producto producto = pro;
		producto.setCantidad(cantidad);
		
		Ventas venta = new Ventas();
		venta.setUnidades(cantidad);
		venta.setPrecio(pro.getPrecio());
		venta.setArticulo(pro.getNombre());
		venta.setSede(cliente.getCiudad());
		venta.setVendedor(pro.getVendedor());
		venta.setComprador(cliente);
		
		ventas.add(venta);
	}

	/**
	 * Este metodo finaliza una transaccion y guarda los cambios en la base de datos
	 * @param fecha
	 * @param tipoPago
	 * @param reserva
	 */
	public void realizarTransaccion(Date fecha, String tipoPago, boolean reserva) {
		
		for (Ventas i : ventas) {
			i.setFecha(fecha);
			i.setReserva(reserva);
			i.setTipoPago(tipoPago);
			cliente.getCompras().add(i);
			
			for (Vendedor v : vendedores) {
				if (v.getUsuario().equals(i.getVendedor().getUsuario())) {
					v.getVentas().add(i);
					Presistence.actualizarVendedor(v);
					break;
				}
			}
			
			Presistence.actualizarCliente(cliente);
		}
	}
	
	/**
	 * Metodo que retira un producto del carro, por ende restaura los valores
	 * anteriores antes de agregarse al carro
	 * 
	 */
	public void retirarProducto(Producto pro) {
		//Validar que el producto sea el mismo que la venta xd
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
