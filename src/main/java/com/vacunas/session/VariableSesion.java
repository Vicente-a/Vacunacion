
package com.vacunas.session;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.vacunas.entity.Usuario;
import com.vacunas.entity.Usuarioperfil;

import lombok.Getter;
import lombok.Setter;


@Named
@SessionScoped
public class VariableSesion implements Serializable {

	private static final long serialVersionUID = 1535967320590479694L;
	
	@Getter
	@Setter
	private Usuario usuario;
	@Getter
	@Setter
	private List<Usuarioperfil> perfiles;
}
//Inicializando variables en null
public void setInstanciaNull() {
		usuario = null;
		perfiles = null;
	}

