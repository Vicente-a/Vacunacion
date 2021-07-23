package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vacuna database table.
 * 
 */
@Entity
@Table(name="vacuna", schema = "vcnn")
@NamedQuery(name="Vacuna.findAll", query="SELECT v FROM Vacuna v")
public class Vacuna implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;


	@Column(nullable=false)
	private Integer cantidad;

	@Column(nullable=false)
	private Integer dosis;

	@Column(nullable=false)
	private Integer estado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fechaingreso;

	@Column(nullable=false)
	private Integer saldo;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="vacuna")
	private List<Consulta> consultas;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="ctvacuna_id", nullable=false)
	private Detallecatalogo detallecatalogo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Detallevacuna
	@OneToMany(mappedBy="vacuna")
	private List<Detallevacuna> detallevacunas;

	public Vacuna() {
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

	public Integer getDosis() {
		return this.dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Integer getSaldo() {
		return this.saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setVacuna(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setVacuna(null);

		return consulta;
	}

	public Detallecatalogo getDetallecatalogo() {
		return this.detallecatalogo;
	}

	public void setDetallecatalogo(Detallecatalogo detallecatalogo) {
		this.detallecatalogo = detallecatalogo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Detallevacuna> getDetallevacunas() {
		return this.detallevacunas;
	}

	public void setDetallevacunas(List<Detallevacuna> detallevacunas) {
		this.detallevacunas = detallevacunas;
	}

	public Detallevacuna addDetallevacuna(Detallevacuna detallevacuna) {
		getDetallevacunas().add(detallevacuna);
		detallevacuna.setVacuna(this);

		return detallevacuna;
	}

	public Detallevacuna removeDetallevacuna(Detallevacuna detallevacuna) {
		getDetallevacunas().remove(detallevacuna);
		detallevacuna.setVacuna(null);

		return detallevacuna;
	}

}
