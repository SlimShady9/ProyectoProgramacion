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
	
	private String confirmacionContrase�a;

	private boolean skip;


	public String guardarCliente() {
		if (cliente.getContrase�a().equals(confirmacionContrase�a)) {
			
			System.out.println("Registrado");
			FacesMessage msg = new FacesMessage("Registro Exitoso", "Bienvenido! :" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Principal";
		}
		else {
			System.out.println("No Registrado");
			FacesMessage msg = new FacesMessage("La clave no es igual" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			confirmacionContrase�a = "";
			cliente.setContrase�a("");
			return "SigninCliente";
		}
	}
	
	public String guardarVendedor() {
		if (vendedor.getContrase�a().equals(confirmacionContrase�a)) {
			
			System.out.println("Vendedor Registrado");
			FacesMessage msg = new FacesMessage("Registro Exitoso", "Bienvenido! :" + vendedor.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Principal";
		}
		else {
			System.out.println("No Registrado");
			FacesMessage msg = new FacesMessage("La clave no es igual" + vendedor.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			confirmacionContrase�a = "";
			vendedor.setContrase�a("");
			return "SigninVendedor";
		}
	}

	public String getConfirmacionContrase�a() {
		return confirmacionContrase�a;
	}

	public void setConfirmacionContrase�a(String confirmacionContrase�a) {
		this.confirmacionContrase�a = confirmacionContrase�a;
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
