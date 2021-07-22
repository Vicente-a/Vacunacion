package com.vacunas.dao.jpa;

import javax.ejb.Stateless;

import com.vacunas.dao.CiudadanoDAO;
import com.vacunas.entity.Ciudadano;

@Stateless
public class CiudadanoDAOImpl extends AbstractBaseGenericDAOImpl<Ciudadano, Integer> implements CiudadanoDAO {


}
