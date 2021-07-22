package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the catalogo database table.
 * 
 */
@Entity
@Table(name="catalogo", schema = "vcnn")
@NamedQuery(name="Catalogo.findAll", query="SELECT c FROM Catalogo c")
public class Catalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String descripcion;

	@Column(nullable=false)
	private Integer estado;

	//bi-directional many-to-one association to Detallecatalogo
	@OneToMany(mappedBy="catalogo")
	private List<Detallecatalogo> detallecatalogos;

	public Catalogo() {
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

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public List<Detallecatalogo> getDetallecatalogos() {
		return this.detallecatalogos;
	}

	public void setDetallecatalogos(List<Detallecatalogo> detallecatalogos) {
		this.detallecatalogos = detallecatalogos;
	}

	public Detallecatalogo addDetallecatalogo(Detallecatalogo detallecatalogo) {
		getDetallecatalogos().add(detallecatalogo);
		detallecatalogo.setCatalogo(this);

		return detallecatalogo;
	}

	public Detallecatalogo removeDetallecatalogo(Detallecatalogo detallecatalogo) {
		getDetallecatalogos().remove(detallecatalogo);
		detallecatalogo.setCatalogo(null);

		return detallecatalogo;
	}

}