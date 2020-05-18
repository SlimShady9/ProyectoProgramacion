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
	
	private String confirmacionContraseña;

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
			confirmacionContraseña = "";
			cliente.setContraseña("");
			return "SigninCliente";
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
			confirmacionContraseña = "";
			vendedor.setContraseña("");
			return "SigninVendedor";
		}
	}

	public String getConfirmacionContraseña() {
		return confirmacionContraseña;
	}

	public void setConfirmacionContraseña(String confirmacionContraseña) {
		this.confirmacionContraseña = confirmacionContraseña;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
