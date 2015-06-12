package com.proyecto.model.material.assessment.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.type.AssessmentMoment;

/**
 * La enumeración que define los tipos de evaluación que tenemos dentro del sistema de acuerdo al momento en el que son tomadas las mismas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum AssessmentMomentImpl implements AssessmentMoment {

	/**
	 * El tipo de evaluación diagnostica tomada al principio del curso.
	 */
	DIAGNOSTIC("type.assessment.time.diagnostic"),
	/**
	 * El tipo de evaluación formativa tomada durante el cursado.
	 */
	FORMATIVE("type.assessment.time.formative"),
	/**
	 * El tipo de evaluación sumativa tomada al final del cursado.
	 */
	SUMMATIVE("type.assessment.time.summative");

	/**
	 * El nombre del tipo de evaluación por el momento en el que son tomadas.
	 */
	private String name;

	/**
	 * El constructor de un tipo de evaluación dentro del sistema.
	 * 
	 * @param name
	 *            El nombre del tipo de evaluación.
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