package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos de selección, ya sean multiples o simples.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ChoiceInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = 2343373861916155108L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
	}
}
