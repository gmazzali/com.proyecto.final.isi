package com.proyecto.model.material.assessment.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.type.AssessmentMoment;

/**
 * La enumeraci�n que define los tipos de evaluaci�n que tenemos dentro del sistema de acuerdo al momento en el que son tomadas las mismas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum AssessmentMomentImpl implements AssessmentMoment {

	/**
	 * El tipo de evaluaci�n diagnostica tomada al principio del curso.
	 */
	DIAGNOSTIC("type.assessment.time.diagnostic"),
	/**
	 * El tipo de evaluaci�n formativa tomada durante el cursado.
	 */
	FORMATIVE("type.assessment.time.formative"),
	/**
	 * El tipo de evaluaci�n sumativa tomada al final del cursado.
	 */
	SUMMATIVE("type.assessment.time.summative");

	/**
	 * El nombre del tipo de evaluaci�n por el momento en el que son tomadas.
	 */
	private String name;

	/**
	 * El constructor de un tipo de evaluaci�n dentro del sistema.
	 * 
	 * @param name
	 *            El nombre del tipo de evaluaci�n.
	 */
	private AssessmentMomentImpl(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return HolderMessage.getMessage(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}
}