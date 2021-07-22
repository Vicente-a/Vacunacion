package com.vacunas.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.vacunas.dao.UsuarioDAO;
import com.vacunas.dao.UsuarioperfilDAO;
import com.vacunas.entity.Usuario;
import com.vacunas.entity.Usuarioperfil;
import com.vacunas.session.VariableSesion;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.WebUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class ValidacionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Setter
	@Getter
	private Usuario usuario;
	@Setter
	@Getter
	private String login;
	@Setter
	@Getter
	private String clave; 
	@EJB(beanName="UsuarioDAOImpl")
	private UsuarioDAO usuarioDAO;
	@Inject
	private VariableSesion variableSesion;
	@EJB(beanName="UsuarioperfilDAOImpl")
	private UsuarioperfilDAO usuarioperfilDAO;
	private List<Usuarioperfil> perfiles;
	@Getter
	private MenuModel model;
	
	public void validarAcceso() {
		try {
			if(!login.equals("") && !clave.equals("")) {
				clave = WebUtil.encrypt512(clave).toUpperCase();
				usuario = usuarioDAO.findOneByWhere(" where t.login = '"+login+"' and t.password = '"+clave+"'");
				if(usuario != null) {
					variableSesion.setUsuario(usuario);
					perfiles = usuarioperfilDAO.findByFk(" where t.usuario.id = "+usuario.getId());
					variableSesion.setPerfiles(perfiles);
					model = new DefaultMenuModel(); 
					
					for (Usuarioperfil usuarioperfil : perfiles) {
						if(usuarioperfil.getPerfil().getId() == 1) {
							DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("Administrador").build();

					        DefaultMenuItem item = DefaultMenuItem.builder()
					                .value("Usuario")
					                .icon("pi pi-save")
					                .ajax(false)
					                .url("/Vacunas/forms/usuario.jsf")
					                .build();
					        firstSubmenu.getElements().add(item);
					        item = DefaultMenuItem.builder()
					                .value("Vacunas")
					                .icon("pi pi-save")
					                .ajax(false)
					                .url("/Vacunas/forms/vacunas.jsf")
					                .build();
					        firstSubmenu.getElements().add(item);
					        item = DefaultMenuItem.builder()
					                .value("Consulta Adm")
					                .icon("pi pi-save")
					                .ajax(false)
					                .url("/Vacunas/forms/consultaAdm.jsf")
					                .build();
					        firstSubmenu.getElements().add(item);
					        item = DefaultMenuItem.builder()
					                .value("Salir")
					                .icon("pi pi-external-link")
					                .ajax(false)
					                .url("/Vacunas")
					                .build();
					        firstSubmenu.getElements().add(item);
					        model.getElements().add(firstSubmenu);
						}else if(usuarioperfil.getPerfil().getId() == 2) {
							DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("MEDICO").build();

					        DefaultMenuItem item = DefaultMenuItem.builder()
					                .value("Consulta")
					                .icon("pi pi-save")
					                .ajax(false)
					                .url("/Vacunas/forms/consulta.jsf")
					                .build();
					        firstSubmenu.getElements().add(item);
					        //model.getElements().add(firstSubmenu);
					        item = DefaultMenuItem.builder()
					                .value("Salir")
					                .icon("pi pi-external-link")
					                .ajax(false)
					                .url("/Vacunas")
					                .build();
					        firstSubmenu.getElements().add(item);
					        model.getElements().add(firstSubmenu);
						}else if(usuarioperfil.getPerfil().getId() == 3) {
							DefaultSubMenu firstSubmenu = DefaultSubMenu.builder().label("INFORMACION").build();
					        DefaultMenuItem item = DefaultMenuItem.builder()
					                .value("Consulta")
					                .icon("pi pi-save")
					                .ajax(false)
					                .url("/Vacunas/forms/consultaAdm.jsf")
					                .build();
					        firstSubmenu.getElements().add(item);
					        //model.getElements().add(firstSubmenu);
					        item = DefaultMenuItem.builder()
					                .value("Salir")
					                .icon("pi pi-external-link")
					                .ajax(false)
					                .url("/Vacunas")
					                .build();
					        firstSubmenu.getElements().add(item);
					        model.getElements().add(firstSubmenu);
						}
					}
					WebUtil.redirect("inicio.jsf");
				}else {
					Mensajes.mensajeError("Usuario o password incorrectos por favor verificar");
				}
			}else {
				Mensajes.mensajeError("Verificar los parametros de validación");
			}
		} catch (Exception e) {
			Mensajes.mensajeAdvertencia("Error generado "+e.getMessage());
		}
		
	}
}
