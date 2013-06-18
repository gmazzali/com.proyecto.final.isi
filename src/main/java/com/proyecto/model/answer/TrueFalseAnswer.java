package com.proyecto.model.answer;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una opción booleana, definiendo el mismo como Verdadero (TRUE) o como Falso (FALSE).
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class TrueFalseAnswer extends Answer<Boolean> {

	private static final long serialVersionUID = -6324391499398899382L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
	}
}
