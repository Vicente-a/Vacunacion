package com.vacunas.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ciudadano database table.
 * 
 */
@Entity
@Table(name="ciudadano", schema = "vcnn")
@NamedQuery(name="Ciudadano.findAll", query="SELECT c FROM Ciudadano c")
public class Ciudadano implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=100)
	private String apellidos;

	@Column(nullable=false, length=15)
	private String cedula;

	@Column(length=100)
	private String correo;

	@Column(nullable=false)
	private Integer estado;

	@Column(nullable=false)
	private Timestamp fechaingreso;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fechanacimiento;

	@Column(nullable=false, length=100)
	private String nombres;

	@Column(length=20)
	private String telefono;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="ctpais_id", nullable=false)
	private Detallecatalogo detallecatalogo1;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="ctprovincia_id", nullable=false)
	private Detallecatalogo detallecatalogo2;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="ctcanton_id", nullable=false)
	private Detallecatalogo detallecatalogo3;

	//bi-directional many-to-one association to Detallecatalogo
	@ManyToOne
	@JoinColumn(name="ctsector_id", nullable=false)
	private Detallecatalogo detallecatalogo4;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="ciudadano")
	private List<Consulta> consultas;

	public Ciudadano() {
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

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Detallecatalogo getDetallecatalogo1() {
		return this.detallecatalogo1;
	}

	public void setDetallecatalogo1(Detallecatalogo detallecatalogo1) {
		this.detallecatalogo1 = detallecatalogo1;
	}

	public Detallecatalogo getDetallecatalogo2() {
		return this.detallecatalogo2;
	}

	public void setDetallecatalogo2(Detallecatalogo detallecatalogo2) {
		this.detallecatalogo2 = detallecatalogo2;
	}

	public Detallecatalogo getDetallecatalogo3() {
		return this.detallecatalogo3;
	}

	public void setDetallecatalogo3(Detallecatalogo detallecatalogo3) {
		this.detallecatalogo3 = detallecatalogo3;
	}

	public Detallecatalogo getDetallecatalogo4() {
		return this.detallecatalogo4;
	}

	public void setDetallecatalogo4(Detallecatalogo detallecatalogo4) {
		this.detallecatalogo4 = detallecatalogo4;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setCiudadano(this);
		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setCiudadano(null);
		return consulta;
	}

}
