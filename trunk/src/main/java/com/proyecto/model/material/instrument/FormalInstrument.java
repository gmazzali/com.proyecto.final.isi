package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un instrumento formal dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "FORMAL_INSTRUMENTS")
@javax.persistence.Entity(name = "FormalInstrument")
public abstract class FormalInstrument extends Instrument {

	private static final long serialVersionUID = 828654143742457389L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Instrument.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public FormalInstrument() {
		super();
	}
}
