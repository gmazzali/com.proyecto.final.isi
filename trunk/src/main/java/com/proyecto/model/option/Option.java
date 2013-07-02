package com.proyecto.model.option;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.model.instrument.ChoiceInstrument;

/**
 * La clase que representa una opción en el instrumento de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "OPTIONS")
@javax.persistence.Entity(name = "Option")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Option extends Entity<Integer> {

	private static final long serialVersionUID = -5744022617325139662L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String DESCRIPTION = "description";
		static final String TRUE_FALSE_ANSWER = "trueFalseAnswer";
		static final String CHOICE_INSTRUMENT = "choiceInstrument";
	}

	/**
	 * La descripción de la opción.
	 */
	private String description;
	/**
	 * El valor de la respuesta.
	 */
	private TrueFalseAnswer trueFalseAnswer;
	/**
	 * El instrumento al que pertenece esta opción.
	 */
	private ChoiceInstrument choiceInstrument;

	/**
	 * El constructor que recibe el tipo de respuesta que vamos a crear con este constructor.
	 * 
	 * @param trueFalseAnswer
	 *            El valor de la respuesta de esta opción.
	 */
	public Option(TrueFalseAnswer trueFalseAnswer) {
		this.description = "";
		this.trueFalseAnswer = trueFalseAnswer;
		this.choiceInstrument = null;
	}

	@Override
	public String toString() {
		return "(" + this.trueFalseAnswer.toString() + ") - " + this.description;
	}

	@Id
	@Override
	@Column(name = "ID_OPTION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función encargada de retornar la descripción de la opción.
	 * 
	 * @return La descripción de la opción.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(255)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función encargada de retornar el instrumento al que pertenece esta opción.
	 * 
	 * @return El instrumento al que pertenece esta opción.
	 */
	@ManyToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = ChoiceInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public ChoiceInstrument getChoiceInstrument() {
		return this.choiceInstrument;
	}

	/**
	 * La función encargada de retornar el valor de respuesta de la opción.
	 * 
	 * @return El valor de respuesta de la opción.
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "option", targetEntity = TrueFalseAnswer.class, orphanRemoval = true)
	public TrueFalseAnswer getTrueFalseAnswer() {
		return this.trueFalseAnswer;
	}

	/**
	 * La función encargada de cargar la descripción de la opción.
	 * 
	 * @param description
	 *            La descripción de la opción.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La función encargada de cargar el instrumento al que pertenece esta opción.
	 * 
	 * @param choiceInstrument
	 *            El instrumento al que pertenece esta opción.
	 */
	public void setChoiceInstrument(ChoiceInstrument choiceInstrument) {
		this.choiceInstrument = choiceInstrument;
	}

	/**
	 * La función encargada de cargar el valor de respuesta de la opción.
	 * 
	 * @param trueFalseAnswer
	 *            El valor de respuesta de la opción.
	 */
	public void setTrueFalseAnswer(TrueFalseAnswer trueFalseAnswer) {
		this.trueFalseAnswer = trueFalseAnswer;

		if (this.trueFalseAnswer != null) {
			this.trueFalseAnswer.setOption(this);
		}
	}
}
