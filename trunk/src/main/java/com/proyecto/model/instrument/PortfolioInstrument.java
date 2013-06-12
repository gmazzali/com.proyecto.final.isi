package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos semiformales compuestos de portfolio del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class PortfolioInstrument extends CompositeInstrument {

	private static final long serialVersionUID = -1469946214054479585L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends CompositeInstrument.Attributes {
	}
}