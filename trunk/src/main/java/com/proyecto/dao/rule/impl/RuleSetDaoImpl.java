package com.proyecto.dao.rule.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.rule.RuleSetDao;
import com.proyecto.model.rule.RuleSet;

/**
 * La clase que implementa la interfaz del DAO de los conjuntos de las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class RuleSetDaoImpl extends ProyectoDaoImpl<RuleSet, Integer> implements RuleSetDao {

	private static final long serialVersionUID = 8698299198542422229L;
}
