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

	/**
	 * La función encargada de recuperar un agente dado su nombre de logueo.
	 * 
	 * @param agentName
	 *            El nombre del agente que queremos recuperar.
	 * @return El agente que corresponde con el nombre que recibimos, en caso de que no se encuentre retornamos un objeto NULL.
	 */
	public Agent findByName(String agentName);
}
