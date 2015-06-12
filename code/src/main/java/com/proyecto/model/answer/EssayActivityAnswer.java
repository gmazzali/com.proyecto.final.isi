package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.instrument.EssayActivityInstrument;

/**
 * La clase que nos representa una respuesta asociada a un instrumento de ensayo formal dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Model
@Table(name = "ESSAY_ANSWERS")
@javax.persistence.Entity(name = "EssayActivityAnswer")
public class EssayActivityAnswer extends Answer {

	private static final long serialVersionUID = 2732402132346858133L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String ANSWER = "answer";
	}

	/**
	 * La respuesta en si misma.
	 */
	protected String answer;
	/**
	 * El instrumento al que corresponde esta respuesta.
	 */
	protected EssayActivityInstrument instrument;

	/**
	 * el constructor por omisión.
	 */
	public EssayActivityAnswer() {
		super();
	}

	@Override
	public String toString() {
		return this.answer;
	}

	/**
	 * La función encargada de retornar el texto de la respuesta.
	 * 
	 * @return El texto de la respuesta.
	 */
	@Column(name = "ANSWER", columnDefinition = "text", nullable = false)
	public String getAnswer() {
		return this.answer;
	}

	/**
	 * La función encargada de retornar el instrumento que corresponde con esta respuesta.
	 * 
	 * @return El instrumento que corresponde con esta respuesta.
	 */
	@OneToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = EssayActivityInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public EssayActivityInstrument getInstrument() {
		return this.instrument;
	}

	/**
	 * La función encargada de cargar el texto de la respuesta.
	 * 
	 * @param answer
	 *            El texto de la respuesta.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * La función encargada de cargar el instrumento al que corresponde esta respuesta.
	 * 
	 * @param instrument
	 *            El instrumento al que corresponde esta respuesta.
	 */
	public void setInstrument(EssayActivityInstrument instrument) {
		this.instrument = instrument;
	}
}