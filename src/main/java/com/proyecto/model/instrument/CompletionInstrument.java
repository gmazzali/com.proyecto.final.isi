package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento que permite completar una palabra en una frase.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CompletionInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = -3676039584247522076L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
	}
}
