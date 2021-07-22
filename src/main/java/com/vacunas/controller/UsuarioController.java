package com.vacunas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.vacunas.dao.PerfilDAO;
import com.vacunas.dao.UsuarioDAO;
import com.vacunas.dao.UsuarioperfilDAO;
import com.vacunas.entity.Perfil;
import com.vacunas.entity.Usuario;
import com.vacunas.entity.Usuarioperfil;
import com.vacunas.utilitario.FechasUtil;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.WebUtil;
import com.vacunas.validaciones.UsuarioValidation;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class UsuarioController implements Bean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573890359385239181L;
	@Getter
	@Setter
	private Usuario usuario;
	@EJB(beanName="UsuarioDAOImpl")
	private UsuarioDAO usuarioDAO;
	@Getter
	@Setter
	private List<Perfil> perfiles;
	@EJB(beanName="PerfilDAOImpl")
	private PerfilDAO perfilDAO;
	@EJB(beanName="UsuarioperfilDAOImpl")
	private UsuarioperfilDAO usuarioperfilDAO;
	@Getter
	@Setter
	private Usuarioperfil usuarioperfil;
	@Getter
	@Setter
	private Integer codigoPerfil;
	@Getter
	@Setter
	private List<Usuario> usuarios;
	@Getter
	@Setter
	private List<Usuarioperfil> usuarioPerfiles;

	@Override
	@PostConstruct
	public void init() {
		cancel();

	}

	@Override
	public void save() {
		if(validate()) {
			usuario.setEstado(1);
			String clave = WebUtil.encrypt512(usuario.getPassword()).toUpperCase();
			usuario.setPassword(clave);
			usuario.setFechaingreso(FechasUtil.getFechaTimestamp());
			usuario = usuarioDAO.create(usuario);
			if(usuario != null && usuario.getId() > 0) {
				usuarioperfil = new Usuarioperfil();
				usuarioperfil.setUsuario(usuario);
				usuarioperfil.setPerfil(perfilDAO.findById(codigoPerfil));
				usuarioperfilDAO.create(usuarioperfil);
				cancel();
			}
		}
	}

	@Override
	public void delete() {
		try {
			usuario = usuarioperfil.getUsuario();
			usuarioperfilDAO.delete(usuarioperfil);
			usuario.setEstado(0);
			usuarioDAO.update(usuario);
			cancel();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Boolean validate() {
		Boolean valor = true;
		if(usuario.getIdentificacion() == null || usuario.getIdentificacion().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("EL NUMERO DE IDENTIFICACION NO PUEDE SER NULO");
		}
		
		Usuario usrovalida = usuarioDAO.findOneByWhere(" where t.identificacion = "+ usuario.getIdentificacion()+" and t.estado = 1");
		if(usrovalida != null && usrovalida.getId() != null) {
			valor = false;
			Mensajes.mensajeAdvertencia("EL NUMERO DE IDENTIFICACION INGRESADO YA EXISTE EN EL SISTEMA");
		}
		return valor;
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll() {
		perfiles = perfilDAO.findAll();
		//usuarios = usuarioDAO.findAll();
		usuarioPerfiles = usuarioperfilDAO.findByFk(" where t.usuario.estado = 1");
	}

	@Override
	public void cancel() {
		usuario = new Usuario();
		findAll();
	}
	
	public void enviar(String path) {
		try {
			WebUtil.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void procesarCliente() {
		enviar("/forms/inicio.jsf");
	}
  	public void validarIdentificacion(String entrada) {
		if(!UsuarioValidation.isIdentificacionValida(entrada)) {
			usuario.setIdentificacion("");
			Mensajes.mensajeAdvertencia("NUMERO DE IDENTIFICACION ERRONEO");
		}
	}


}
