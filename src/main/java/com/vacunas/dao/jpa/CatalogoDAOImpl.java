package com.vacunas.dao.jpa;

import javax.ejb.Stateless;

import com.vacunas.dao.CatalogoDAO;
import com.vacunas.entity.Catalogo;

@Stateless
public class CatalogoDAOImpl extends AbstractBaseGenericDAOImpl<Catalogo, Integer> implements CatalogoDAO {

}
