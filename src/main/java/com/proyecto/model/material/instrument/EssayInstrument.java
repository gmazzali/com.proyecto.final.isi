package com.proyecto.model.material.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos semiformales simples como ensayos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EssayInstrument extends SimpleInstrument {

	private static final long serialVersionUID = 3086645405389560462L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends SimpleInstrument.Attributes {
	}
}