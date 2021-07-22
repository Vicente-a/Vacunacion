package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the detallevacuna database table.
 * 
 */
@Entity
@Table(name="detallevacuna", schema = "vcnn")
@NamedQuery(name="Detallevacuna.findAll", query="SELECT d FROM Detallevacuna d")
public class Detallevacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Integer cantidad;

	//bi-directional many-to-one association to Consulta
	@ManyToOne
	@JoinColumn(name="consulta_id")
	private Consulta consulta;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="cttipomovimiento_id", nullable=false)
	private Detallecatalogo detallecatalogo;

	//bi-directional many-to-one association to Vacuna
	@ManyToOne
	@JoinColumn(name="vacuna_id", nullable=false)
	private Vacuna vacuna;

	public Detallevacuna() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Consulta getConsulta() {
		return this.consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Detallecatalogo getDetallecatalogo() {
		return this.detallecatalogo;
	}

	public void setDetallecatalogo(Detallecatalogo detallecatalogo) {
		this.detallecatalogo = detallecatalogo;
	}

	public Vacuna getVacuna() {
		return this.vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

}