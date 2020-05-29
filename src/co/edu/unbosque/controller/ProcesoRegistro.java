package co.edu.unbosque.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContexts;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import com.sun.mail.util.MailConnectException;

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
	private Ultilidades util = new Ultilidades();

	private String confirmacionContrase�a, numeroTarjeta = "";
	private static Cliente cli;
	private static Vendedor vend;

	public String guardarCliente() {
		String retorno;
		String men;

		try {
			String gRecaptchaResponse = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(verify){
				cliente.setContrase�a(Ultilidades.generarContrase�a());
				cliente.setContrase�a(Ultilidades.encriptador(cliente.getContrase�a()));
				if (Presistence.agregarCliente(cliente)) {
					men = "Registro exitoso! ";
					cli = cliente;
					vend = null;
					retorno = "ValidacionTarjeta";
					try {
						util.SendMailCliente(cliente);
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
				else {
					men = "Error en el registro...";
					retorno = "SigninCliente";
				}

			}else{
				men = "Seleccione Captcha";
				retorno = "SigninCliente";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			men = "error";
			retorno = "SinginCliente";
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}


		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return retorno;
	}

	public String guardarVendedor() {
		String retorno;
		String men;

		try {
			String gRecaptchaResponse = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
			boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			if(verify){
				vendedor.setContrase�a(Ultilidades.generarContrase�a());
				vendedor.setContrase�a(Ultilidades.encriptador(vendedor.getContrase�a()));
				if (Presistence.agregarVendedor(vendedor)) {
					men = "Registo Existoso! Bienenido ";
					vend = vendedor;
					cli = null;
					retorno = "ValidacionTarjeta";
					try {
						util.SendMailVendedor(vendedor);
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
				else {
					men = "Datos invalidos ";
					retorno = "SigninCliente";
				}
			}else{
				men = "Select Captcha";
				retorno = "SigninCliente";
			}

		} catch (Exception e) {
			e.printStackTrace();
			men = "error";
			retorno = "SinginCliente";
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
	FacesContext.getCurrentInstance().addMessage(null, msg);

	return retorno;
}

public String registrarTarjeta() {
	String men;
	if (Ultilidades.validarTarjeta(numeroTarjeta)) {
		if (cli == null) {
			vend.setBanco(numeroTarjeta);
			Presistence.actualizarVendedor(vend);
			men = vend.getNombres();
		}
		else {
			cli.setTarjetaCredito(numeroTarjeta);
			Presistence.actualizarCliente(cli);
			men = cli.getNombres();
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Finalizado! Inicia Seccion" + men);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "Principal";
	}
	else {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tarjeta no valida");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return "ValidacionTarjeta";
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

public String getNumeroTarjeta() {
	return numeroTarjeta;
}

public void setNumeroTarjeta(String numeroTarjeta) {
	this.numeroTarjeta = numeroTarjeta;
}



}
