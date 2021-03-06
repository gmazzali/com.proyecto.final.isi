package com.proyecto.service.rule.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.model.query.filter.Filter;
import com.proyecto.dao.rule.RuleDao;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.rule.RuleService;

/**
 * La clase que nos permite implementar la interfaz de los servicios de las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RuleServiceImpl extends ProyectoServiceImpl<Rule, Integer> implements RuleService {

	@Override
	@Autowired
	public void setRuleDao(RuleDao ruleDao) {
		this.setDao(ruleDao);
	}

	@Override
	public void validate(Rule entity) throws CheckedException {
	}

	/**
	 * La funci�n que recupera todas las reglas activas dentro del sistema.
	 * 
	 * @return El listado de reglas activas dentro del sistema.
	 */
	@Override
	public List<Rule> findAll() throws CheckedException {
		Filter filter = Filter.eq(RuleSet.Attributes.ACTIVE, true);
		return this.findByFilter(filter);
	}

	/**
	 * El borrado se realiza cambiando el estado de la regla a inactiva.
	 * 
	 * @param entity
	 *            La regla que vamos a eliminar.
	 */
	@Override
	public void delete(Rule entity) throws CheckedException {
		// Cargamos el estado de inactivo y actualizamos.
		entity.setActive(false);
		this.update(entity);
	}

	/**
	 * El borrado se realiza cambiando el estado de la regla que corresponde con el id a inactiva.
	 * 
	 * @param id
	 *            El identificador de la regla que vamos a eliminar.
	 */
	@Override
	public void deleteById(Integer id) throws CheckedException {
		// Recuperamos el conjunto, lo marcamos como inactivo y lo actualizamos.
		Rule entity = this.findById(id);
		entity.setActive(false);
		this.update(entity);
	}

	@Override
	public com.hp.hpl.jena.reasoner.rulesys.Rule parseRule(Rule rule) throws Exception {
		String nuevaRegla = rule.getRule();
		com.hp.hpl.jena.reasoner.rulesys.Rule r = com.hp.hpl.jena.reasoner.rulesys.Rule.parseRule(nuevaRegla);
		return r;
	}
}