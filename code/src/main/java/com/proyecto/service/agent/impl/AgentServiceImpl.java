package com.proyecto.service.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.model.query.filter.Filter;
import com.proyecto.dao.agent.AgentDao;
import com.proyecto.model.agent.Agent;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.agent.AgentService;

/**
 * La clase que implementa los servicios para los agentes que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AgentServiceImpl extends ProyectoServiceImpl<Agent, Integer> implements AgentService {

	@Override
	@Autowired
	public void setAgentDao(AgentDao agentDao) {
		this.setDao(agentDao);
	}

	@Override
	public void validate(Agent entity) throws CheckedException {
	}

	@Override
	public Agent findByName(String agentName) {
		Agent agent = null;
		try {
			Filter filter = Filter.like(Agent.Attributes.NAME, agentName);
			agent = this.dao.findByFilter(filter).get(0);
		} catch (Exception ex) {
		}
		return agent;
	}
}
