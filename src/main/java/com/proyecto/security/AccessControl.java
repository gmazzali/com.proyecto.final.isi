package com.proyecto.security;

import com.common.util.annotations.Service;
import com.proyecto.model.agent.Agent;

/**
 * La clase que nos permite controlar el acceso a las diferentes partes de una aplicación de acuerdo al agente logueado al mismo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AccessControl {

	/**
	 * El agente que ingreso correctamente dentro del sistema.
	 */
	private Agent agentLogged;

	public AccessControl() {
		this.agentLogged = null;
	}

	/**
	 * La función encargada de retornar el agente que tenemos logueado dentro del sistema.
	 * 
	 * @return El agente que esta logueado dentro del sistema.
	 */
	public Agent getAgentLogged() {
		return this.agentLogged;
	}

	/**
	 * La función encargada de cargar el usuario que acaba de loguearse dentro del sistema.
	 * 
	 * @param agentLogged
	 *            El agente que se logueo dentro del sistema.
	 */
	public void setAgentLogged(Agent agentLogged) {
		this.agentLogged = agentLogged;
	}
}
