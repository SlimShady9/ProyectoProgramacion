/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Recurso extraido de internet
 * @author  jinu jawad m

 * Link : https://www.youtube.com/watch?v=-R_dgdeXURo
 * 
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
 *  Esta clase es la encargada de conecta el api de verificacion de google con jsf
 */
public class VerifyRecaptcha {

	/**
	 *  String que se refiere al url del api de google
	 */
	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	/**
	 *  String clave secreta de nuestro verificador
	 */
	public static final String secret = "6Le3qPoUAAAAAFSTP49rGf89_tCo4AE3q8RHe710";
	/**
	 *  String que se refiere agente contededor del api
	 */
	private final static String USER_AGENT = "Mozilla/5.0";

	/**
	 *  Este metodo verifica si la respuesta del html es "humana"
	 *  @param respuesta por parte del html representativo de JSF para evaluear los valores del que envio la respuesta
	 *  @return valor booleano verificando el contendio JSON de la respuesta del api
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