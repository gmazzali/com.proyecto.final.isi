package com.proyecto.model.agent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un aprendiz, un tomador de examen o un creador de exámenes.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "AGENTS")
@javax.persistence.Entity(name = "Agent")
public class Agent extends Entity<Integer> {

	private static final long serialVersionUID = -7071604791620647704L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String NAME = "name";
		static final String PASSWORD = "password";
	}

	/**
	 * El nombre de usuario del agente.
	 */
	private String name;
	/**
	 * El password del agente.
	 */
	private String password;
	/**
	 * El listado de las materias a las que esta anotado el agente.
	 */
	private List<Subject> subjects;

	/**
	 * El constructor por omisión.
	 */
	public Agent() {
		this.name = "";
		this.password = "";
		this.subjects = new ArrayList<>();
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * La función encargada de retornar el id de un agente.
	 * 
	 * @return El identificador de un agente.
	 */
	@Id
	@Column(name = "ID_AGENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función que retorna el nombre de usuario que va a utilizar el agente.
	 * 
	 * @return El nombre de usuario del agente.
	 */
	@Column(name = "NAME", columnDefinition = "varchar(50)", nullable = false)
	public String getName() {
		return this.name;
	}

	/**
	 * La función encargada de retornar el password del agente.
	 * 
	 * @return El password del agente.
	 */
	@Column(name = "PASSWORD", columnDefinition = "varchar(50)", nullable = false)
	public String getPassword() {
		return this.password;
	}

	/**
	 * La función encargada de retornar el listado de las materias a las que esta inscrito el agente.
	 * 
	 * @return El listado de las materias a las que esta inscrita el agente.
	 */
	@ManyToMany(cascade =
		{ CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "AGENTS_SUBJECTS", joinColumns =
		{ @JoinColumn(name = "ID_AGENT") }, inverseJoinColumns =
		{ @JoinColumn(name = "ID_SUBJECT") })
	public List<Subject> getSubjects() {
		return this.subjects;
	}

	/**
	 * La función encargada de cargar el nombre de usuario que va a tener este agente.
	 * 
	 * @param name
	 *            El nombre de usuario de este agente.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * La función encargada de cargar el password al agente.
	 * 
	 * @param password
	 *            El password que vamos a cargar.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * La función encargada de cargar el listado de las materias que corresponde a este agente.
	 * 
	 * @param subjects
	 *            El listado de materias que corresponde con este agente.
	 */
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
