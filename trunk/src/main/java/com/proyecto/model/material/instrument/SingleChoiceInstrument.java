package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos de selección simples.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "SINGLE_CHOICE_INSTRUMENTS")
@javax.persistence.Entity(name = "SingleChoiceInstrument")
public class SingleChoiceInstrument extends ChoiceInstrument {

	private static final long serialVersionUID = -1297368159158004458L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ChoiceInstrument.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public SingleChoiceInstrument() {
		super();
	}
}