package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the detallecatalogo database table.
 * 
 */
@Entity
@Table(name="detallecatalogo", schema = "vcnn")
@NamedQuery(name="Detallecatalogo.findAll", query="SELECT d FROM Detallecatalogo d")
public class Detallecatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Column(name="detallecatalogo_id")
	private Integer detallecatalogoId;

	@Column(nullable=false)
	private Integer estado;

	@Column(length=10)
	private String valor;

	//bi-directional many-to-one association to Ciudadano
	@OneToMany(mappedBy="detallecatalogo1")
	private List<Ciudadano> ciudadanos1;

	//bi-directional many-to-one association to Ciudadano
	@OneToMany(mappedBy="detallecatalogo2")
	private List<Ciudadano> ciudadanos2;

	//bi-directional many-to-one association to Ciudadano
	@OneToMany(mappedBy="detallecatalogo3")
	private List<Ciudadano> ciudadanos3;

	//bi-directional many-to-one association to Ciudadano
	@OneToMany(mappedBy="detallecatalogo4")
	private List<Ciudadano> ciudadanos4;

	//bi-directional many-to-one association to Catalogo
	@ManyToOne
	@JoinColumn(name="catalogo_id", nullable=false)
	private Catalogo catalogo;

	//bi-directional many-to-one association to Vacuna
	@OneToMany(mappedBy="detallecatalogo")
	private List<Vacuna> vacunas;

	//bi-directional many-to-one association to Detallevacuna
	@OneToMany(mappedBy="detallecatalogo")
	private List<Detallevacuna> detallevacunas;

	public Detallecatalogo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDetallecatalogoId() {
		return this.detallecatalogoId;
	}

	public void setDetallecatalogoId(Integer detallecatalogoId) {
		this.detallecatalogoId = detallecatalogoId;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Ciudadano> getCiudadanos1() {
		return this.ciudadanos1;
	}

	public void setCiudadanos1(List<Ciudadano> ciudadanos1) {
		this.ciudadanos1 = ciudadanos1;
	}

	public Ciudadano addCiudadanos1(Ciudadano ciudadanos1) {
		getCiudadanos1().add(ciudadanos1);
		ciudadanos1.setDetallecatalogo1(this);

		return ciudadanos1;
	}

	public Ciudadano removeCiudadanos1(Ciudadano ciudadanos1) {
		getCiudadanos1().remove(ciudadanos1);
		ciudadanos1.setDetallecatalogo1(null);

		return ciudadanos1;
	}

	public List<Ciudadano> getCiudadanos2() {
		return this.ciudadanos2;
	}

	public void setCiudadanos2(List<Ciudadano> ciudadanos2) {
		this.ciudadanos2 = ciudadanos2;
	}

	public Ciudadano addCiudadanos2(Ciudadano ciudadanos2) {
		getCiudadanos2().add(ciudadanos2);
		ciudadanos2.setDetallecatalogo2(this);

		return ciudadanos2;
	}

	public Ciudadano removeCiudadanos2(Ciudadano ciudadanos2) {
		getCiudadanos2().remove(ciudadanos2);
		ciudadanos2.setDetallecatalogo2(null);

		return ciudadanos2;
	}

	public List<Ciudadano> getCiudadanos3() {
		return this.ciudadanos3;
	}

	public void setCiudadanos3(List<Ciudadano> ciudadanos3) {
		this.ciudadanos3 = ciudadanos3;
	}

	public Ciudadano addCiudadanos3(Ciudadano ciudadanos3) {
		getCiudadanos3().add(ciudadanos3);
		ciudadanos3.setDetallecatalogo3(this);

		return ciudadanos3;
	}

	public Ciudadano removeCiudadanos3(Ciudadano ciudadanos3) {
		getCiudadanos3().remove(ciudadanos3);
		ciudadanos3.setDetallecatalogo3(null);

		return ciudadanos3;
	}

	public List<Ciudadano> getCiudadanos4() {
		return this.ciudadanos4;
	}

	public void setCiudadanos4(List<Ciudadano> ciudadanos4) {
		this.ciudadanos4 = ciudadanos4;
	}

	public Ciudadano addCiudadanos4(Ciudadano ciudadanos4) {
		getCiudadanos4().add(ciudadanos4);
		ciudadanos4.setDetallecatalogo4(this);

		return ciudadanos4;
	}

	public Ciudadano removeCiudadanos4(Ciudadano ciudadanos4) {
		getCiudadanos4().remove(ciudadanos4);
		ciudadanos4.setDetallecatalogo4(null);

		return ciudadanos4;
	}

	public Catalogo getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public List<Vacuna> getVacunas() {
		return this.vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Vacuna addVacuna(Vacuna vacuna) {
		getVacunas().add(vacuna);
		vacuna.setDetallecatalogo(this);

		return vacuna;
	}

	public Vacuna removeVacuna(Vacuna vacuna) {
		getVacunas().remove(vacuna);
		vacuna.setDetallecatalogo(null);

		return vacuna;
	}

	public List<Detallevacuna> getDetallevacunas() {
		return this.detallevacunas;
	}

	public void setDetallevacunas(List<Detallevacuna> detallevacunas) {
		this.detallevacunas = detallevacunas;
	}

	public Detallevacuna addDetallevacuna(Detallevacuna detallevacuna) {
		getDetallevacunas().add(detallevacuna);
		detallevacuna.setDetallecatalogo(this);

		return detallevacuna;
	}

	

}