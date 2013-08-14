package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir los instrumentos de selección multiple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "MULTIPLE_CHOICE_INSTRUMENTS")
@javax.persistence.Entity(name = "MultipleChoiceInstrument")
public class MultipleChoiceInstrument extends ChoiceInstrument {

	private static final long serialVersionUID = 3919655075898550736L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ChoiceInstrument.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public MultipleChoiceInstrument() {
		super();
	}
}