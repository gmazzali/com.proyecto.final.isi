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
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

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
		static final String DESCRIPTION = "description";
		static final String RULES = "rules";
		static final String ACTIVE = "active";
	}

	/**
	 * La descripci�n del conjunto de las reglas.
	 */
	private String description;
	/**
	 * Las reglas que pertenecen a este conjunto.
	 */
	private List<Rule> rules;
	/**
	 * El valor booleano que nos determina el estado en el que se encuentra el conjunto de reglas.
	 */
	private Boolean active;

	/**
	 * El constructor por omisi�n.
	 */
	public RuleSet() {
		this.description = null;
		this.rules = new ArrayList<Rule>();
		this.active = false;
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Id
	@Column(name = "ID_RULE_SET")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La funci�n encargada de retornar la descripci�n del conjunto de reglas.
	 * 
	 * @return La descripci�n del conjunto de las reglas.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(255)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n que retorna el conjunto de las reglas que tenemos.
	 * 
	 * @return El conjunto de las reglas.
	 */
	@ManyToMany(cascade =
		{ CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "RULES_IN_SETS", joinColumns =
		{ @JoinColumn(name = "ID_RULE_SET") }, inverseJoinColumns =
		{ @JoinColumn(name = "ID_RULE") })
	public List<Rule> getRules() {
		return this.rules;
	}

	/**
	 * La funci�n que retorna el estado en el que se encuentra el conjunto de reglas.
	 * 
	 * @return El estado en el que se encuentra el conjunto de las reglas.
	 */
	@Column(name = "ACTIVE", columnDefinition = "boolean", nullable = false)
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * La funci�n que permite cargar la descripci�n del conjunto de reglas que tenemos.
	 * 
	 * @param description
	 *            La descripci�n del conjunto de reglas.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n que permite cargar el conjunto de las reglas que tenemos.
	 * 
	 * @param rules
	 *            El conjunto de las reglas que tenemos dentro.
	 */
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * La funci�n que carga el estado en el que se encuentra el conjunto de las reglas.
	 * 
	 * @param active
	 *            El valor booleano que le vamos a poner al conjunto de las reglas.
	 */
	public void setActive(Boolean active) {
		if (active != null) {
			this.active = active;
		}
	}
}