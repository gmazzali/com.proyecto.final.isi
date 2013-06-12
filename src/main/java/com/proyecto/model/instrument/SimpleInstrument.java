package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos semiformales simples del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class SimpleInstrument extends SemiFormalInstrument {

	private static final long serialVersionUID = 97542763860294853L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends SemiFormalInstrument.Attributes {
	}
}
