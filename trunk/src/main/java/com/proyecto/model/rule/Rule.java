package com.proyecto.model.rule;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.common.util.model.Entity;

/**
 * La clase que define una regla dentro del sistema y que va a aplicarse dentro de la ontolog�a para control de validaci�n de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@javax.persistence.Entity(name = "Rule")
@Table(name = "RULES")
public class Rule extends Entity<Integer> {

	private static final long serialVersionUID = -7668405363416480227L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String DESCRIPTION = "description";
		static final String RULE = "rule";
		static final String ACTIVE = "active";
	}

	/**
	 * La descripci�n de la funci�n que cumple la regla.
	 */
	private String description;
	/**
	 * La regla en si misma.
	 */
	private String rule;

	/**
	 * Constructor por default de una regla.
	 */
	public Rule() {
		this.description = null;
		this.rule = null;
	}

	@Override
	public String toString() {
		return this.description;
	}

	/**
	 * La funci�n que retorna el ID de la regla.
	 * 
	 * @return El identificador de la regla.
	 */
	@Id
	@Column(name = "ID_RULE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La funci�n que retorna la descripci�n de la regla.
	 * 
	 * @return La descripci�n de la regla.
	 */
	@Column(name = "DESCRIPTION",
			columnDefinition = "varchar(100)",
			nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n que retorna la regla en si misma.
	 * 
	 * @return La regla en si misma.
	 */
	@Column(name = "RULE",
			columnDefinition = "text",
			nullable = false)
	public String getRule() {
		return this.rule;
	}

	/**
	 * La funci�n que setea la descripci�n de la regla.
	 * 
	 * @param description
	 *            La descripci�n de la regla.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n que setea la regla en si misma.
	 * 
	 * @param rule
	 *            La regla en si misma.
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
}
