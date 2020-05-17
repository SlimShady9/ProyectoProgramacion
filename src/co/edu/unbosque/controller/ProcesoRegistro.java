package co.edu.unbosque.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FlowEvent;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Vendedor;

/**
 * @author Camilo
 *
 */
@ManagedBean(name="procesoRegistro")
@ViewAccessScoped
public class ProcesoRegistro implements Serializable {

	private Cliente cliente = new Cliente();
	private Vendedor vendedor = new Vendedor();
	
	private String nombres, apellidos, usuario, celular, contraseña, correo;
	private String nDocumento, tarjeta, confirmacionContraseña, ciudad;

	private boolean skip;


	public String guardarCliente() {
		if (cliente.getContraseña().equals(confirmacionContraseña)) {
			
			System.out.println("Registrado");
			FacesMessage msg = new FacesMessage("Registro Exitoso", "Bienvenido! :" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Principal";
		}
		else {
			System.out.println("No Registrado");
			FacesMessage msg = new FacesMessage("La clave no es igual" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "signincliente";
		}
	}
	
	public String guardarVendedor() {
		if (vendedor.getContraseña().equals(confirmacionContraseña)) {
			
			System.out.println("Vendedor Registrado");
			FacesMessage msg = new FacesMessage("Registro Exitoso", "Bienvenido! :" + vendedor.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Principal";
		}
		else {
			System.out.println("No Registrado");
			FacesMessage msg = new FacesMessage("La clave no es igual" + vendedor.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "signinvendedor";
		}
	}

	public String getConfirmacionContraseña() {
		return confirmacionContraseña;
	}



	public void setConfirmacionContraseña(String confirmacionContraseña) {
		this.confirmacionContraseña = confirmacionContraseña;
	}



	public String getCiudad() {
		return ciudad;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getContraseña() {
		return contraseña;
	}



	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	public String getnDocumento() {
		return nDocumento;
	}



	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}



	public String getTarjeta() {
		return tarjeta;
	}



	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	

}
