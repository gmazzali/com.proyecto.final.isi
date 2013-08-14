package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento sin restricción de ensayo que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "UNRESTRICTED_ESSAY_INSTRUMENTS")
@javax.persistence.Entity(name = "UnrestrictedEssayActivityInstrument")
public class UnrestrictedEssayActivityInstrument extends EssayActivityInstrument {

	private static final long serialVersionUID = -3446922984939841794L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends EssayActivityInstrument.Attributes {
	}

	/**
	 * Constructor por omisión.
	 */
	public UnrestrictedEssayActivityInstrument() {
		super();
	}
}