package com.proyecto.model.answer;

import javax.persistence.Column;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

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
	}

	/**
	 * El valor definido para una opción de respuesta verdadera.
	 */
	public final static TrueFalseAnswer TRUE = new TrueFalseAnswer(1, true);
	/**
	 * El valor definido para una opción de respuesta falsa.
	 */
	public final static TrueFalseAnswer FALSE = new TrueFalseAnswer(2, false);

	/**
	 * El valor booleano de la respuesta.
	 */
	private Boolean value;

	/**
	 * El constructor por copia de valor.
	 * 
	 * @param id
	 *            El identificador de la respuesta.
	 * @param bool
	 *            El valor booleano que vamos a usar dentro de la respuesta.
	 */
	public TrueFalseAnswer(Integer id, Boolean bool) {
		this.value = bool;
	}

	/**
	 * El constructor por copia de valor.
	 * 
	 * @param bool
	 *            El valor booleano que vamos a usar dentro de la respuesta.
	 */
	public TrueFalseAnswer(Boolean bool) {
		this(null, bool);
	}

	/**
	 * Constructor por omisión.
	 */
	public TrueFalseAnswer() {
		this.value = null;
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
	 * La función encargada de cargar el valor booleano de la respuesta.
	 * 
	 * @param value
	 *            El valor booleano de la respuesta.
	 */
	public void setValue(Boolean value) {
		this.value = value;
	}
}
