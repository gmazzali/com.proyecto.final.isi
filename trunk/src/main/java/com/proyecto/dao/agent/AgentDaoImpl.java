package com.proyecto.dao.agent;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.model.agent.Agent;

/**
 * La clase que implementa en DAO de los agentes del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class AgentDaoImpl extends ProyectoDaoImpl<Agent, Integer> implements AgentDao {
}
