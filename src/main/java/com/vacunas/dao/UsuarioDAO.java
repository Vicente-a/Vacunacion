package com.vacunas.dao;

import javax.ejb.Local;

import com.vacunas.entity.Usuario;

@Local
public interface UsuarioDAO extends BaseGenericDAO<Usuario, Integer> {

}
