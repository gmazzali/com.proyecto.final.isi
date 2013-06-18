package com.proyecto.model.answer;

import java.io.Serializable;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una relación entre 2 frases para un ejercicio de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class RelationAnswer extends Answer<Serializable> {

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
	 * El constructor de una relación.
	 */
	public RelationAnswer() {
		super();
		this.leftSide = null;
		this.rightSide = null;
	}

	/**
	 * La función encargada de retornar el lado izquierdo de la relación.
	 * 
	 * @return La frase del lado izquierdo de la relación.
	 */
	public String getLeftSide() {
		return this.leftSide;
	}

	/**
	 * La función encargada de retornar el lado derecho de la relación.
	 * 
	 * @return La frase del lado derecho de la relación.
	 */
	public String getRightSide() {
		return this.rightSide;
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
}
