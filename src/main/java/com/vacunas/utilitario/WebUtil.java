package com.vacunas.utilitario;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.context.FacesContext;

public class WebUtil {
	
	//https://passwordsgenerator.net/sha512-hash-generator/

	public static void redirect(final String url) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(url);
	}
	
	public static String encrypt512(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
