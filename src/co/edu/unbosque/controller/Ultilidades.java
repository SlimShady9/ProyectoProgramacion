package co.edu.unbosque.controller;

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

//

public class Ultilidades {
	
	public static BarChartModel GrafiquitasVendedores(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Ventas realizadas por Vendedor");
		ventas.setLabel("Ventas");
		
		ArrayList<Vendedor> vends = vendedorTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < vends.size(); i++) {
			ventas.set(vends.get(i).getUsuario(), vends.get(i).getVentas().size());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	
	public static BarChartModel GrafiquitasTopClientes(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries compras = new ChartSeries();			
		model.setTitle("Top Compras realizadas por Cliente");
		compras.setLabel("Compras");
		
		ArrayList<Cliente> clientes = clientesTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getCompras().size());
			compras.set(clientes.get(i).getUsuario(), clientes.get(i).getCompras().size());
		}
		
		model.addSeries(compras);

		return model;
		
	}
	
	public static BarChartModel GrafiquitasTopProductos(Administrador adminGuapo) 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Productos por Sede");
		ventas.setLabel("Productos");
		
		ArrayList<Ventas> productos = ordenarTopSede(adminGuapo.getSede());
		
		for (int i = 0; i < productos.size(); i++) {
			ventas.set(productos.get(i).getArticulo(), productos.get(i).getUnidades());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	
	public static BarChartModel GrafiquitasVendedoresGerencia() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Ventas realizadas por Vendedor");
		ventas.setLabel("Ventas");
		
		ArrayList<Vendedor> vends = vendedorTopGeneral();
		
		for (int i = 0; i < vends.size(); i++) {
			ventas.set(vends.get(i).getUsuario(), vends.get(i).getVentas().size());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	
	public static BarChartModel GrafiquitasTopClientes() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries compras = new ChartSeries();			
		model.setTitle("Top Clientes");
		compras.setLabel("Compras");
		
		ArrayList<Cliente> clientes = clientesTopGenera();
		
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println(clientes.get(i).getCompras().size());
			compras.set(clientes.get(i).getUsuario(), clientes.get(i).getCompras().size());
		}
		
		model.addSeries(compras);

		return model;
		
	}
	
	public static BarChartModel GrafiquitasTopProductosGerente() 
	{
		BarChartModel model = new BarChartModel();
		ChartSeries ventas = new ChartSeries();			
		model.setTitle("Top Productos");
		ventas.setLabel("Productos");
		
		ArrayList<Ventas> productos = ordenarTopGeneral();
		
		for (int i = 0; i < productos.size(); i++) {
			ventas.set(productos.get(i).getArticulo(), productos.get(i).getUnidades());
		}
		
		model.addSeries(ventas);

		return model;
		
	}
	

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

	public static List<ArrayList<Producto>> generarMatrizProducto(String sede){
		ArrayList<Producto> products = ordenarTopProductos(ordenarTopSede(sede));
		ArrayList<ArrayList<Producto>> matriz = new ArrayList<ArrayList<Producto>>();
		ArrayList<Producto> fila = new ArrayList<Producto>();
		for (int i = 0 ; i < products.size() ; i++) {
			fila.add(Dao.productos.get(i));
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
	//Este metodo retorna un arraylist de las ventas en una sede ordenadas por numero de unidaces vendidas (psdta: sofi xd we)
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
	//Este metodo retorna un arraylist de las ventas en general de todas las sedes ordenadas por numero de unidaces vendidas (psdta: sofi xd we)
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

	//Este metodo devuelve un arraylist de cliente por sede ordenados por numero de compras realizadas
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
	//Este metodo devuelve un arraylist de cliente en la cual me dice los clientes a nivel genereal ordenado por numero de compras realizadas
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
	//Este metodo devuelve un arraylist de vendedores por sede ordenados por numero de ventas realizadas
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
	//Este metodo devuelve un arraylist de vendedores en la cual me dice los vededores a nivel genereal ordenado por numero de ventas realizadas
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
	//Este metodo devuelve un arraylist de sedes en la cual saldran en orden por ventas realizadas en la sede(Para las estadisticas)
	public ArrayList<Sede> topSedeVentas() {
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
	public int ventasDeSede(ArrayList<Cliente> user) {
		int ventas=0;
		for(int i =0; i<user.size();i++) {
			ventas+=user.get(i).getCompras().size();
		}
		return ventas;
	}
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

	// 
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

	// este metodo cancela las reservas despues de 3 días :ccc
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

	public static Date fechaRevizar() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -3);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
	public static Date fechaRevizar2() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -2);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
	public static Date fechaRevizar3() {	
		Calendar fechad=Calendar.getInstance();
		fechad.add(Calendar.DAY_OF_YEAR, -1);
		Date fecha = new Date(fechad.getTime().getTime());
		return fecha;
	}
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
