package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento de ensayo restringido que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class RestrictedEssayActivityInstrument extends EssayActivityInstrument {

	private static final long serialVersionUID = -8910201315198314251L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends EssayActivityInstrument.Attributes {
	}
}
