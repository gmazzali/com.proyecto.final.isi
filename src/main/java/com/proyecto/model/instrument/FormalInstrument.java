package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un instrumento formal dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class FormalInstrument extends Instrument {

	private static final long serialVersionUID = 828654143742457389L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Instrument.Attributes {
	}
}
