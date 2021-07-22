package com.vacunas.dao.jpa;

import javax.ejb.Stateless;

import com.vacunas.dao.PerfilDAO;
import com.vacunas.entity.Perfil;

@Stateless
public class PerfilDAOImpl extends AbstractBaseGenericDAOImpl<Perfil, Integer> implements PerfilDAO {

}
