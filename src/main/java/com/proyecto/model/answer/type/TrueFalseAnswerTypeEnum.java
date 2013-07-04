package com.proyecto.model.answer.type;

import com.proyecto.model.answer.TrueFalseAnswer;

/**
 * La enumeraci�n que contiene solo los 2 tipos de respuesta que podemos crear en este tipo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum TrueFalseAnswerTypeEnum {

	TRUE("Verdadero"), FALSE("Falso");

	/**
	 * La descripci�n.
	 */
	private String description;

	/**
	 * El constructor de un tipo de respuesta booleana.
	 * 
	 * @param description
	 *            La descripci�n.
	 */
	private TrueFalseAnswerTypeEnum(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.description;
	}

	/**
	 * La funci�n encargada de retornar el tipo de respuesta booleana dado el tipo seleccionado.
	 * 
	 * @return La respuesta booleana dado el tipo seleccionado.
	 */
	public TrueFalseAnswer getAnswer() {
		switch (this) {
			case TRUE:
				return new TrueFalseAnswer(true);

			case FALSE:
				return new TrueFalseAnswer(false);

			default:
				return null;
		}
	}
}