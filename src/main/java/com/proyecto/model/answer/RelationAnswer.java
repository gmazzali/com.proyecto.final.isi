package com.proyecto.model.answer;

import java.io.Serializable;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una relaci�n entre 2 frases para un ejercicio de correspondencia.
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
	 * La primer parte de la relaci�n.
	 */
	private String leftSide;
	/**
	 * La segunda parte de la relaci�n.
	 */
	private String rightSide;

	/**
	 * El constructor de una relaci�n.
	 */
	public RelationAnswer() {
		super();
		this.leftSide = null;
		this.rightSide = null;
	}

	/**
	 * La funci�n encargada de retornar el lado izquierdo de la relaci�n.
	 * 
	 * @return La frase del lado izquierdo de la relaci�n.
	 */
	public String getLeftSide() {
		return this.leftSide;
	}

	/**
	 * La funci�n encargada de retornar el lado derecho de la relaci�n.
	 * 
	 * @return La frase del lado derecho de la relaci�n.
	 */
	public String getRightSide() {
		return this.rightSide;
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
}
