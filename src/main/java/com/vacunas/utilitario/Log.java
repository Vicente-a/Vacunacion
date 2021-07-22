package com.vacunas.utilitario;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private Log() {
    }

    public static void info(String clase, String mensaje) {
        Logger.getLogger(clase).log(Level.INFO, mensaje.toUpperCase());
    }

    public static void advertencia(String clase, String mensaje, Exception e) {
        Logger.getLogger(clase).log(Level.WARNING, mensaje.toUpperCase(), e);
    }

    public static void error(String clase, String mensaje, Exception e) {
        Logger.getLogger(clase).log(Level.SEVERE, mensaje.toUpperCase(), e);
    }
}