/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogot� - Colombia)
 * Programa de Ingenier�a de Sistemas
 * Programaci�n II
 * 
 * Profesor: Miguel Alejandro Feijoo Garc�a
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura Mar�a L�pez Moreno
 * 	@author	Andr�s Felipe Rey Pedraza
 * 	@author	Juan Camilo D�az
 * 	@author	Camilo Andr�s Romero Posada
 * 			
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package co.edu.unbosque.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import com.sun.mail.util.MailConnectException;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.resources.VerifyRecaptcha;

/**
 * Clase que gestiona el proceso de registro del vendedor o del cliente y el registro de los datos de la tarjeta de cr�dito.
 */
@ManagedBean(name="procesoRegistro")
@ViewAccessScoped
public class ProcesoRegistro implements Serializable {

	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	/**
	 * Serial por defecto de la clase.
	 */
	private static final long serialVersionUID = 1L;
	
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	
	/**
	 * Clase que representa al cliente.
	 */
	private Cliente cliente = new Cliente();
	
	/**
	 * Clase que representa al vendedor.
	 */
	private Vendedor vendedor = new Vendedor();
	
	/**
	 * Clase que representa las utilidades.
	 */
	private Ultilidades util = new Ultilidades();
	
	/**
	 * Cadena de texto acerca de la confirmaci�n de la Contrase�a del usuario y el numero de la tarjeta de cr�dito
	 * inicializado en "".
	 */
	private String confirmacionContrase�a, numeroTarjeta = "";
	
	/**
	 * Cliente de tipo estatico.
	 */
	private static Cliente cli;
	
	/**
	 * Vendedor de tipo estatico.
	 */
	private static Vendedor vend;
	
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

	/**
	 * Guarda un nuevo cliente a partir de los datos ingresados por un formulario.
	 * Para guardar satisfacotriamente el cliente se verifica que la contrase�a digitada
	 * sea igual a la confirmaci�n de la contrase�a. As� mismo, verifica la validez del 
	 * Recaptcha.
	 * @return la p�gina de validaci�n de la tarjeta de cr�dito en el caso exitoso de registro
	 * y la misma p�gina de registro en caso contrario.
	 */
	public String guardarCliente() {
		String retorno;
		String men;
		if (cliente.getContrase�a().equals(confirmacionContrase�a)) {
			try {
				String gRecaptchaResponse = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
				 boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			        if(verify){
			        	cliente.setContrase�a(Ultilidades.encriptador(cliente.getContrase�a()));
			        	if (Presistence.agregarCliente(cliente)) {
			        		men = "Registro exitoso! " + cliente.getNombres();
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
			
		}
		else {
			men = "La contrase�a no es igual digite de nuevo ";
			retorno = "SigninCliente";
			confirmacionContrase�a = "";
			cliente.setContrase�a("");
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return retorno;
	}
	
	/**
	 * Guarda un nuevo vendedror a partir de los datos ingresados por un formulario.
	 * Para guardar satisfacotriamente el vendedor se verifica que la contrase�a digitada
	 * sea igual a la confirmaci�n de la contrase�a. As� mismo, verifica la validez del 
	 * Recaptcha.
	 * @return la p�gina de validaci�n de la tarjeta de cr�dito en el caso exitoso de registro
	 * y la misma p�gina de registro en caso contrario.
	 */
	public String guardarVendedor() {
		String retorno;
		String men;
		if (vendedor.getContrase�a().equals(confirmacionContrase�a)) {
			try {
				String gRecaptchaResponse = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
				 boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			        if(verify){
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
			
		}
		else {
			men = "La contrase�a no es igual digite de nuevo ";
			retorno = "SigninCliente";
			confirmacionContrase�a = "";
			vendedor.setContrase�a("");
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return retorno;
	}
	
	/**
	 * Registra y valida los datos de la tarjeta de cr�dito. Si es exitoso permite iniciar sesi�n.
	 * @return la p�gina principal en el caso exitoso y la misma p�gina de validaci�n de la tarjeta
	 * en caso contrario.
	 */
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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Finalizado! Inicia Sesi�n" + men);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "Principal";
		}
		else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Tarjeta no valida");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "ValidacionTarjeta";
		}
		
		
	}
	
	/**
	 * Retorna la confirmaci�n de la contrase�a del usuario.
	 * @return la confirmaci�n de la contrase�a del usuario. 
	 * confirmaci�nContrase�a!=null, confirmaci�nContrase�a!="". 
	 */
	public String getConfirmacionContrase�a() {
		return confirmacionContrase�a;
	}

	/**
	 * Setter de la cadena de texto referente a la confirmaci�n de la contrase�a
	 * del usurio.
	 * @param confirmacionContrase�a
	 */
	public void setConfirmacionContrase�a(String confirmacionContrase�a) {
		this.confirmacionContrase�a = confirmacionContrase�a;
	}

	/**
	 * Getter del vendedor.
	 * @return vendedor.
	 */
	public Vendedor getVendedor() {
		return vendedor;
	}

	/**
	 * Setter del vendedor.
	 * @param vendedor
	 */
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * Getter del cliente.
	 * @return cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Setter del cliente.
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Getter del n�mero de la tarjeta de cr�dito.
	 * @return numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Setter del numero de la tarjeta de cr�dito.
	 * @param numeroTarjeta.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

}
