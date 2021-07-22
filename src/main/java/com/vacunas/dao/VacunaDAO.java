package com.vacunas.dao;

import javax.ejb.Local;

import com.vacunas.entity.Vacuna;

@Local
public interface VacunaDAO extends BaseGenericDAO<Vacuna, Integer> {

}
