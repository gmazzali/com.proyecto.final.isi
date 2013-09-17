package com.proyecto.model.rule;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.agent.Subject;

/**
 * La clase que representa un conjunto de reglas que vamos a poder tener activo en un momento dado dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "RULES_SETS")
@javax.persistence.Entity(name = "RuleSet")
public class RuleSet extends Entity<Integer> {

	private static final long serialVersionUID = 5095579130173244421L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String SUBJECT = "subject";
		static final String DESCRIPTION = "description";
		static final String RULES = "rules";
		static final String ACTIVE = "active";
	}

	/**
	 * La materia a la que corresponde este conjunto de reglas.
	 */
	private Subject subject;
	/**
	 * La descripción del conjunto de las reglas.
	 */
	private String description;
	/**
	 * Las reglas que pertenecen a este conjunto.
	 */
	private List<Rule> rules;
	/**
	 * El valor booleano que nos determina si el conjunto de reglas esta activo o no.
	 */
	private Boolean active;

	/**
	 * El constructor por omisión.
	 */
	public RuleSet() {
		this.subject = null;
		this.description = null;
		this.rules = new ArrayList<Rule>();
		this.active = true;
	}

	@Override
	public String toString() {
		return this.description;
	}

	/**
	 * La función encargada de cargar una regla dentro del listado de reglas que tenemos dentro de este conjunto.
	 * 
	 * @param rule
	 *            La regla que vamos a cargar dentro de este conjunto.
	 */
	public void addRule(Rule rule) {
		if (this.rules != null && rule != null) {
			this.rules.add(rule);
		}
	}

	/**
	 * La función encargada de cargar una colección de regla dentro del listado de reglas que tenemos dentro de este conjunto.
	 * 
	 * @param rules
	 *            El listado de las reglas que vamos a cargar dentro de este conjunto.
	 */
	public void addAllRules(List<Rule> rules) {
		if (this.rules != null && rules != null) {
			this.rules.addAll(rules);
		}
	}

	/**
	 * La función encargada de remover una regla desde del listado de reglas que tenemos dentro de este conjunto.
	 * 
	 * @param rule
	 *            La regla que vamos a remover dentro de este conjunto.
	 */
	public void removeRule(Rule rule) {
		if (this.rules != null && rule != null) {
			this.rules.remove(rule);
		}
	}

	/**
	 * La función encargada de remover una colección de regla desde del listado de reglas que tenemos dentro de este conjunto.
	 * 
	 * @param rules
	 *            El listado de las reglas que vamos a remover desde de este conjunto.
	 */
	public void removeAllRules(List<Rule> rules) {
		if (this.rules != null && rules != null) {
			this.rules.removeAll(rules);
		}
	}

	/**
	 * La función encargada de vaciar el contenido del listado de las reglas que tenemos dentro de este conjunto.
	 */
	public void clearRules() {
		if (this.rules != null && !this.rules.isEmpty()) {
			this.rules.clear();
		}
	}

	@Id
	@Column(name = "ID_RULE_SET")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función que retorna la materia a la que pertenece este conjunto.
	 * 
	 * @return La materia a la que pertenece este conjunto.
	 */
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Subject.class)
	@JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID_SUBJECT", insertable = true, updatable = true, nullable = true)
	public Subject getSubject() {
		return this.subject;
	}

	/**
	 * La función encargada de retornar la descripción del conjunto de reglas.
	 * 
	 * @return La descripción del conjunto de las reglas.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(255)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función que retorna el conjunto de las reglas que tenemos.
	 * 
	 * @return El conjunto de las reglas.
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "RULES_IN_SETS", joinColumns = { @JoinColumn(name = "ID_RULE_SET") }, inverseJoinColumns = { @JoinColumn(name = "ID_RULE") })
	public List<Rule> getRules() {
		return this.rules;
	}

	/**
	 * La función que retorna si un conjunto de reglas esta activo dentro del sistema o no.
	 * 
	 * @return El estado que determina si un conjunto de reglas esta activo dentro del sistema o no.
	 */
	@Column(name = "ACTIVE", columnDefinition = "boolean", nullable = false)
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * La función encargada de cargar la materia a la que pertenece este elemento.
	 * 
	 * @param subject
	 *            La materia a la que pertenece este elemento.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * La función que permite cargar la descripción del conjunto de reglas que tenemos.
	 * 
	 * @param description
	 *            La descripción del conjunto de reglas.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La función que permite cargar el conjunto de las reglas que tenemos.
	 * 
	 * @param rules
	 *            El conjunto de las reglas que tenemos dentro.
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * La función que carga el estado que determina si un conjunto de reglas esta activo dentro del sistema o no.
	 * 
	 * @param active
	 *            El estado que determina si un conjunto de reglas esta activo dentro del sistema o no.
	 */
	public void setActive(Boolean active) {
		if (active != null) {
			this.active = active;
		}
	}
}