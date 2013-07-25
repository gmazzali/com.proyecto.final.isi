package com.proyecto.model.instrument;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.option.Option;

/**
 * La clase que permite definir el instrumento formal de actividad para un ensayo que vamos a ocupar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */

@Model
@Table(name = "ESSAYACTIVITY_INSTRUMENTS")
@javax.persistence.Entity(name = "EssayActivityInstrument")
public abstract class EssayActivityInstrument extends FormalInstrument {

	private static final long serialVersionUID = 9169751255700936135L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends FormalInstrument.Attributes {
		static final String ANSWER = "answer";
	}
	
	protected String essayActivityAnswer;
	
	/**
	 * El constructor por omisión.
	 */
	public EssayActivityInstrument() {
		super();
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "instrument", targetEntity = EssayActivityAnswer.class, orphanRemoval = true)
	public String getEssayActivityAnswer() {
		return essayActivityAnswer;
	}

	public void setEssayActivityAnswer(String essayActivityAnswer) {
		this.essayActivityAnswer = essayActivityAnswer;
	}
	
}
