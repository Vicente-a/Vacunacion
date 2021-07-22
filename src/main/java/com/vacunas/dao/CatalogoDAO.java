package com.vacunas.dao;

import javax.ejb.Local;

import com.vacunas.entity.Catalogo;

@Local
public interface CatalogoDAO extends BaseGenericDAO<Catalogo, Integer> {

}
