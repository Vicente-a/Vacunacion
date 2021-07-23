package com.vacunas.entity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the usuarioperfil database table.
 * 
 */

@Entity
@Table(name="usuarioperfil", schema = "vcnn")
@NamedQuery(name="Usuarioperfil.findAll", query="SELECT u FROM Usuarioperfil u")
public class Usuarioperfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="perfil_id", nullable=false)
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;

	public Usuarioperfil() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
