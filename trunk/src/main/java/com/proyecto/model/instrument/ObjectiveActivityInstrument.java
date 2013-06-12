package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un instrumento formal para una actividad objetiva.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ObjectiveActivityInstrument extends FormalInstrument {

	private static final long serialVersionUID = -831835089600767398L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends FormalInstrument.Attributes {
	}
}
