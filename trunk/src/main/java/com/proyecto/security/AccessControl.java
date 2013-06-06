package com.proyecto.security;

import com.common.util.annotations.Service;
import com.proyecto.model.agent.Agent;
import com.proyecto.model.agent.Subject;

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
	/**
	 * La materia a la que el agente ingresa.
	 */
	private Subject subjectSelected;

	/**
	 * La función encargada de retornar el agente que tenemos logueado dentro del sistema.
	 * 
	 * @return El agente que esta logueado dentro del sistema.
	 */
	public Agent getAgentLogged() {
		return this.agentLogged;
	}

	/**
	 * La función encargada de retornar la materia a la que ingresa el agente.
	 * 
	 * @return La materia a la que ingresa el agente.
	 */
	public Subject getSubjectSelected() {
		return this.subjectSelected;
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

	/**
	 * La función encargada de cargar la materia a la que ingreso el agente.
	 * 
	 * @param subjectSelected
	 *            La materia seleccionada.
	 */
	public void setSubjectSelected(Subject subjectSelected) {
		this.subjectSelected = subjectSelected;
	}
}
