package com.vacunas.dao.jpa;

import javax.ejb.Stateless;

import com.vacunas.dao.ConsultaDAO;
import com.vacunas.entity.Consulta;

@Stateless
public class ConsultaDAOImpl extends AbstractBaseGenericDAOImpl<Consulta, Integer> implements ConsultaDAO {

}
