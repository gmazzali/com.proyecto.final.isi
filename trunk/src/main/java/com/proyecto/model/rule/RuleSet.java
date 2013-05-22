package com.proyecto.model.rule;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.common.util.model.Entity;

/**
 * La clase que representa un conjunto de reglas que vamos a poder tener activo en un momento dado dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@javax.persistence.Entity(name = "RuleSet")
@Table(name = "RULES_SETS")
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
	 * La descripción del conjunto de las reglas.
	 */
	private String description;
	/**
	 * Las reglas que pertenecen a este conjunto.
	 */
	private Set<Rule> rules;
	/**
	 * El valor booleano que nos determina el estado en el que se encuentra el conjunto de reglas.
	 */
	private Boolean active;

	/**
	 * El constructor por omisión.
	 */
	public RuleSet() {
	}

	/**
	 * La función encargada de retornar el id de un conjunto de reglas.
	 * 
	 * @return El identificador de un conjunto de reglas.
	 */
	@Id
	@Column(name = "ID_RULE_SET")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función encargada de retornar la descripción del conjunto de reglas.
	 * 
	 * @return La descripción del conjunto de las reglas.
	 */
	@Column(name = "DESCRIPTION",
			columnDefinition = "varchar(100)",
			nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función que retorna el conjunto de las reglas que tenemos.
	 * 
	 * @return El conjunto de las reglas.
	 */
	@ManyToMany
	@JoinTable(name = "rules_in_sets",
			joinColumns =
				{ @JoinColumn(name = "id_rule_set") },
			inverseJoinColumns =
				{ @JoinColumn(name = "id_rule") })
	public Set<Rule> getRules() {
		return this.rules;
	}

	/**
	 * La función que retorna el estado en el que se encuentra el conjunto de reglas.
	 * 
	 * @return El estado en el que se encuentra el conjunto de las reglas.
	 */
	@Column(name = "ACTIVE",
			columnDefinition = "bit",
			nullable = false)
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * La función que permite setear la descripción del conjunto de reglas que tenemos.
	 * 
	 * @param description
	 *            La descripción del conjunto de reglas.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La función que permite setear el conjunto de las reglas que tenemos.
	 * 
	 * @param rules
	 *            El conjunto de las reglas que tenemos dentro.
	 */
	public void setRules(Set<Rule> rules) {
		this.rules = rules;
	}

	/**
	 * La función que setea el estado en el que se encuentra el conjunto de las reglas.
	 * 
	 * @param active
	 *            El valor booleano que le vamos a poner al conjunto de las reglas.
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
}