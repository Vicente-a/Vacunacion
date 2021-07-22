/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vacunas.validaciones;

import java.math.BigDecimal;

/**
 *
 * @author dmurillo
 */
public class UsuarioValidation {
    
    public static boolean isIdentificacionValida(String identificacion){
        boolean validado = false;
        if (identificacion != null){
            if(identificacion.length() == 10){
                validado = isCedulaValida(identificacion);
                if(validado){
                    validado = isSecondValidation(identificacion);
                }
            }else if(identificacion.length() == 13){
            	validado = ValidarRuc.validaRucSociedades(identificacion);
                if (!validado) {
                	validado = ValidarRuc.validaRucEP(identificacion);
                    if (!validado) {
                    	validado = ValidarRuc.validaRucPN(identificacion);
                    }
                }
            }else{
                validado = false;
            }
        }else{
            validado = false;
        }
        return validado;
    }
    
    private static boolean isCedulaValida(String cedula) {
        
        boolean valorRetorno = true;
        try { 
            if (cedula != null && cedula.length() >= 10) {
                cedula = cedula.substring(0, 10);
                int[] modulo9 = new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2};
                BigDecimal verif = new BigDecimal(0);
                if (cedula.length() != 10) {
                    valorRetorno = false;
                } else {
                    for (int i = 0; i < 9; ++i) {
                        BigDecimal temp = new BigDecimal(new BigDecimal(cedula.substring(i, i + 1)).multiply(new BigDecimal(modulo9[i])).toString());
                        if (temp.doubleValue() > 9.0) {
                            temp = temp.subtract(new BigDecimal("9"));
                        }
                        verif = verif.add(temp);
                    }
                    valorRetorno = verif.doubleValue() % 10.0 == 0.0 ? Integer.parseInt(cedula.substring(9, 10)) == 0 : 10.0 - verif.doubleValue() % 10.0 == (double)Integer.parseInt(cedula.substring(9, 10));
                }
            }else{
                valorRetorno = false;
            }
            
        } catch (Exception e) {
            valorRetorno = false;
        }
        
        return valorRetorno;
    }
    private static boolean  isSecondValidation(String cedula) {
        boolean valorRetorno;
        int sum = 0;
        int sum_total = 0;
        int residuo = 0;
        int digito = 0;
        valorRetorno = false;
         if (cedula.length() == 10) {
            int nRegion = Integer.parseInt(cedula.substring(0,2));
            if ((nRegion >= 1 && nRegion <= 24) || nRegion == 30) {
                for (int n = 0; n < cedula.length() - 1; n++) {
                    if ((n + 1) % 2 != 0) {
                       sum = Integer.parseInt(cedula.substring(n, n + 1)) * 2;
                    }else{
                       sum = Integer.parseInt(cedula.substring(n, n + 1)) * 1;
                    }
                    if (sum > 9) {
                        sum = sum - 9;
                    }
                    sum_total = sum + sum_total;
                }
                residuo = sum_total % 10;
                if (residuo != 0) {
                     digito = 10 - residuo;
                }else{
                     digito = 0;
                }
                if (Integer.parseInt(cedula.substring(9, 10)) == digito) {
                     valorRetorno = true;
                }
            }
         }
        return valorRetorno;
    }
}
