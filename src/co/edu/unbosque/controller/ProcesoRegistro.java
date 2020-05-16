package co.edu.unbosque.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FlowEvent;
import co.edu.unbosque.model.Cliente;

@ManagedBean(name="procesoRegistro")
@ViewAccessScoped
public class ProcesoRegistro implements Serializable {

	private Cliente cliente = new Cliente();
	private String nombres, apellidos, usuario, celular, contrase�a, correo;
	private String nDocumento, tarjeta, confirmacionContrase�a, ciudad;

	private boolean skip;


	public String guardarCliente() {
		if (cliente.getContrase�a().equals(confirmacionContrase�a)) {
			
			
			FacesMessage msg = new FacesMessage("Registro Exitoso", "Bienvenido! :" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "principal";
		}
		else {
			
			FacesMessage msg = new FacesMessage("La clave no es igual" + cliente.getNombres());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "signin";
		}
	}


	public String getConfirmacionContrase�a() {
		return confirmacionContrase�a;
	}



	public void setConfirmacionContrase�a(String confirmacionContrase�a) {
		this.confirmacionContrase�a = confirmacionContrase�a;
	}



	public String getCiudad() {
		return ciudad;
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


	public String getContrase�a() {
		return contrase�a;
	}



	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
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
