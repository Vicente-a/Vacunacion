package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 * 
 */
@Entity
@Table(name="perfil", schema = "vcnn")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50)
	private String descripcion;

	@Column(nullable=false)
	private Integer estado;

	//bi-directional many-to-one association to Usuarioperfil
	@OneToMany(mappedBy="perfil")
	private List<Usuarioperfil> usuarioperfils;

	public Perfil() {
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

	public List<Usuarioperfil> getUsuarioperfils() {
		return this.usuarioperfils;
	}

	public void setUsuarioperfils(List<Usuarioperfil> usuarioperfils) {
		this.usuarioperfils = usuarioperfils;
	}

	public Usuarioperfil addUsuarioperfil(Usuarioperfil usuarioperfil) {
		getUsuarioperfils().add(usuarioperfil);
		usuarioperfil.setPerfil(this);

		return usuarioperfil;
	}

	public Usuarioperfil removeUsuarioperfil(Usuarioperfil usuarioperfil) {
		getUsuarioperfils().remove(usuarioperfil);
		usuarioperfil.setPerfil(null);

		return usuarioperfil;
	}

}
