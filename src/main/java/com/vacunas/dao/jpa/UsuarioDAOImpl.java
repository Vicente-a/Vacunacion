package com.vacunas.dao.jpa;

import javax.ejb.Stateless;

import com.vacunas.dao.UsuarioDAO;
import com.vacunas.entity.Usuario;

@Stateless
public class UsuarioDAOImpl extends AbstractBaseGenericDAOImpl<Usuario, Integer> implements UsuarioDAO {


}
