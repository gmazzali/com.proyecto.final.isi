package com.proyecto.model.material.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos semiformales compuestos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class CompositeInstrument extends SemiFormalInstrument {

	private static final long serialVersionUID = 3427866462468736247L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends SemiFormalInstrument.Attributes {
	}
}