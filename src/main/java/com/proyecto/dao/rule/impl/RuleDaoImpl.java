package com.proyecto.dao.rule.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.rule.RuleDao;
import com.proyecto.model.rule.Rule;

/**
 * La clase que implementa la interfaz del DAO para las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class RuleDaoImpl extends ProyectoDaoImpl<Rule, Integer> implements RuleDao {
}
