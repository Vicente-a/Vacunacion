package com.vacunas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vacunas.dao.DetallecatalogoDAO;
import com.vacunas.dao.DetallevacunaDAO;
import com.vacunas.dao.VacunaDAO;
import com.vacunas.entity.Detallecatalogo;
import com.vacunas.entity.Detallevacuna;
import com.vacunas.entity.Vacuna;
import com.vacunas.session.VariableSesion;
import com.vacunas.utilitario.ComunEnum;
import com.vacunas.utilitario.Mensajes;
import com.vacunas.utilitario.WebUtil;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class VacunasController implements Bean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private Vacuna vacuna;
	@EJB(beanName="DetallecatalogoDAOImpl")
	private DetallecatalogoDAO detallecatalogoDAO;
	@EJB(beanName="VacunaDAOImpl")
	private VacunaDAO vacunaDAO;
	@Getter
	@Setter
	private List<Detallecatalogo> vacunas;
	@Getter
	@Setter
	private Integer codigoVacuna;
	@Inject
	private VariableSesion variableSesion;
	@Getter
	@Setter
	private List<Vacuna> inventario;
	@EJB(beanName="DetallevacunaDAOImpl")
	private DetallevacunaDAO detallevacunaDAO;
	@Getter
	@Setter
	private Integer cantidadVacuna;
	@Getter
	@Setter
	private Integer dosisVacuna;

	@Override
	@PostConstruct
	public void init() {
		cancel();

	}

	@Override
	public void save() {
		if(validate()) {
			vacuna.setDetallecatalogo(detallecatalogoDAO.findById(codigoVacuna));
			vacuna.setFechaingreso(new Date());
			vacuna.setUsuario(variableSesion.getUsuario());
			vacuna.setEstado(1);
			vacuna.setSaldo(vacuna.getCantidad());
			vacuna = vacunaDAO.create(vacuna);
			if(vacuna != null && vacuna.getId() > 0) {
				inventario.add(vacuna);
				Detallevacuna detallevacuna = new Detallevacuna();
				detallevacuna.setCantidad(vacuna.getSaldo());
				detallevacuna.setVacuna(vacuna);
				detallevacuna.setDetallecatalogo(detallecatalogoDAO.findById(ComunEnum.TIPO_MOVIMIENTO_ALTA));
				detallevacuna = detallevacunaDAO.create(detallevacuna);
				cancel();
			}
		}
	}
	
	public void update() {
		if(cantidadVacuna > 0) {
			cantidadVacuna = cantidadVacuna + vacuna.getSaldo();
			vacuna.setSaldo(cantidadVacuna);
			vacuna.setCantidad(cantidadVacuna);
			vacuna.setDosis(dosisVacuna);
			vacuna = vacunaDAO.update(vacuna);
			Detallevacuna detallevacuna = new Detallevacuna();
			detallevacuna.setCantidad(cantidadVacuna);
			detallevacuna.setVacuna(vacuna);
			detallevacuna.setDetallecatalogo(detallecatalogoDAO.findById(ComunEnum.TIPO_MOVIMIENTO_ALTA));
			detallevacuna = detallevacunaDAO.create(detallevacuna);
			cancel();
		}else {
			Mensajes.mensajeAdvertencia("No se puede actualziar a 0");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll() {
		vacunas = detallecatalogoDAO.findByFk(" where t.catalogo.id = 5");
		inventario = vacunaDAO.findAll();
	}

	@Override
	public void cancel() {
		vacuna = new Vacuna();
		inventario = new ArrayList<Vacuna>();
		cantidadVacuna = 0;
		dosisVacuna = 0;
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

	public void tomaDosis(Integer entrada) {
		dosisVacuna = entrada;
	}
}
