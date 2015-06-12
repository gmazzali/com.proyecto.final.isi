package com.proyecto.model.material.instrument;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.proyecto.model.material.instrument.type.impl.InstrumentTypeImpl;

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
	 * El constructor por omisión.
	 */
	public SemiFormalInstrument() {
		super();
	}

	@Override
	@Transient
	public InstrumentTypeImpl getInstrumentType() {
		return InstrumentTypeImpl.SEMIFORMAL;
	}
}
