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
import javax.swing.JOptionPane;

import com.sun.mail.util.MailConnectException;

import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Vendedor;


public class Ultilidades {

	private static String parseoTarjeta(String input) {
		String nuevo = "";
		for (int i = 0 ; i < input.length() ; i++) {
			if (input.charAt(i) != ' ') {
				nuevo += input.charAt(i);
			}
			
		}
		return nuevo;
	}
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

	public static String encriptador(String texto) {
		String textoPlano = texto;
		char cadenaTexto[] = textoPlano.toCharArray();
		for(int i=0;i<cadenaTexto.length;i++) {
			cadenaTexto[i] = (char)(cadenaTexto[i]+(char)5);
		}
		return String.valueOf(cadenaTexto);
	}

	public static String desencriptador(String texto) {
		String textoEncriptado = texto;
		char cadenaTexto[] = textoEncriptado.toCharArray();
		for(int i=0;i<cadenaTexto.length;i++) {
			cadenaTexto[i] = (char)(cadenaTexto[i]-(char)5);
		}
		return String.valueOf(cadenaTexto);
	}
	public void SendMailCliente(Cliente user) throws AddressException, MessagingException, MailConnectException
	{
		 // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente tambi�n.
	    String remitente = "thegranhermanocorp@gmail.com";  //Para la direcci�n nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Sofix1234");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO,user.getCorreo());   //Se podr�an a�adir varios de la misma manera
	        message.setSubject("Registo The Gran Hermano Store");
	        message.setText("felicitaciones! Has completado tu proceso de registro\nEs hora de que empieces a disfrutar de tu cuenta\nVerifica que estos datos sean correctos antes de iniciar\n"
           		+"Datos de tu cuenta: \n"
           		+ "Nombre: "+user.getNombres()+"\n"
           		+ "Apellido : "+user.getApellidos()+"\n"
           		+ "Nombre de usuario: "+user.getUsuario()+"\n"
           		+ "Contrase�a: "+desencriptador(user.getContrase�a())+"\n"
           		+ "Si este correo no es para ti por favor eliminalo");
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "Sofix1234");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
   }
	public void SendMailVendedor(Vendedor user) throws AddressException, MessagingException, MailConnectException
	{
		 // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente tambi�n.
	    String remitente = "thegranhermanocorp@gmail.com";  //Para la direcci�n nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Sofix1234");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticaci�n mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO,user.getCorreo());   //Se podr�an a�adir varios de la misma manera
	        message.setSubject("Registo The Gran Hermano Store");
	        message.setText("felicitaciones! Has completado tu proceso de registro\nEs hora de que empieces a disfrutar de tu cuenta\nVerifica que estos datos sean correctos antes de iniciar\n"
            		+"Datos de tu cuenta: \n"
            		+ "Nombre: "+user.getNombres()+"\n"
            		+ "Apellido : "+user.getApellidos()+"\n"
            		+ "Nombre de usuario: "+user.getUsuario()+"\n"
            		+ "Contrase�a: "+desencriptador(user.getContrase�a())+"\n"
            		+ "Si este correo no es para ti por favor eliminalo");
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "Sofix1234");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
    }

}
