package com.vacunas.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the consulta database table.
 * 
 */
@Entity
@Table(name="consulta", schema = "vcnn")
@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private Integer dosis;

	@Column(nullable=false)
	private Timestamp fechaingreso;

	//bi-directional many-to-one association to Ciudadano
	@ManyToOne
	@JoinColumn(name="ciudadano_id", nullable=false)
	private Ciudadano ciudadano;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Vacuna
	@ManyToOne
	@JoinColumn(name="vacuna_id", nullable=false)
	private Vacuna vacuna;

	//bi-directional many-to-one association to Detallevacuna
	@OneToMany(mappedBy="consulta")
	private List<Detallevacuna> detallevacunas;

	public Consulta() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDosis() {
		return this.dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public Timestamp getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Timestamp fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public Ciudadano getCiudadano() {
		return this.ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vacuna getVacuna() {
		return this.vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public List<Detallevacuna> getDetallevacunas() {
		return this.detallevacunas;
	}

	public void setDetallevacunas(List<Detallevacuna> detallevacunas) {
		this.detallevacunas = detallevacunas;
	}

	public Detallevacuna addDetallevacuna(Detallevacuna detallevacuna) {
		getDetallevacunas().add(detallevacuna);
		detallevacuna.setConsulta(this);
		return detallevacuna;
	}

	public Detallevacuna removeDetallevacuna(Detallevacuna detallevacuna) {
		getDetallevacunas().remove(detallevacuna);
		detallevacuna.setConsulta(null);
		return detallevacuna;
	}

}
