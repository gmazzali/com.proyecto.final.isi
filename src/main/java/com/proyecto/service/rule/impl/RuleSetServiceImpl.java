package com.proyecto.service.rule.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.model.query.filter.Filter;
import com.proyecto.dao.rule.RuleSetDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.rule.RuleSetService;

/**
 * La clase que implementa la interfaz de los servicios de los conjuntos de las reglas dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RuleSetServiceImpl extends ProyectoServiceImpl<RuleSet, Integer> implements RuleSetService {

	@Override
	public void validate(RuleSet entity) throws CheckedException {
	}

	@Override
	@Autowired
	public void setRuleSetDao(RuleSetDao ruleSetDao) {
		this.setDao(ruleSetDao);
	}

	@Override
	public List<RuleSet> findBySubject(Subject subjectSelected) throws CheckedException {
		Filter filter = new Filter();

		// Si la asignatura no es nula, cargamos el filtro y buscamos los materiales.
		if (subjectSelected != null) {
			filter = Filter.eq(RuleSet.Attributes.SUBJECT, subjectSelected);
		} else {
			filter = Filter.isNull(RuleSet.Attributes.SUBJECT);
		}

		// Cargamos solos los materiales activos.
		filter = Filter.and(filter, Filter.eq(RuleSet.Attributes.ACTIVE, true));

		return this.findByFilter(filter);
	}

	@Override
	public List<RuleSet> findAll() throws CheckedException {
		Filter filter = new Filter();

		// Cargamos solos los conjuntos activos.
		filter = Filter.and(filter, Filter.eq(RuleSet.Attributes.ACTIVE, true));

		return this.findByFilter(filter);
	}

	/**
	 * El borrado se realiza cambiando el estado del conjunto a inactivo.
	 * 
	 * @param entity
	 *            El conjunto que vamos a eliminar.
	 */
	@Override
	public void delete(RuleSet entity) throws CheckedException {
		// Cargamos el estado de inactivo y actualizamos.
		entity.setActive(false);
		this.update(entity);
	}

	/**
	 * El borrado se realiza cambiando el estado del material que corresponde con el id a inactivo.
	 * 
	 * @param id
	 *            El identificador del material que vamos a eliminar.
	 */
	@Override
	public void deleteById(Integer id) throws CheckedException {
		// Recuperamos el conjunto, lo marcamos como inactivo y lo actualizamos.
		RuleSet entity = this.findById(id);
		entity.setActive(false);
		this.update(entity);
	}
}
