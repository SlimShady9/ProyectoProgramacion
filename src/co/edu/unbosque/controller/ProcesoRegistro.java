package co.edu.unbosque.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import co.edu.unbosque.model.Cliente;

@ManagedBean(name="procesoRegistro")
@RequestScoped
public class ProcesoRegistro implements Serializable {

	private Cliente cliente = new Cliente();
	private String nombres, apellidos, usuario, celular, contraseña, correo;
	private String nDocumento, tarjeta, confirmacionContraseña, ciudad;

	private boolean skip;


	public void save() {
		FacesMessage msg = new FacesMessage("Successful", "Welcome :" + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}



	public String onFlowProcess(FlowEvent event) {
		if (nombres != null) {
			if (cliente.getNombres() == null) {
				cliente.setNombres(nombres);
			}else if (!cliente.getNombres().equals(nombres)) {
				cliente.setNombres(nombres);
			}
		}
		if (apellidos != null) {
			if (cliente.getApellidos() == null) {
				cliente.setApellidos(apellidos);
			}else if (!cliente.getApellidos().equals(apellidos)) {
				cliente.setApellidos(apellidos);
			}
		}
		if (usuario!= null) {
			if (cliente.getUsuario() == null) {
				cliente.setUsuario(usuario);
			}else if (!cliente.getUsuario().equals(usuario)) {
				cliente.setUsuario(usuario);
			}
		}
		if (celular!= null) {
			if (cliente.getCelular() == null) {
				cliente.setCelular(celular);
			}else if (!cliente.getCelular().equals(celular)) {
				cliente.setCelular(celular);
			}	
		}
		if (contraseña != null && confirmacionContraseña != null) {
			if (cliente.getContraseña() == null) {
				if (contraseña.equals(confirmacionContraseña) && confirmacionContraseña != null) {
					cliente.setContraseña(contraseña);
				}
			}else if (!cliente.getContraseña().equals(contraseña)) {
				if (contraseña.equals(confirmacionContraseña) && confirmacionContraseña != null) {
					cliente.setContraseña(contraseña);
				}
			}
			
		}
		
		return event.getNewStep();

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
