package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir un instrumento formal para una actividad objetiva.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "OBJECTIVE_INSTRUMENTS")
@javax.persistence.Entity(name = "ObjectiveActivityInstrument")
public abstract class ObjectiveActivityInstrument extends FormalInstrument {

	private static final long serialVersionUID = -831835089600767398L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends FormalInstrument.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public ObjectiveActivityInstrument() {
		super();
	}
}
