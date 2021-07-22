package com.vacunas.dao;

import javax.ejb.Local;

import com.vacunas.entity.Ciudadano;

@Local
public interface CiudadanoDAO extends BaseGenericDAO<Ciudadano, Integer> {

}
