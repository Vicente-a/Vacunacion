
package com.vacunas.session;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.vacunas.entity.Usuario;
import com.vacunas.entity.Usuarioperfil;

import lombok.Getter;
import lombok.Setter;

/**
 * <b> Incluir aqui la descripcion de la clase. </b>
 *
 * @author David Murillo
 *         <p>
 *         [$Author: David Murillo $, $Date: 11/06/2018]
 *         </p>
 */
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
