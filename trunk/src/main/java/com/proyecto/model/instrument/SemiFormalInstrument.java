package com.proyecto.model.instrument;

/**
 * La clase que nos permite definir los instrumentos semiformales del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class SemiFormalInstrument extends Instrument {

	private static final long serialVersionUID = -2475200894637987347L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Instrument.Attributes {
	}
}
