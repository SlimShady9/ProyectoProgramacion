/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad El Bosque (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Programación II.
 * 
 * Profesor: Miguel Alejandro Feijoo García.
 * 
 * Licenciado bajo el esquema Academic Free License version 2.1 .
 *
 * Proyecto The Gran Hermano Store.
 * Proyecto Final Grupo C.
 * Autor: Equipo de ElectroCompras Corp:
 * 	@author	Juan David Alberto Quintero Gaona.
 * 	@author	Laura María López Moreno.
 * 	@author	Andrés Felipe Rey Pedraza.
 * 	@author	Juan Camilo Díaz.
 * 	@author	Camilo Andrés Romero Posada.
 * 			
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package co.edu.unbosque.controller;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.sun.mail.util.MailConnectException;

import co.edu.unbosque.model.Administrador;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.Gerencia;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.Sede;
import co.edu.unbosque.model.Vendedor;
import co.edu.unbosque.model.Ventas;
import java.util.Calendar;

/**
 * Clase que representa las utilidades para validat la tarjeta de crédto,
 * proceso de encriptación y desencriptación, envio de e-mail de registro. *
 */
public class Ultilidades {
	
	// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
	
	/**
	 * Generea una grafica de barras de vendedores.
	 * @param adminGuapo.
	 * @return model.
	 */
	public static BarChartModel GrafiquitasVendedores(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Ventas realizadas por Vendedor");
		ventas.setLabel("Ventas");
		
		ArrayList<Vendedor> vends = vendedorTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < vends.size(); i++) {
			if (i == 5) {
				break;
			}
			ventas.set(vends.get(i).getUsuario(), vends.get(i).getVentas().size());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	/**
	 * Generea una grafica de barras de clientes.
	 * @param adminGuapo.
	 * @return model.
	 */
	
	public static BarChartModel GrafiquitasTopClientes(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries compras = new ChartSeries();			
		model.setTitle("Top Compras realizadas por Cliente");
		compras.setLabel("Compras");
		
		ArrayList<Cliente> clientes = clientesTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < clientes.size(); i++) {
			if (i == 5) {
				break;
			}
			compras.set(clientes.get(i).getUsuario(), clientes.get(i).getCompras().size());
		}
		
		model.addSeries(compras);

		return model;
		
	}
	/**
	 * Generea una grafica de barras de productos.
	 * @param adminGuapo.
	 * @return model.
	 */
	public static BarChartModel GrafiquitasTopProductos(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Productos por Sede");
		ventas.setLabel("Productos");
		
		ArrayList<Ventas> productos = ordenarTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < productos.size(); i++) {
			if (i == 5) {
				break;
			}
			ventas.set(productos.get(i).getArticulo(), productos.get(i).getUnidades());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	
	/**
	 * Generea una grafica de barras de vendedores.
	 * @return model.
	 */
	
	public static BarChartModel GrafiquitasVendedoresGerencia() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Ventas realizadas por Vendedor");
		ventas.setLabel("Ventas");
		
		ArrayList<Vendedor> vends = vendedorTopGeneral();
		
		for (int i = 0; i < vends.size(); i++) {
			if (i == 5) {
				break;
			}
			ventas.set(vends.get(i).getUsuario(), vends.get(i).getVentas().size());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	/**
	 * Generea una grafica de barras de clientes.
	 * @return model.
	 */
	public static BarChartModel GrafiquitasTopClientes() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries compras = new ChartSeries();			
		model.setTitle("Top Clientes");
		compras.setLabel("Compras");
		
		ArrayList<Cliente> clientes = clientesTopGenera();
		
		for (int i = 0; i < clientes.size(); i++) {
			if (i == 5) {
				break;
			}
			compras.set(clientes.get(i).getUsuario(), clientes.get(i).getCompras().size());
		}
		
		model.addSeries(compras);

		return model;
		
	}
	/**
	 * Generea una grafica de barras de productos.
	 * @return model.
	 */
	public static BarChartModel GrafiquitasTopProductosGerente() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Productos");
		ventas.setLabel("Productos");
		
		ArrayList<Ventas> productos = ordenarTopGeneral();
		
		for (int i = 0; i < productos.size(); i++) {
			if (i == 5) {
				break;
			}
			ventas.set(productos.get(i).getArticulo(), productos.get(i).getUnidades());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	/**
	 * Generea una grafica de barras de sedes.
	 * @return model.
	 */
	public static BarChartModel GrafiquitasTopSedes() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Sedes");
		ventas.setLabel("Sedes");
		
		ArrayList<Sede> productos = topSedeVentas();
		
		for (int i = 0; i < productos.size(); i++) {
			if (i == 5) {
				break;
			}
			ventas.set(productos.get(i).getNombreSede(), productos.get(i).getVentas());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	

	/**
	 * Guarda en una cadena de carácteres el número de la tarjeta sin espacios.
	 * @param input.
	 * @return nuevo.
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
	public static Date fechaActual() {
		Date fecha = new Date(Calendar.getInstance().getTime().getTime());
		return fecha;
	}
	/**
	 * Valida el número de la tarjeta de crédito.
	 * @param input.
	 * @return true si es válida, false de lo contrario.
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
	 * @param texto.
	 * @return hashtext 
	 */
	public static String encriptador(String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(texto.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	
	}
	/**
	 * Envia un correro electrónico al cliente por su registro exitoso.
	 * @param user.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
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
				+ "Contraseña: " + user.getContraseña()+ "\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Envia un correo electrónico al vendedor por su registro exitoso.
	 * @param user.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
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
				+ "Contraseña: "+ user.getContraseña()+ "\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Genera un arreglo de 3 dimensiones para 4 colimnas para una cantdad indeterminada de productos.
	 * @param sede.
	 * @return matriz.
	 */
	public static List<ArrayList<Producto>> generarMatrizProducto(String sede){
		ArrayList<Producto> products;
		if (sede != null) {
			products = ordenarTopProductos(ordenarTopSede(sede));
		}
		else {
			products = (ArrayList<Producto>) Dao.productos;
		}
		ArrayList<ArrayList<Producto>> matriz = new ArrayList<ArrayList<Producto>>();
		ArrayList<Producto> fila = new ArrayList<Producto>();
		for (int i = 0 ; i < products.size() && i < 25; i++) {
			fila.add(products.get(i));
			// cada 4 columnas agrego una fila
			if ( (i+1) % 4 == 0) {
				ArrayList<Producto> loQueMetere = fila;
				matriz.add(loQueMetere);
				// limpio el array
				fila = new ArrayList<Producto>();
			}
		}
		if (products.size() % 4 != 0) {
			matriz.add(fila);
		}
		return matriz;
	}
	/**
	 * Ordena las ventas de la sede de mayor a menor y lo retorna en un ArrayList.
	 * @param sede.
	 * @return vent.
	 */
	public static ArrayList<Ventas> ordenarTopSede(String sede) {
		ArrayList<Vendedor> ven = Presistence.busquedaVendedores(sede);
		ArrayList<Ventas>  vent=Presistence.busquedaVentas(ven);
		for (int i = 1; i <vent.size(); i++) {
			Ventas aux = vent.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getUnidades() > vent.get(j).getUnidades())){
				vent.set(j+1, vent.get(j));
				j--;
			}
			vent.set(j+1, aux);
		}
		return vent;
	}
	/**
	 * Ordena las ventas de mayor a menor y lo retorna en un ArrayList.
	 * @return vent.
	 */
	public static ArrayList<Ventas> ordenarTopGeneral() {
		ArrayList<Vendedor> ven = new ArrayList<Vendedor>(Dao.vendedores);
		ArrayList<Ventas>  vent=Presistence.busquedaVentas(ven);
		for (int i = 1; i <vent.size(); i++) {
			Ventas aux = vent.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getUnidades() > vent.get(j).getUnidades())){
				vent.set(j+1, vent.get(j));
				j--;
			}
			vent.set(j+1, aux);
		}
		return vent;
	}
	/**
	 * Ordena los productos en cuanto a ventas de la sede de mayor a menor y lo retorna en un ArrayList.
	 * @param vent.
	 * @return resultado.
	 */
	public static ArrayList<Producto> ordenarTopProductos(ArrayList<Ventas> vent){
		ArrayList<Producto> todos= new ArrayList<Producto>(Dao.productos);
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		if(vent.size()!=0){
		for(int i =0; i< vent.size();i++) {
			for(int j=0; j<todos.size();j++) {
				if(vent.get(i).getArticulo()==todos.get(j).getNombre()&&vent.get(i).getVendedor()==todos.get(j).getVendedor()) {
					if(todos.get(j).getCantidad()!=0) {
						if(vent.get(i).getFecha()==fechaRevizar()) {
						resultado.add(todos.get(j));
						todos.remove(j);
						break;
						}
						else if(vent.get(i).getFecha()==fechaRevizar2()) {
							resultado.add(todos.get(j));
							todos.remove(j);
							break;
						}else if(vent.get(i).getFecha()==fechaRevizar3()) {
							resultado.add(todos.get(j));
							todos.remove(j);
							break;
						}
					}
				}
			}
			if(resultado.size()==5) {
				break;
			}
		}
		}
		else if(vent.size()==0) {
			resultado = todos;
		}
		return resultado;
	}
	/**
	 * Ordena los clientes de la sede de mayor a menor en cuanto a compras y lo retorna en un ArrayList.
	 * @param sede.
	 * @return cliente.
	 */
	public static ArrayList<Cliente> clientesTopSede(String sede){
		ArrayList<Cliente> cliente = Presistence.busquedaClientes(sede);
		for (int i = 1; i <cliente.size(); i++) { //i los clientes de la sede
			Cliente aux = cliente.get(i); // aux es el cliente en i 
			int j = i-1; //j inicia en 0
			while((j >= 0) && (aux.getCompras().size() > cliente.get(j).getCompras().size())){
				cliente.set(j+1, cliente.get(j));
				j--;
			}
			cliente.set(j+1, aux);
		}
		return cliente;
	}
	/**
	 * Ordena los clientes de mayor a menor en cuanto a compras y lo retorna en un ArrayList.
	 * @return resultado.
	 */
	public static ArrayList<Cliente> clientesTopGenera(){
		ArrayList<Cliente> cliente = new ArrayList<Cliente>(Dao.clientes);
		for (int i = 1; i <cliente.size(); i++) {
			Cliente aux = cliente.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getCompras().size() > cliente.get(j).getCompras().size())){
				cliente.set(j+1, cliente.get(j));
				j--;
			}
			cliente.set(j+1, aux);
		}
		return cliente;
	}
	/**
	 * Ordena los vendedores de la sede de mayor a menor en cuanto a ventas y lo retorna en un ArrayList.
	 * @param sede.
	 * @return ven.
	 */
	public static ArrayList<Vendedor> vendedorTopSede(String sede){
		ArrayList<Vendedor> ven = Presistence.busquedaVendedores(sede);
		for (int i = 1; i <ven.size(); i++) {
			Vendedor aux = ven.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getVentas().size() > ven.get(j).getVentas().size())){
				ven.set(j+1, ven.get(j));
				j--;
			}
			ven.set(j+1, aux);
		}
		return ven;
	}
	/**
	 * Ordena los vendedores de mayor a menor en cuanto a ventasy lo retorna en un ArrayList.
	 * @return resultado.
	 */
	public static ArrayList<Vendedor> vendedorTopGeneral(){
		ArrayList<Vendedor> ven = new ArrayList<Vendedor>(Dao.vendedores);
		for (int i = 1; i <ven.size(); i++) {
			Vendedor aux = ven.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getVentas().size() > ven.get(j).getVentas().size())){
				ven.set(j+1, ven.get(j));
				j--;
			}
			ven.set(j+1, aux);
		}
		return ven;
	}
	/**
	 * Ordena las sedes de mayor a menor en cuanto a ventas y lo retorna en un ArrayList.
	 * @return ventas.
	 */
	public static ArrayList<Sede> topSedeVentas() {
		int ventasBogota=0;
		int ventasMedellin=0;
		int ventasBarranquilla=0;
		int ventasBucaramanga=0;
		ArrayList<Sede> sedes= new ArrayList<Sede>();
		Sede bogota = new Sede();
		bogota.setClientes(Presistence.busquedaClientes("Bogotá"));
		bogota.setVendedores(Presistence.busquedaVendedores("Bogotá"));
		bogota.setAdmin(Presistence.busquedaAdministradores("Bogotá"));
		bogota.setVentas(ventasDeSede(Presistence.busquedaClientes("Bogotá")));
		bogota.setNombreSede("Bogotá");
		Sede medellin = new Sede();
		medellin.setClientes(Presistence.busquedaClientes("Medellín"));
		medellin.setVendedores(Presistence.busquedaVendedores("Medellín"));
		medellin.setAdmin(Presistence.busquedaAdministradores("Medellín"));
		medellin.setVentas(ventasDeSede(Presistence.busquedaClientes("Medellín"))); 
		//
		Sede barranquilla = new Sede();
		barranquilla.setClientes(Presistence.busquedaClientes("Barranquilla"));
		barranquilla.setVendedores(Presistence.busquedaVendedores("Barranquilla"));
		barranquilla.setAdmin(Presistence.busquedaAdministradores("Barranquilla"));
		barranquilla.setVentas(ventasDeSede(Presistence.busquedaClientes("Barranquilla")));
		Sede bucaramanga = new Sede();
		bucaramanga.setClientes(Presistence.busquedaClientes("Bucaramanga"));
		bucaramanga.setVendedores(Presistence.busquedaVendedores("Bucaramanga"));
		bucaramanga.setAdmin(Presistence.busquedaAdministradores("Bucaramanga"));
		bucaramanga.setVentas(ventasDeSede(Presistence.busquedaClientes("Bucaramanga")));
		sedes.add(medellin);
		sedes.add(bucaramanga);
		sedes.add(barranquilla);
		sedes.add(bogota);
		for (int i = 1; i <sedes.size(); i++) {
			Sede aux = sedes.get(i);
			int j = i-1;
			while((j >= 0) && (aux.getVentas() > sedes.get(i).getVentas())){
				sedes.set(j+1, sedes.get(j));
				j--;
			}
			sedes.set(j+1, aux);
		}
		return sedes;
	}
	/**
	 * Este metodo calcula la cantidad de compras de un cliente.
	 * @param user.
	 * @return ventas.
	 */
	public static int ventasDeSede(ArrayList<Cliente> user) {
		int ventas=0;
		for(int i =0; i<user.size();i++) {
			ventas+=user.get(i).getCompras().size();
		}
		if (ventas == 0) {
			return ventas +1;
		}
		return ventas;
	}
	/**
	 * Envia un correro electrónico al cliente por su compra exitoso.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailComprar(Cliente user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Compra Realizada The Gran Hermano Stor");
		message.setText("felicitaciones! Has completado tu proceso de compra \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de tu compra: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Envia un correro electrónico al cliente por su reserva exitoso.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailReservar(Cliente user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Compra Realizada The Gran Hermano Stor");
		message.setText("felicitaciones! Has completado tu proceso de reserva \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de tu compra: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Si no realizas el pago de este en 3 días se cancelara la reserva automaticamente\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Envia un correro electrónico al vendedor por su venta exitoso.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailVentas(Vendedor user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Venta Realizada The Gran Hermano Stor");
		message.setText("felicitaciones! Se ha realizado la compra de uno de tus productos \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de tu Venta: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Envia un correro electrónico al cliente por la reserva de uno de sus productos.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailReserva(Vendedor user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Venta Realizada The Gran Hermano Stor");
		message.setText("felicitaciones! Se ha realizado la reserva de uno de tus productos \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de tu Venta: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Se ha realizado la reserva de uno de tus productos, si no se realiza el pago en 3 días este se te devolvera automaticamente \n "
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}

	/**
	 * Genera una contraseña aleatoria para asignarla a los usuarios que se registran .
	 * @return password.
	 */
	public static String generarContraseña() {
		String[] charCategories = new String[] {
				"abcdefghijklmnopqrstuvwxyz",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ",
				"0123456789"
		};
		StringBuilder password = new StringBuilder(10);
		Random random = new Random(System.nanoTime());

		for (int i = 0; i < 10; i++) {
			String charCategory = charCategories[random.nextInt(charCategories.length)];
			int position = random.nextInt(charCategory.length());
			password.append(charCategory.charAt(position));
		}

		return new String(password);
	}

	/**
	 * Verifica las reservas que hay en el sistema para cancelar 
	 * las que superen los 3 días sin terminar la compra y les envia un correo al cliente y el vendedor .
	 */
	public static void verificarReservas() {
		ArrayList<Ventas> vend= new ArrayList<Ventas>(Dao.ventas);
		for(int i=0; i<vend.size();i++) {
			Date fecha1= vend.get(i).getFecha();
			Date fecha2= fechaRevizar();
			if(fecha1==fecha2) {
				Vendedor ven= vend.get(i).getVendedor();
				List<Producto> pro = ven.getProductos();
				for(int j=0;j<pro.size();j++) {
					if(vend.get(i).isReserva()) {
						if(vend.get(i).getArticulo().equals(pro.get(j).getNombre())) {
							pro.get(j).setCantidad(vend.get(i).getUnidades());
							ven.setProductos(pro);
							Presistence.actualizarVendedor(ven);
							Cliente x= vend.get(i).getComprador();
							Vendedor v = vend.get(i).getVendedor();
							int u = vend.get(i).getUnidades();
							Presistence.eliminarVenta(vend.get(i));
							vend.remove(i);
							try {
								SendMailReservaCancelada(x, pro.get(j), u);
								SendMailVentasReservaCancelada(v, pro.get(i), u);
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
							break;
						}
					}
				}
			}
		}
	}
	/**
	 * Toma la fecha acual y le resta 3 días.
	 * @return fecha.
	 */

	public static Date fechaRevizar() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -3);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
	/**
	 * Toma la fecha acual y le resta 2 días.
	 * @return fecha.
	 */
	public static Date fechaRevizar2() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -2);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
	/**
	 * Toma la fecha acual y le resta 1 día.
	 * @return fecha.
	 */
	public static Date fechaRevizar3() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -1);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
	/**
	 * Envia un correro electrónico al cliente por la cancelación de una de las reservas de un producto.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailReservaCancelada(Cliente user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Reserva Cancelada The Gran Hermano Stor");
		message.setText(" Se ha cancelado tu proceso de reserva \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de tu Reserva: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	/**
	 * Envia un correro electrónico al vendedor por la cancelación de la reserva de uno de sus productos.
	 * @param user.
	 * @param prod.
	 * @param Ncompras.
	 * @throws AddressException.
	 * @throws MessagingException.
	 * @throws MailConnectException.
	 */
	public static void SendMailVentasReservaCancelada(Vendedor user, Producto prod,int Ncompras) throws AddressException, MessagingException, MailConnectException
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
		message.setSubject("Reserva Realizada The Gran Hermano Stor");
		message.setText(" Se ha cancelado la reserva de uno de tus productos \nVerifica que estos datos sean correctos antes de iniciar\n"
				+"Datos de la Reserva: \n"
				+ "Producto: "+prod.getNombre()+"\n"
				+ "Cantidad : "+Ncompras+"\n"
				+ "Precio por unidad: "+prod.getPrecio()+"\n"
				+ "Precio total: "+(prod.getPrecio()*Ncompras)+"\n"
				+ "Si este correo no es para ti por favor eliminalo");
		Transport.send(message);
	}
	

}