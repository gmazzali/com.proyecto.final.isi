package com.proyecto.service.rule;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.service.impl.GenericServiceImpl;
import com.proyecto.dao.rule.RuleDao;
import com.proyecto.model.rule.Rule;

/**
 * La clase que nos permite implementar la interfaz de los servicios de las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RuleServiceImpl extends GenericServiceImpl<Rule, Integer> implements RuleService {

	@Autowired
	public void setRuleDao(RuleDao ruleDao) {
		this.setDao(ruleDao);
	}

	public void validate(Rule entity) throws CheckedException {
	}
}