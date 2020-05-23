package co.edu.unbosque.controller;

public class Ultilidades {
	
	public static boolean validarTarjeta(String input) {
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

}
