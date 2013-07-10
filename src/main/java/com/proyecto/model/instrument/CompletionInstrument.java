package com.proyecto.model.instrument;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.CompletionAnswer;

/**
 * La clase que permite definir el instrumento que permite completar una palabra en una frase.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "COMPLETION_INSTRUMENTS")
@javax.persistence.Entity(name = "CompletionInstrument")
public class CompletionInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = -3676039584247522076L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
	}

	/**
	 * La respuesta a la palabra que falta en el instrumento.
	 */
	private CompletionAnswer answer;

	/**
	 * El constructor por omisión de un instrumento para completar.
	 */
	public CompletionInstrument() {
		super();
		this.answer = null;
	}

	/**
	 * La función encargada de retornar la respuesta que corresponde con este instrumento.
	 * 
	 * @return La respuesta que corresponde con este instrumento.
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "completionInstrument", targetEntity = CompletionAnswer.class, orphanRemoval = true)
	public CompletionAnswer getAnswer() {
		return this.answer;
	}

	/**
	 * La función encargada de cargar la respuesta que corresponde con este instrumento.
	 * 
	 * @param answer
	 *            La respuesta que corresponde con este instrumento.
	 */
	public void setAnswer(CompletionAnswer answer) {
		this.answer = answer;
	}
}
