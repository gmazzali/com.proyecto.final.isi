package com.proyecto.model.material.instrument;

import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que permite definir el instrumento de ensayo restringido que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "RESTRICTED_ESSAY_INSTRUMENTS")
@javax.persistence.Entity(name = "RestrictedEssayActivityInstrument")
public class RestrictedEssayActivityInstrument extends EssayActivityInstrument {

	private static final long serialVersionUID = -8910201315198314251L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends EssayActivityInstrument.Attributes {
	}

	/**
	 * Constructor por omisión.
	 */
	public RestrictedEssayActivityInstrument() {
		super();
	}
}