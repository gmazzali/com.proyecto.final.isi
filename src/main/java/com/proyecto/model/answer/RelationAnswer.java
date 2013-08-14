package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;

/**
 * La clase que nos permite definir una relaci�n entre 2 frases para un ejercicio de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "RELATION_ANSWERS")
@javax.persistence.Entity(name = "RelationAnswer")
public class RelationAnswer extends Answer {

	private static final long serialVersionUID = -6324391499398899382L;

	/**
	 * La enumeraci�n que nos dice de que lado vamos a cargar la relaci�n.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public enum Side {
		LEFT, RIGTH;
	}

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String LEFT_SIDE = "leftSide";
		static final String RIGHT_SIDE = "rightSide";
		static final String INSTRUMENT = "instrument";
	}

	/**
	 * La primer parte de la relaci�n.
	 */
	private String leftSide;
	/**
	 * La segunda parte de la relaci�n.
	 */
	private String rightSide;

	/**
	 * El instrumento de correspondencia a la que pertenece esta relaci�n.
	 */
	private CorrespondenceInstrument instrument;

	/**
	 * El constructor de una relaci�n.
	 */
	public RelationAnswer() {
		super();
		this.leftSide = null;
		this.rightSide = null;
		this.instrument = null;
	}

	@Override
	public String toString() {
		if (this.leftSide != null && this.rightSide != null) {
			return this.leftSide + " - " + this.rightSide;
		} else if (this.leftSide != null) {
			return this.leftSide;
		} else if (this.rightSide != null) {
			return this.rightSide;
		} else {
			return null;
		}
	}

	/**
	 * Agregamos una frase de acuerdo al lado de la relaci�n que recibimos.
	 * 
	 * @param phrase
	 *            La frase que vamos a guardar en la relaci�n.
	 * @param side
	 *            El lado de la relaci�n que vamos a cargar.
	 */
	public void setPhrase(String phrase, Side side) {
		switch (side) {
			case LEFT:
				this.leftSide = phrase;
				break;

			case RIGTH:
				this.rightSide = phrase;
				break;
		}
	}

	/**
	 * La funci�n encargada de retornar la frase que corresponde con el lado que se recibe.
	 * 
	 * @param side
	 *            El lado que se quiere recuperar.
	 * @return La frase que corresponde con el lado que se recibi�.
	 */
	public String getPhrase(Side side) {
		switch (side) {
			case LEFT:
				return this.leftSide;

			case RIGTH:
				return this.rightSide;
		}
		return null;
	}

	/**
	 * La funci�n encargada de retornar el lado izquierdo de la relaci�n.
	 * 
	 * @return La frase del lado izquierdo de la relaci�n.
	 */
	@Column(name = "LEFT_SIDE", columnDefinition = "varchar(255)", nullable = true)
	public String getLeftSide() {
		return this.leftSide;
	}

	/**
	 * La funci�n encargada de retornar el lado derecho de la relaci�n.
	 * 
	 * @return La frase del lado derecho de la relaci�n.
	 */
	@Column(name = "RIGHT_SIDE", columnDefinition = "varchar(255)", nullable = true)
	public String getRightSide() {
		return this.rightSide;
	}

	/**
	 * La funci�n encargada de retornar la correspondencia a la que pertenece esta relaci�n.
	 * 
	 * @return La correspondencia a la que pertenece esta relaci�n.
	 */
	@ManyToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = CorrespondenceInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public CorrespondenceInstrument getInstrument() {
		return this.instrument;
	}

	/**
	 * La funci�n encargada de cargar el lado izquierdo de la relaci�n.
	 * 
	 * @param leftSide
	 *            El lado izquierdo de la relaci�n.
	 */
	public void setLeftSide(String leftSide) {
		this.leftSide = leftSide;
	}

	/**
	 * La funci�n encargada de cargar el lado derecho de la relaci�n.
	 * 
	 * @param rightSide
	 *            El lado derecho de la relaci�n.
	 */
	public void setRightSide(String rightSide) {
		this.rightSide = rightSide;
	}

	/**
	 * La funci�n encargada de cargar la correspondencia a la que pertenece esta relaci�n.
	 * 
	 * @param instrument
	 *            La correspondencia a la que pertenece esta relaci�n.
	 */
	public void setInstrument(CorrespondenceInstrument instrument) {
		this.instrument = instrument;
	}
}
