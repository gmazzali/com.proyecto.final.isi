package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
		static final String WORD = "word";
	}

	/**
	 * La palabra que vamos a utilizar para completar.
	 */
	private String word;
	/**
	 * El instrumento al que corresponde esta respuesta.
	 */
	private CompletionInstrument completionInstrument;

	/**
	 * El constructor por omisión.
	 */
	public CompletionAnswer() {
		super();
		this.word = null;
		this.completionInstrument = null;
	}

	@Override
	public String toString() {
		return this.word;
	}

	/**
	 * La función encargada de retornar la palabra que vamos a ocupar para completar la frase incompleta.
	 * 
	 * @return La palabra que vamos a ocupar para completar la frase incompleta.
	 */
	@Column(name = "WORD", columnDefinition = "varchar(255)", nullable = true)
	public String getWord() {
		return this.word;
	}

	/**
	 * La función encargada de retornar el instrumento al que corresponde esta respuesta.
	 * 
	 * @return El instrumento que corresponde con esta respuesta.
	 */
	@OneToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = CompletionInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public CompletionInstrument getCompletionInstrument() {
		return this.completionInstrument;
	}

	/**
	 * La función encargada de cargar la palabra que vamos a ocupar para completar la frase incompleta.
	 * 
	 * @param word
	 *            La palabra que vamos a ocupar para completar la frase incompleta.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * La función encargada de cargar el instrumento que corresponde con esta respuesta.
	 * 
	 * @param completionInstrument
	 *            El instrumento que corresponde con esta respuesta.
	 */
	public void setCompletionInstrument(CompletionInstrument completionInstrument) {
		this.completionInstrument = completionInstrument;
	}
}
