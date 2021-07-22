package com.vacunas.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.vacunas.dao.CiudadanoDAO;
import com.vacunas.dao.DetallecatalogoDAO;
import com.vacunas.entity.Ciudadano;
import com.vacunas.entity.Detallecatalogo;
import com.vacunas.utilitario.FechasUtil;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.PersonaUtil;
import com.vacunas.utilitario.WebUtil;
import com.vacunas.validaciones.UsuarioValidation;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CiudadanoController implements Bean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3528161593244366145L;
	@Getter
	@Setter
	private Ciudadano ciudadano;
	@EJB(beanName="CiudadanoDAOImpl")
	private CiudadanoDAO ciudadanoDAO;
	@EJB(beanName="DetallecatalogoDAOImpl")
	private DetallecatalogoDAO detallecatalogoDAO;
	@Getter
	@Setter
	private List<Detallecatalogo> paises;
	@Getter
	@Setter
	private List<Detallecatalogo> provincias;
	@Getter
	@Setter
	private List<Detallecatalogo> cantones;
	@Getter
	@Setter
	private Date fechaNacimiento;
	@Getter
	@Setter
	private List<Detallecatalogo> sectores;
	@Getter
	@Setter
	private Integer codigoPais;
	@Getter
	@Setter
	private Integer codigoProvincia;
	@Getter
	@Setter
	private Integer codigoCanton;
	@Getter
	@Setter
	private Integer codigoSector;
	@Getter
	@Setter
	private Boolean requerido;
	@Getter
	@Setter
	private String validaCorreo;

	@Override
	@PostConstruct
	public void init() {
		cancel();

	}

	@Override
	public void save() {
		if(validate()) {
			try {
				
				ciudadano.setFechanacimiento(fechaNacimiento);
				ciudadano.setDetallecatalogo1(detallecatalogoDAO.findById(codigoPais));
				ciudadano.setDetallecatalogo2(detallecatalogoDAO.findById(codigoProvincia));
				ciudadano.setDetallecatalogo3(detallecatalogoDAO.findById(codigoCanton));
				ciudadano.setDetallecatalogo4(detallecatalogoDAO.findById(codigoSector));
				ciudadano.setFechaingreso(FechasUtil.getFechaTimestamp());
				ciudadano.setEstado(1);
				ciudadano = ciudadanoDAO.create(ciudadano);
				Mensajes.mensajeInformacion("Registro almacenado correctamente");
				//redirigir();
				cancel();
			} catch (Exception e) {
				Mensajes.mensajeAdvertencia("Error al almacenar el registro "+e.getMessage());
			}
		}
		
	}
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean validate() {
		Boolean valor = true;
		if(ciudadano.getCedula() == null || ciudadano.getCedula().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo cedula es requerido");
		}
		if(ciudadano.getApellidos() == null || ciudadano.getApellidos().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo apellidos es requerido");
		}
		if(ciudadano.getNombres() == null || ciudadano.getNombres().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo nombres es requerido");
		}
		if(fechaNacimiento == null ) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo fecha es requerido");
		}
		if(ciudadano.getCorreo() == null || ciudadano.getCorreo().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo correo es requerido");
		}
		if(ciudadano.getTelefono() == null || ciudadano.getTelefono().equals("")) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo telefono es requerido");
		}
		if( codigoPais == null) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo pais es requerido");
		}
		if(codigoProvincia == null) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo provincia es requerido");
		}
		if(codigoCanton == null) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo ciudad es requerido");
		}
		if(codigoSector == null) {
			valor = false;
			Mensajes.mensajeAdvertencia("Campo sector es requerido");
		}
		return valor;
	}

	@Override
	public void find() {
		String cedula = ciudadano.getCedula();
		if(UsuarioValidation.isIdentificacionValida(ciudadano.getCedula())) {
			ciudadano = ciudadanoDAO.findOneByWhere(" where t.cedula = '"+ciudadano.getCedula()+"' and t.estado = 1");
			if(ciudadano != null && ciudadano.getId() != null) {
				ciudadano = new Ciudadano();
				Mensajes.mensajeAdvertencia("Usuario ya registrado ");
			}else {
				ciudadano = new Ciudadano();
				ciudadano.setCedula(cedula);
				requerido = true;
			}
		}else {
			Mensajes.mensajeAdvertencia("Numero de identificacion "+ciudadano.getCedula()+" no es valido ");
		}
	}

	@Override
	public void findAll() {
		paises = detallecatalogoDAO.findByFk(" where t.catalogo.id = 1");
		sectores = detallecatalogoDAO.findByFk(" where t.catalogo.id = 4");
	}

	@Override
	public void cancel() {
		ciudadano = new Ciudadano();
		fechaNacimiento = new Date();
		requerido = false;
		codigoPais = null;
		codigoProvincia = null;
		codigoCanton = null;
		codigoSector = null;
		validaCorreo = PersonaUtil.EMAIL_PATTERN;
		findAll();
	}

	public void findProvincias(){
		provincias = detallecatalogoDAO.findByFk(" where t.detallecatalogoId = "+codigoPais);
	}
	public void findCanton(){
		cantones = detallecatalogoDAO.findByFk(" where t.detallecatalogoId = "+codigoProvincia);
	}
	
	
        
}
