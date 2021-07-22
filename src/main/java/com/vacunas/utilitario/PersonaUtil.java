package com.vacunas.utilitario;

import java.io.Serializable;
import java.util.Arrays;

public class PersonaUtil implements Serializable {

	private static final long serialVersionUID = -1817018912662600270L;
	
	public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	public String getEmailPattern() {
		return EMAIL_PATTERN;
	}

	public static String[] normalizarNombreCompleto(String nombreCompleto) {
		String[] nombreCompletoSplit = nombreCompleto.split(" ");
		StringBuilder nombreCompletoValidado = new StringBuilder();
		String[] determinativos = { "DE", "DEL", "LA", "LAS", "LO", "LOS" };
		Arrays.stream(nombreCompletoSplit).forEachOrdered(s -> {
			boolean isDeterminativo = Arrays.stream(determinativos).anyMatch(det -> det.equalsIgnoreCase(s));
			if (isDeterminativo) {
				nombreCompletoValidado.append(s).append("*");
			} else {
				nombreCompletoValidado.append("$").append(s).append("$");
			}
		});

		nombreCompletoSplit = nombreCompletoValidado.toString().split("(\\$\\$)");

		for (int i = 0; i < nombreCompletoSplit.length; i++) {
			nombreCompletoSplit[i] = nombreCompletoSplit[i].replaceAll("(\\*\\$)", " ");
			nombreCompletoSplit[i] = nombreCompletoSplit[i].replaceAll("(\\*)", " ");
			nombreCompletoSplit[i] = nombreCompletoSplit[i].replaceAll("(\\$)", " ");
			nombreCompletoSplit[i] = nombreCompletoSplit[i].trim();
		}

		return nombreCompletoSplit;
	}
	
}
