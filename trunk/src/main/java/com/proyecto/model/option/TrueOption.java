package com.proyecto.model.option;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.TrueFalseAnswer;

/**
 * La clase que representa una opción verdadera.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "TRUE_OPTIONS")
@javax.persistence.Entity(name = "TrueOption")
public class TrueOption extends Option {

	private static final long serialVersionUID = -3583657987137329525L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Option.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public TrueOption() {
		super(TrueFalseAnswer.TRUE);
	}
}
