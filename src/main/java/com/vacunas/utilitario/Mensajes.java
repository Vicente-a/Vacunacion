package com.vacunas.utilitario;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensajes {
	public static void mensajeInformacion(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg,null));
	}
	
	public static void mensajeError(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,null));
	}
	
	public static void mensajeAdvertencia(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg,null));
	}
}
