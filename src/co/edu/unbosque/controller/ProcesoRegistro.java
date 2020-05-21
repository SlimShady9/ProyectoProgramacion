package co.edu.unbosque.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContexts;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FlowEvent;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.resources.VerifyRecaptcha;

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


	public String guardarCliente() {
		String retorno;
		String men;
		if (cliente.getContrase�a().equals(confirmacionContrase�a)) {
			try {
				String gRecaptchaResponse = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
				 boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			        if(verify){
			        	System.out.println(cliente.toString());
			        	if (Presistence.agregarCliente(cliente)) {
							men = "Registo Existoso! Bienenido ";
							retorno = "Principal";
						}
						else {
							men = "Datos invalidos ";
							retorno = "SigninCliente";
						}
			        }else{
			             men = "Select Captcha";
			             retorno = "SigninCliente";
			          }
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				men = "error";
				retorno = "SinginCliente";
			}
			
		}
		else {
			men = "La contrase�a no es igual digite de nuevo ";
			retorno = "SigninCliente";
			confirmacionContrase�a = "";
			cliente.setContrase�a("");
		}

		FacesMessage msg = new FacesMessage(men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return retorno;
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
