package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario", schema = "vcnn")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String apellidos;

	@Column(nullable=false)
	private Integer estado;

	@Column(nullable=false)
	private Timestamp fechaingreso;

	@Column(nullable=false, length=15)
	private String identificacion;

	@Column(length=13)
	private String login;

	@Column(nullable=false, length=100)
	private String nombres;

	@Column(length=200)
	private String password;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="usuario")
	private List<Consulta> consultas;

	//bi-directional many-to-one association to Usuarioperfil
	@OneToMany(mappedBy="usuario")
	private List<Usuarioperfil> usuarioperfils;

	//bi-directional many-to-one association to Vacuna
	@OneToMany(mappedBy="usuario")
	private List<Vacuna> vacunas;

	public Usuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Timestamp fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setUsuario(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setUsuario(null);

		return consulta;
	}

	public List<Usuarioperfil> getUsuarioperfils() {
		return this.usuarioperfils;
	}

	public void setUsuarioperfils(List<Usuarioperfil> usuarioperfils) {
		this.usuarioperfils = usuarioperfils;
	}

	public Usuarioperfil addUsuarioperfil(Usuarioperfil usuarioperfil) {
		getUsuarioperfils().add(usuarioperfil);
		usuarioperfil.setUsuario(this);

		return usuarioperfil;
	}

	public Usuarioperfil removeUsuarioperfil(Usuarioperfil usuarioperfil) {
		getUsuarioperfils().remove(usuarioperfil);
		usuarioperfil.setUsuario(null);

		return usuarioperfil;
	}

	public List<Vacuna> getVacunas() {
		return this.vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Vacuna addVacuna(Vacuna vacuna) {
		getVacunas().add(vacuna);
		vacuna.setUsuario(this);

		return vacuna;
	}

	public Vacuna removeVacuna(Vacuna vacuna) {
		getVacunas().remove(vacuna);
		vacuna.setUsuario(null);

		return vacuna;
	}

}