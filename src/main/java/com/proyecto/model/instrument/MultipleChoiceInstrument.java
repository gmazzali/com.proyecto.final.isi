package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos de selección multiple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class MultipleChoiceInstrument extends ChoiceInstrument {

	private static final long serialVersionUID = 3919655075898550736L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ChoiceInstrument.Attributes {
	}
}