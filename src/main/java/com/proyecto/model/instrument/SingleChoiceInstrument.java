package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos de selección simples.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SingleChoiceInstrument extends ChoiceInstrument {

	private static final long serialVersionUID = -1297368159158004458L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ChoiceInstrument.Attributes {
	}
}