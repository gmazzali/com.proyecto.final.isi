package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento formal de actividad para un ensayo que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class EssayActivityInstrument extends FormalInstrument {

	private static final long serialVersionUID = 9169751255700936135L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends FormalInstrument.Attributes {
	}
}
