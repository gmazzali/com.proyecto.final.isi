package com.proyecto.model.material.instrument;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.EssayActivityAnswer;

/**
 * La clase que permite definir el instrumento formal de actividad para un ensayo que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "ESSAY_INSTRUMENTS")
@javax.persistence.Entity(name = "EssayActivityInstrument")
public abstract class EssayActivityInstrument extends FormalInstrument {

	private static final long serialVersionUID = 9169751255700936135L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends FormalInstrument.Attributes {
		static final String ANSWER = "answer";
	}

	/**
	 * La respuesta del ensayo.
	 */
	protected EssayActivityAnswer answer;

	/**
	 * El constructor por omisión.
	 */
	public EssayActivityInstrument() {
		super();
	}

	/**
	 * La función que retorna la respuesta asociada a este instrumento.
	 * 
	 * @return La respuesta asociada a este instrumento.
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instrument", targetEntity = EssayActivityAnswer.class, orphanRemoval = true)
	public EssayActivityAnswer getAnswer() {
		return this.answer;
	}

	/**
	 * La función encargada de cargar la respuesta asociada a este instrumento.
	 * 
	 * @param answer
	 *            La respuesta asociada a este instrumento.
	 */
	public void setAnswer(EssayActivityAnswer answer) {
		this.answer = answer;
		if (this.answer != null) {
			this.answer.setInstrument(this);
		}
	}
}