package com.proyecto.service.rule;

import com.common.util.service.GenericService;
import com.proyecto.dao.rule.RuleSetDao;
import com.proyecto.model.rule.RuleSet;

/**
 * La interfaz que nos permite definir los servicios que vamos a aplicar a los conjuntos de las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface RuleSetService extends GenericService<RuleSet, Integer> {

	/**
	 * La función encargada de setear el DAO para este servicio.
	 * 
	 * @param ruleSetDao
	 *            El dao para el servicio de los conjuntos de las reglas.
	 */
	public void setRuleSetDao(RuleSetDao ruleSetDao);
}
