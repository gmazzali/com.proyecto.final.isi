package com.proyecto.service.rule.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.rule.RuleDao;
import com.proyecto.model.rule.Rule;
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
}