package com.vacunas.utilitario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptarImpl implements Encriptar {
	

	@Override
	public String encriptarSHA512(String palabra) {
		StringBuilder h = null;
	       try {
	           if (palabra != null) {
	               if (!palabra.equals("")) {
	                   MessageDigest md = MessageDigest.getInstance("SHA-512");
	                   byte[] b = md.digest(palabra.getBytes());
	                   int size = b.length;
	                   h = new StringBuilder(size);
	                   for (int i = 0; i < size; i++) {
	                       int u = b[i] & 255; // unsigned conversion
	                       if (u < 16) {
	                           h.append("0");
	                           h.append(Integer.toHexString(u));
	                       } else {
	                           h.append(Integer.toHexString(u));
	                       }
	                   }
	                   return h.toString();
	               }
	           }
	       } catch (NoSuchAlgorithmException e) {
	    	   Log.info("encriptarSHA512", "Error "+e.getMessage());
	       }
	       return null;
	}

}
