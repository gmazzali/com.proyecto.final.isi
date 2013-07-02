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
 * La clase que representa una opci�n en el instrumento de selecci�n.
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
	 * La descripci�n de la opci�n.
	 */
	private String description;
	/**
	 * El valor de la respuesta.
	 */
	private TrueFalseAnswer trueFalseAnswer;
	/**
	 * El instrumento al que pertenece esta opci�n.
	 */
	private ChoiceInstrument choiceInstrument;

	/**
	 * El constructor que recibe el tipo de respuesta que vamos a crear con este constructor.
	 * 
	 * @param trueFalseAnswer
	 *            El valor de la respuesta de esta opci�n.
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
	 * La funci�n encargada de retornar la descripci�n de la opci�n.
	 * 
	 * @return La descripci�n de la opci�n.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(255)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n encargada de retornar el instrumento al que pertenece esta opci�n.
	 * 
	 * @return El instrumento al que pertenece esta opci�n.
	 */
	@ManyToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = ChoiceInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public ChoiceInstrument getChoiceInstrument() {
		return this.choiceInstrument;
	}

	/**
	 * La funci�n encargada de retornar el valor de respuesta de la opci�n.
	 * 
	 * @return El valor de respuesta de la opci�n.
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "option", targetEntity = TrueFalseAnswer.class, orphanRemoval = true)
	public TrueFalseAnswer getTrueFalseAnswer() {
		return this.trueFalseAnswer;
	}

	/**
	 * La funci�n encargada de cargar la descripci�n de la opci�n.
	 * 
	 * @param description
	 *            La descripci�n de la opci�n.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n encargada de cargar el instrumento al que pertenece esta opci�n.
	 * 
	 * @param choiceInstrument
	 *            El instrumento al que pertenece esta opci�n.
	 */
	public void setChoiceInstrument(ChoiceInstrument choiceInstrument) {
		this.choiceInstrument = choiceInstrument;
	}

	/**
	 * La funci�n encargada de cargar el valor de respuesta de la opci�n.
	 * 
	 * @param trueFalseAnswer
	 *            El valor de respuesta de la opci�n.
	 */
	public void setTrueFalseAnswer(TrueFalseAnswer trueFalseAnswer) {
		this.trueFalseAnswer = trueFalseAnswer;

		if (this.trueFalseAnswer != null) {
			this.trueFalseAnswer.setOption(this);
		}
	}
}
