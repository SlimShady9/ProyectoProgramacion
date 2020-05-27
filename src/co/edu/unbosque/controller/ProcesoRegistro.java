/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Programación II
 * 
 * Profesor: Miguel Alejandro Feijoo García
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto The Gran Hermano Store
 * Proyecto Final Grupo C
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona
 * 	@author	Laura María López Moreno
 * 	@author	Andrés Felipe Rey Pedraza
 * 	@author	Juan Camilo Díaz
 * 	@author	Camilo Andrés Romero Posada
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
 * Clase que gestiona el proceso de registro del vendedor o del cliente y el registro de los datos de la tarjeta de crédito.
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
	 * Cadena de texto acerca de la confirmación de la Contraseña del usuario y el numero de la tarjeta de crédito
	 * inicializado en "".
	 */
	private String confirmacionContraseña, numeroTarjeta = "";
	
	/**
	 * Cliente de tipo estatico.
	 */
	private static Cliente cli;
	
	/**
	 * Vendedor de tipo estatico.
	 */
	private static Vendedor vend;
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

	/**
	 * Guarda un nuevo cliente a partir de los datos ingresados por un formulario.
	 * Para guardar satisfacotriamente el cliente se verifica que la contraseña digitada
	 * sea igual a la confirmación de la contraseña. Así mismo, verifica la validez del 
	 * Recaptcha.
	 * @return la página de validación de la tarjeta de crédito en el caso exitoso de registro
	 * y la misma página de registro en caso contrario.
	 */
	public String guardarCliente() {
		String retorno;
		String men;
		if (cliente.getContraseña().equals(confirmacionContraseña)) {
			try {
				String gRecaptchaResponse = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
				 boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			        if(verify){
			        	cliente.setContraseña(Ultilidades.encriptador(cliente.getContraseña()));
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
			men = "La contraseña no es igual digite de nuevo ";
			retorno = "SigninCliente";
			confirmacionContraseña = "";
			cliente.setContraseña("");
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return retorno;
	}
	
	/**
	 * Guarda un nuevo vendedror a partir de los datos ingresados por un formulario.
	 * Para guardar satisfacotriamente el vendedor se verifica que la contraseña digitada
	 * sea igual a la confirmación de la contraseña. Así mismo, verifica la validez del 
	 * Recaptcha.
	 * @return la página de validación de la tarjeta de crédito en el caso exitoso de registro
	 * y la misma página de registro en caso contrario.
	 */
	public String guardarVendedor() {
		String retorno;
		String men;
		if (vendedor.getContraseña().equals(confirmacionContraseña)) {
			try {
				String gRecaptchaResponse = FacesContext.getCurrentInstance()
						.getExternalContext().getRequestParameterMap().get("g-recaptcha-response");
				 boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
			        if(verify){
			        	vendedor.setContraseña(Ultilidades.encriptador(vendedor.getContraseña()));
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
			men = "La contraseña no es igual digite de nuevo ";
			retorno = "SigninCliente";
			confirmacionContraseña = "";
			vendedor.setContraseña("");
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", men + cliente.getNombres());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		return retorno;
	}
	
	/**
	 * Registra y valida los datos de la tarjeta de crédito. Si es exitoso permite iniciar sesión.
	 * @return la página principal en el caso exitoso y la misma página de validación de la tarjeta
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
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro Finalizado! Inicia Sesión" + men);
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
	 * Retorna la confirmación de la contraseña del usuario.
	 * @return la confirmación de la contraseña del usuario. 
	 * confirmaciónContraseña!=null, confirmaciónContraseña!="". 
	 */
	public String getConfirmacionContraseña() {
		return confirmacionContraseña;
	}

	/**
	 * Setter de la cadena de texto referente a la confirmación de la contraseña
	 * del usurio.
	 * @param confirmacionContraseña
	 */
	public void setConfirmacionContraseña(String confirmacionContraseña) {
		this.confirmacionContraseña = confirmacionContraseña;
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
	 * Getter del número de la tarjeta de crédito.
	 * @return numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Setter del numero de la tarjeta de crédito.
	 * @param numeroTarjeta.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

}
