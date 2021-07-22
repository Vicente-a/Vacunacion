package com.vacunas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.vacunas.dao.CiudadanoDAO;
import com.vacunas.dao.ConsultaDAO;
import com.vacunas.dao.DetallecatalogoDAO;
import com.vacunas.dao.DetallevacunaDAO;
import com.vacunas.dao.VacunaDAO;
import com.vacunas.entity.Ciudadano;
import com.vacunas.entity.Consulta;
import com.vacunas.entity.Detallecatalogo;
import com.vacunas.entity.Vacuna;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.WebUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ConsultaAdmController implements Bean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private List<Consulta> consultas;
	@EJB(beanName="ConsultaDAOImpl")
	private ConsultaDAO consultaDAO;
	@Getter
	@Setter
	private Ciudadano ciudadano;
	@EJB(beanName="CiudadanoDAOImpl")
	private CiudadanoDAO ciudadanoDAO;
	@Getter
	@Setter
	private String identificacion;
	@Getter
	@Setter
	private Vacuna vacuna;
	@EJB(beanName="VacunaDAOImpl")
	private VacunaDAO vacunaDAO;
	@EJB(beanName="DetallevacunaDAOImpl")
	private DetallevacunaDAO detallevacunaDAO;
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

	@Override
	@PostConstruct
	public void init() {
		cancel();

	}

	@Override
	public void save() {
		if(validate()) {
			ciudadano = ciudadanoDAO.update(ciudadano);
		}

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean validate() {
		return true;
	}

	@Override
	public void find() {
		ciudadano = ciudadanoDAO.findOneByWhere(" where t.cedula = '"+identificacion+"'");
		if(ciudadano != null && ciudadano.getId() != null) {
			/**
			 * BUSCO LA CONSULTAS
			 */
			consultas = consultaDAO.findByFk(" where t.ciudadano.id = "+ciudadano.getId());
			/**
			 * DATOS GENERALES
			 */
			codigoPais = ciudadano.getDetallecatalogo1().getId();
			findProvincias();
			codigoProvincia = ciudadano.getDetallecatalogo2().getId();
			findCanton();
			codigoCanton = ciudadano.getDetallecatalogo3().getId();
			codigoSector = ciudadano.getDetallecatalogo4().getId();
			
			
		}else {
			Mensajes.mensajeAdvertencia("El numero de cedula ingresado "+identificacion+" no se encuentra registrado por favor verifique");
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
		identificacion = "";
		findAll();
	}

	public void findProvincias(){
		provincias = detallecatalogoDAO.findByFk(" where t.detallecatalogoId = "+codigoPais);
	}
	public void findCanton(){
		cantones = detallecatalogoDAO.findByFk(" where t.detallecatalogoId = "+codigoProvincia);
	}
	public void enviar(String path) {
		try {
			WebUtil.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
