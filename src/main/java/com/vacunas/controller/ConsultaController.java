package com.vacunas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.vacunas.dao.CiudadanoDAO;
import com.vacunas.dao.ConsultaDAO;
import com.vacunas.dao.DetallecatalogoDAO;
import com.vacunas.dao.DetallevacunaDAO;
import com.vacunas.dao.VacunaDAO;
import com.vacunas.entity.Ciudadano;
import com.vacunas.entity.Consulta;
import com.vacunas.entity.Detallevacuna;
import com.vacunas.entity.Vacuna;
import com.vacunas.session.VariableSesion;
import com.vacunas.utilitario.ComunEnum;
import com.vacunas.utilitario.FechasUtil;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.WebUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ConsultaController implements Bean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Consulta consulta;
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
	@Inject
	private VariableSesion variableSesion;
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
	private List<Vacuna> vacunas;
	@Getter
	@Setter
	private List<Consulta> consultas;
	@Getter
	@Setter
	private Boolean ocultar;
	private PrimeFaces current;

	@Override
	@PostConstruct
	public void init() {
		cancel();
	}

	@Override
	public void save() {
		if(validate()) {
			consulta.setFechaingreso(FechasUtil.getFechaTimestamp());
			consulta.setCiudadano(ciudadano);
			consulta.setUsuario(variableSesion.getUsuario());
			consulta.setVacuna(vacuna);
			consulta.setDosis(vacuna.getDosis());
			consulta = consultaDAO.create(consulta);
			if(consulta != null && consulta.getId() > 0) {
				Detallevacuna detallevacuna = new Detallevacuna();
				detallevacuna.setCantidad(1);
				detallevacuna.setVacuna(vacuna);
				detallevacuna.setDetallecatalogo(detallecatalogoDAO.findById(ComunEnum.TIPO_MOVIMIENTO_BAJA));
				detallevacuna = detallevacunaDAO.create(detallevacuna);
				vacuna.setSaldo(vacuna.getSaldo() - 1);
				vacuna = vacunaDAO.update(vacuna);
				Mensajes.mensajeInformacion("Informacion almacenada correctamente");
				//cancel();
			}
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
			 * BUSCO LA DOSIS
			 */
			List<Consulta> consultasAnterior = consultaDAO.findByFk(" where t.ciudadano.id = "+ciudadano.getId());
			if(consultasAnterior != null && consultasAnterior.size() > 0) {
				
				if(consultasAnterior.get(0).getVacuna().getDetallecatalogo().getValor().equals("1")) {
					ocultar = false;
					current = PrimeFaces.current(); current.executeScript("PF('tbl').hide();");
					Mensajes.mensajeAdvertencia("La vacuna "+consultasAnterior.get(0).getVacuna().getDetallecatalogo().getDescripcion()+" No necesita 2da dosis");
				}else {
					if(consultasAnterior.size() == 1) {
						vacunas = vacunaDAO.findByFk(" where t.dosis = 2 and t.detallecatalogo.id = "+consultasAnterior.get(0).getVacuna().getDetallecatalogo().getId());
					}else if(consultasAnterior.size() == 2) {
						ocultar = false;
						current = PrimeFaces.current(); current.executeScript("PF('tbl').hide();");
						Mensajes.mensajeAdvertencia("La identificacion "+identificacion+" cuenta con las 2 dosis de la vacuna");
					}
				}
			}else {
				vacunas = vacunaDAO.findByFk(" where t.dosis = 1");
			}
		}else {
			Mensajes.mensajeAdvertencia("El numero de cedula ingresado "+identificacion+" no se encuentra registrado por favor verifique");
		}
		
	}

	@Override
	public void findAll() {
		
	}
	
	@Override
	public void cancel() {
		consulta = new Consulta();
		ciudadano = new Ciudadano();
		identificacion = "";
		ocultar = true;
		vacunas = new ArrayList<Vacuna>();
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
}
