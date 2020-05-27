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
package co.edu.unbosque.resources;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL; 
import javax.json.Json; 
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

/**
 * Verifica la respuesta del recaptcha del registro.
 */
public class VerifyRecaptcha {
	
	/**
	 * Constante de la url de la api del recaptcha.
	 */
    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    
    /**
     * Constante secreta.
     */
    public static final String secret = "6Le3qPoUAAAAAFSTP49rGf89_tCo4AE3q8RHe710";
    
    /**
     * Constante del agente del usuario.
     */
	private final static String USER_AGENT = "Mozilla/5.0";
	
	
	/**
	 * Verifica la respuesta de la recaptcha.
	 * @param gRecaptchaResponse
	 * @return
	 * @throws IOException
	 */
	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		try{
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String postParams = "secret=" + secret + "&response="
				+ gRecaptchaResponse;
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(postParams);
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());
		//parse JSON response and return 'success' value
	JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject.getBoolean("success");
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}