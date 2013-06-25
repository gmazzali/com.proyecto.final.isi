package com.proyecto.model.answer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.instrument.CorrespondenceInstrument;

/**
 * La clase que nos permite definir una relación entre 2 frases para un ejercicio de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "RELATIONS_ANSWERS")
@javax.persistence.Entity(name = "RelationAnswer")
public class RelationAnswer extends Answer {

	private static final long serialVersionUID = -6324391499398899382L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String LEFT_SIDE = "leftSide";
		static final String RIGHT_SIDE = "rightSide";
	}

	/**
	 * La primer parte de la relación.
	 */
	private String leftSide;
	/**
	 * La segunda parte de la relación.
	 */
	private String rightSide;

	/**
	 * El instrumento de correspondencia a la que pertenece esta relación.
	 */
	private CorrespondenceInstrument correspondenceInstrument;

	/**
	 * El constructor de una relación.
	 */
	public RelationAnswer() {
		super();
		this.leftSide = null;
		this.rightSide = null;
		this.correspondenceInstrument = null;
	}

	@Override
	public String toString() {
		return this.leftSide + " - " + this.rightSide;
	}

	/**
	 * La función encargada de retornar el lado izquierdo de la relación.
	 * 
	 * @return La frase del lado izquierdo de la relación.
	 */
	@Column(name = "LEFT_SIDE", columnDefinition = "text", nullable = true)
	public String getLeftSide() {
		return this.leftSide;
	}

	/**
	 * La función encargada de retornar el lado derecho de la relación.
	 * 
	 * @return La frase del lado derecho de la relación.
	 */
	@Column(name = "RIGHT_SIDE", columnDefinition = "text", nullable = true)
	public String getRightSide() {
		return this.rightSide;
	}

	/**
	 * La función encargada de retornar la correspondencia a la que pertenece esta relación.
	 * 
	 * @return La correspondencia a la que pertenece esta relación.
	 */
	@ManyToOne(cascade =
		{ CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER, targetEntity = CorrespondenceInstrument.class, optional = false)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public CorrespondenceInstrument getCorrespondenceInstrument() {
		return this.correspondenceInstrument;
	}

	/**
	 * La función encargada de cargar el lado izquierdo de la relación.
	 * 
	 * @param leftSide
	 *            El lado izquierdo de la relación.
	 */
	public void setLeftSide(String leftSide) {
		this.leftSide = leftSide;
	}

	/**
	 * La función encargada de cargar el lado derecho de la relación.
	 * 
	 * @param rightSide
	 *            El lado derecho de la relación.
	 */
	public void setRightSide(String rightSide) {
		this.rightSide = rightSide;
	}

	/**
	 * La función encargada de cargar la correspondencia a la que pertenece esta relación.
	 * 
	 * @param correspondenceInstrument
	 *            La correspondencia a la que pertenece esta relación.
	 */
	public void setCorrespondenceInstrument(CorrespondenceInstrument correspondenceInstrument) {
		this.correspondenceInstrument = correspondenceInstrument;
	}
}
