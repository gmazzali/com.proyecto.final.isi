package com.proyecto.service.rule;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.service.impl.GenericServiceImpl;
import com.proyecto.dao.rule.RuleSetDao;
import com.proyecto.model.rule.RuleSet;

/**
 * La clase que implementa la interfaz de los servicios de los conjuntos de las reglas dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RuleSetServiceImpl extends GenericServiceImpl<RuleSet, Integer> implements RuleSetService {

	public void validate(RuleSet entity) throws CheckedException {
	}

	@Autowired
	public void setRuleSetDao(RuleSetDao ruleSetDao) {
		this.setDao(ruleSetDao);
	}
}