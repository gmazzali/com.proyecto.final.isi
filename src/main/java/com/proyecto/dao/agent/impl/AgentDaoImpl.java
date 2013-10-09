package com.proyecto.dao.agent.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.agent.AgentDao;
import com.proyecto.model.agent.Agent;

/**
 * La clase que implementa en DAO de los agentes del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class AgentDaoImpl extends ProyectoDaoImpl<Agent, Integer> implements AgentDao {

	private static final long serialVersionUID = 2698663283837160878L;
}
