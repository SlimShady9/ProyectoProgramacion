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

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailConnectException;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Vendedor;

/**
 * Clase que representa las utilidades para validat la tarjeta de crédto,
 * proceso de encriptación y desencriptación, envio de e-mail de registro. *
 */
public class Ultilidades {

	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Guarda en una cadena de carácteres el número de la tarjeta sin espacios.
	 * @param input
	 * @return nuevo
	 */
	private static String parseoTarjeta(String input) {
		String nuevo = "";
		for (int i = 0 ; i < input.length() ; i++) {
			if (input.charAt(i) != ' ') {
				nuevo += input.charAt(i);
			}
			
		}
		return nuevo;
	}
	
	/**
	 * Valida el número de la tarjeta de crédito.
	 * @param input
	 * @return true si es válida, false de lo contrario
	 */
	public static boolean validarTarjeta(String input) {
		input = parseoTarjeta(input);
		int[] tarjeta=new int[input.length()];  
		for (int i = 0; i < input.length(); i++) {
			tarjeta [i] = Integer.parseInt(input.substring(i,i+1));
		}
		for (int i = tarjeta.length-2; i >=0; i=i-2) {
			int temporal= tarjeta[i];
			temporal = temporal*2;
			if (temporal>9) {
				temporal = temporal % 10 + 1;	
			}
			tarjeta[i]= temporal;
		}
		int total = 0;
		for (int i = 0; i < tarjeta.length; i++) {	
			total += tarjeta[i];
		} 	
		if (total % 10 == 0) {
			return true;
		}
		else {
			return false;
		} 
	}

	/**
	 * Encripta un texto.
	 * @param texto
	 * @return texto encriptado
	 */
	public static String encriptador(String texto) {
		String textoPlano = texto;
		char cadenaTexto[] = textoPlano.toCharArray();
		for(int i=0;i<cadenaTexto.length;i++) {
			cadenaTexto[i] = (char)(cadenaTexto[i]+(char)5);
		}
		return String.valueOf(cadenaTexto);
	}

	/**
	 * Desencripta un texto.
	 * @param texto
	 * @return texto desencriptado
	 */
	public static String desencriptador(String texto) {
		String textoEncriptado = texto;
		char cadenaTexto[] = textoEncriptado.toCharArray();
		for(int i=0;i<cadenaTexto.length;i++) {
			cadenaTexto[i] = (char)(cadenaTexto[i]-(char)5);
		}
		return String.valueOf(cadenaTexto);
	}
	
	/**
	 * Envia un correro electrónico al cliente por su registro exitoso.
	 * @param user
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws MailConnectException
	 */
	public void SendMailCliente(Cliente user) throws AddressException, MessagingException, MailConnectException
	{

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("thegranhermanocorp@gmail.com","Sofix1234");
                    }
                });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thegranhermanocorp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getCorreo()));
            message.setSubject("registo bostinder");
            message.setText("felicitaciones! Has completado tu proceso de registro\nEs hora de que empieces a disfrutar de tu cuenta\nVerifica que estos datos sean correctos antes de iniciar\n"
            		+"Datos de tu cuenta: \n"
            		+ "Nombre: "+user.getNombres()+"\n"
            		+ "Apellido : "+user.getApellidos()+"\n"
            		+ "Nombre de usuario: "+user.getUsuario()+"\n"
            		+ "Contraseña: "+desencriptador(user.getContraseña())+"\n"
            		+ "Si este correo no es para ti por favor eliminalo");
            Transport.send(message);
    }
	
	/**
	 * Envia un correo electrónico al vendedor por su registro exitoso.
	 * @param user
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws MailConnectException
	 */
	public void SendMailVendedor(Vendedor user) throws AddressException, MessagingException, MailConnectException
	{

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("thegranhermanocorp@gmail.com","Sofix1234");
                    }
                });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("thegranhermanocorp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getCorreo()));
            message.setSubject("Registo The Gran Hermano Stor");
            message.setText("felicitaciones! Has completado tu proceso de registro\nEs hora de que empieces a disfrutar de tu cuenta\nVerifica que estos datos sean correctos antes de iniciar\n"
            		+"Datos de tu cuenta: \n"
            		+ "Nombre: "+user.getNombres()+"\n"
            		+ "Apellido : "+user.getApellidos()+"\n"
            		+ "Nombre de usuario: "+user.getUsuario()+"\n"
            		+ "Contraseña: "+desencriptador(user.getContraseña())+"\n"
            		+ "Si este correo no es para ti por favor eliminalo");
            Transport.send(message);
    }

}
