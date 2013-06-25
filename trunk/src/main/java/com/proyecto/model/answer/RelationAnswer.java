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
 * La clase que nos permite definir una relaci�n entre 2 frases para un ejercicio de correspondencia.
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
	private CorrespondenceInstrument correspondenceInstrument;

	/**
	 * El constructor de una relaci�n.
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
	 * La funci�n encargada de retornar el lado izquierdo de la relaci�n.
	 * 
	 * @return La frase del lado izquierdo de la relaci�n.
	 */
	@Column(name = "LEFT_SIDE", columnDefinition = "text", nullable = true)
	public String getLeftSide() {
		return this.leftSide;
	}

	/**
	 * La funci�n encargada de retornar el lado derecho de la relaci�n.
	 * 
	 * @return La frase del lado derecho de la relaci�n.
	 */
	@Column(name = "RIGHT_SIDE", columnDefinition = "text", nullable = true)
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
	public CorrespondenceInstrument getCorrespondenceInstrument() {
		return this.correspondenceInstrument;
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
	 * @param correspondenceInstrument
	 *            La correspondencia a la que pertenece esta relaci�n.
	 */
	public void setCorrespondenceInstrument(CorrespondenceInstrument correspondenceInstrument) {
		this.correspondenceInstrument = correspondenceInstrument;
	}
}
