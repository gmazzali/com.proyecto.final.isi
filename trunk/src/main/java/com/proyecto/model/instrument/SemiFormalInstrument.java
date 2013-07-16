package com.proyecto.model.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;

/**
 * La clase que nos permite definir los instrumentos semiformales del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "SEMIFORMAL_INSTRUMENTS")
@javax.persistence.Entity(name = "SemiFormalInstrument")
public abstract class SemiFormalInstrument extends Instrument {

	private static final long serialVersionUID = -2475200894637987347L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Instrument.Attributes {
	}

	/**
	 * El constructor por omisi�n.
	 */
	public SemiFormalInstrument() {
		super();
	}
}
