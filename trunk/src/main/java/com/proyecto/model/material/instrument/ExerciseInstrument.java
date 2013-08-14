package com.proyecto.model.material.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos semiformales simples como ejercicios del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ExerciseInstrument extends SimpleInstrument {

	private static final long serialVersionUID = 97542763860294853L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends SimpleInstrument.Attributes {
	}
}
