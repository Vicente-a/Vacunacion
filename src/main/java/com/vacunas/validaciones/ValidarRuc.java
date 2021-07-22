package com.vacunas.validaciones;

import java.math.BigDecimal;

public class ValidarRuc {
	private static final int NUM_PROVINCIAS = 24;
	
	public static Boolean validaRucSociedades(String numero){
		int[] coeficientes = { 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		int constante = 11;
		boolean resp_dato = false;
		final int prov = Integer.parseInt(numero.substring(0, 2));
		if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
			resp_dato = false;
		}
 
		int[] d = new int[10];
		int suma = 0;
 
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(numero.charAt(i) + "");
		}
 
		for (int i = 0; i < d.length - 1; i++) {
			d[i] = d[i] * coeficientes[i];
			suma += d[i];
		}
 
		int aux, resp;
 
		aux = suma % constante;
		resp = constante - aux;
 
		resp = (aux == 0) ? 0 : resp;
 
		if (resp == d[9]) {
			resp_dato = true;
		} else {
			resp_dato = false;
		}
		return resp_dato;
	}
	public static Boolean validaRucEP(String numero){
		final int prov = Integer.parseInt(numero.substring(0, 2));
		boolean resp = false;
 
		if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
			resp = false;
		}
 
		// boolean val = false;
		Integer v1, v2, v3, v4, v5, v6, v7, v8, v9;
		Integer sumatoria;
		Integer modulo;
		Integer digito;
		int[] d = new int[numero.length()];
 
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.parseInt(numero.charAt(i) + "");
		}
 
		v1 = d[0] * 3;
		v2 = d[1] * 2;
		v3 = d[2] * 7;
		v4 = d[3] * 6;
		v5 = d[4] * 5;
		v6 = d[5] * 4;
		v7 = d[6] * 3;
		v8 = d[7] * 2;
		v9 = d[8];
 
		sumatoria = v1 + v2 + v3 + v4 + v5 + v6 + v7 + v8;
		modulo = sumatoria % 11;
		if (modulo == 0) {
			return true;
		}
		digito = 11 - modulo;
 
		if (digito.equals(v9)) {
			resp = true;
		} else {
			resp = false;
		}
		return resp;
	}
	public static Boolean validaRucPN(String numero){
		if (numero != null && numero.length() > 10) {
			numero = numero.substring(0, 10);
        }
        int[] modulo9 = new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2};
        boolean valorRetorno = true;
        BigDecimal verif = new BigDecimal(0);
        if (numero.length() != 10) {
            valorRetorno = false;
        } else {
            for (int i = 0; i < 9; ++i) {
                BigDecimal temp = new BigDecimal(new BigDecimal(numero.substring(i, i + 1)).multiply(new BigDecimal(modulo9[i])).toString());
                if (temp.doubleValue() > 9.0) {
                    temp = temp.subtract(new BigDecimal("9"));
                }
                verif = verif.add(temp);
            }
            valorRetorno = verif.doubleValue() % 10.0 == 0.0 ? Integer.parseInt(numero.substring(9, 10)) == 0 : 10.0 - verif.doubleValue() % 10.0 == (double)Integer.parseInt(numero.substring(9, 10));
        }
        return valorRetorno;
	}
}
