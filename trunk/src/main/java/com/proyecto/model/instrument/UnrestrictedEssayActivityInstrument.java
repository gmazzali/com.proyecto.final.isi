package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento sin restricción de ensayo que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class UnrestrictedEssayActivityInstrument extends EssayActivityInstrument {

	private static final long serialVersionUID = -3446922984939841794L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends EssayActivityInstrument.Attributes {
	}

}
