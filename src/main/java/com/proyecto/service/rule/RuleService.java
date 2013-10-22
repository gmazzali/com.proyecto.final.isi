package com.proyecto.service.rule;

import com.proyecto.dao.rule.RuleDao;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios para las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface RuleService extends ProyectoService<Rule, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param ruleDao
	 *            El dao para el servicio de las reglas.
	 */
	public void setRuleDao(RuleDao ruleDao);

	/**
	 * La función encargada de convertir una regla del proyecto en una regla para poder utilizarse en la ontología creada con JENA.
	 * 
	 * @param rule
	 *            La regla que tenemos dentro del proyecto.
	 * @return La regla creada para usarla dentro de una ontología en JENA.
	 * @throws Exception
	 *             En caso de una falla en el parseo de la regla.
	 */
	public com.hp.hpl.jena.reasoner.rulesys.Rule parseRule(Rule rule) throws Exception;
}
