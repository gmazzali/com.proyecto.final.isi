package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.option.Option;

/**
 * La clase que nos permite definir una opción booleana, definiendo el mismo como Verdadero (TRUE) o como Falso (FALSE).
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "TRUE_FALSE_ANSWERS")
@javax.persistence.Entity(name = "TrueFalseAnswer")
public class TrueFalseAnswer extends Answer {

	private static final long serialVersionUID = -6324391499398899382L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String VALUE = "value";
		static final String OPTION = "option";
	}

	/**
	 * El valor booleano de la respuesta.
	 */
	private Boolean value;
	/**
	 * La opción a la que corresponde esta respuesta.
	 */
	private Option option;

	/**
	 * El constructor por copia de valor.
	 * 
	 * @param bool
	 *            El valor booleano que vamos a usar dentro de la respuesta.
	 */
	public TrueFalseAnswer(Boolean bool) {
		this.value = bool;
		this.option = null;
	}

	/**
	 * Constructor por omisión.
	 */
	public TrueFalseAnswer() {
		this(null);
	}

	@Override
	public String toString() {
		return this.value == null ? "null" : this.value.booleanValue() ? "T" : "F";
	}

	/**
	 * La función encargada de retornar el valor booleano de la respuesta.
	 * 
	 * @return El valor booleano de la respuesta.
	 */
	@Column(name = "VALUE", columnDefinition = "bool", nullable = false)
	public Boolean getValue() {
		return this.value;
	}

	/**
	 * La función encargada de retornar la opción que corresponde con esta respuesta.
	 * 
	 * @return La opción que corresponde con esta respuesta.
	 */
	@OneToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = Option.class, optional = false)
	@JoinColumn(name = "ID_OPTION", referencedColumnName = "ID_OPTION", insertable = true, updatable = true, nullable = false)
	public Option getOption() {
		return this.option;
	}

	/**
	 * La función encargada de cargar el valor booleano de la respuesta.
	 * 
	 * @param value
	 *            El valor booleano de la respuesta.
	 */
	public void setValue(Boolean value) {
		this.value = value;
	}

	/**
	 * La función encargada de cargar la opción que corresponde con esta respuesta.
	 * 
	 * @param value
	 *            La opción que corresponde con esta respuesta.
	 */
	public void setOption(Option option) {
		this.option = option;
	}
}
