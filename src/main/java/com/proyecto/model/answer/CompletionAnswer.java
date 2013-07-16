package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.instrument.CompletionInstrument;

/**
 * La clase que nos permite definir una respuesta para completar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "COMPLETION_ANSWERS")
@javax.persistence.Entity(name = "CompletionAnswer")
public class CompletionAnswer extends Answer {

	private static final long serialVersionUID = -6324391499398899382L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String PHRASE = "phrase";
		static final String INDEX = "index";
		static final String INSTRUMENT = "instrument";
	}

	/**
	 * La palabra que vamos a utilizar para completar.
	 */
	private String phrase;
	/**
	 * El índice que corresponde a esta frase con el instrumento.
	 */
	private Integer index;
	/**
	 * El instrumento al que corresponde esta respuesta.
	 */
	private CompletionInstrument instrument;

	/**
	 * El constructor por omisión.
	 */
	public CompletionAnswer() {
		super();
		this.phrase = null;
		this.index = 0;
		this.instrument = null;
	}

	@Override
	public String toString() {
		return this.index + " - " + this.phrase;
	}

	/**
	 * La función encargada de retornar la frase que vamos a ocupar para completar la frase incompleta.
	 * 
	 * @return La frase que vamos a ocupar para completar la frase incompleta.
	 */
	@Column(name = "PHRASE", columnDefinition = "varchar(255)", nullable = false)
	public String getPhrase() {
		return this.phrase;
	}

	/**
	 * La función encargada de retornar el índice que corresponde con la frase de la respuesta dentro del instrumento.
	 * 
	 * @return El índice que corresponde con la frase de la respuesta dentro del instrumento.
	 */
	@Column(name = "PHRASE_INDEX", columnDefinition = "integer", nullable = false)
	public Integer getIndex() {
		return this.index;
	}

	/**
	 * La función encargada de retornar el instrumento al que corresponde esta respuesta.
	 * 
	 * @return El instrumento que corresponde con esta respuesta.
	 */
	@ManyToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = CompletionInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public CompletionInstrument getInstrument() {
		return this.instrument;
	}

	/**
	 * La función encargada de cargar la frase que vamos a ocupar para completar la frase incompleta.
	 * 
	 * @param phrase
	 *            La frase que vamos a ocupar para completar la frase incompleta.
	 */
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}

	/**
	 * La función encargada de cargar el índice que corresponde con la frase de la respuesta dentro del instrumento.
	 * 
	 * @param index
	 *            El índice que corresponde con la frase de la respuesta dentro del instrumento.
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * La función encargada de cargar el instrumento que corresponde con esta respuesta.
	 * 
	 * @param instrument
	 *            El instrumento que corresponde con esta respuesta.
	 */
	public void setInstrument(CompletionInstrument instrument) {
		this.instrument = instrument;
	}
}
