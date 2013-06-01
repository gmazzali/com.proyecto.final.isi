package com.proyecto.service.agent;

import com.proyecto.dao.agent.AgentDao;
import com.proyecto.model.agent.Agent;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios que vamos a ofrecer a los agentes que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AgentService extends ProyectoService<Agent, Integer> {

	/**
	 * La función encargada de cargar el DAO para el servicio de agentes.
	 * 
	 * @param agentDao
	 *            El DAO para los servicios de los agentes.
	 */
	public void setAgentDao(AgentDao agentDao);
}
