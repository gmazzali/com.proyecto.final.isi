package com.proyecto.ontology.task;

import java.io.Serializable;

import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;

/**
 * La interfaz que nos permite validar la conformaci�n de una evaluaci�n mediante la carga de la misma dentro de una ontolog�a y cargando un conjunto
 * de reglas dentro de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ValidateAssessment extends Serializable {

	/**
	 * La funci�n encargada de cargar dentro del proceso la evaluaci�n y el conjunto de reglas que tenemos para evaluar la evaluaci�n.
	 * 
	 * @param assessment
	 *            La evaluaci�n que va a someterse a su validaci�n.
	 * @param ruleSet
	 *            El conjunto de reglas que vamos a usar para validar la evaluaci�n.
	 */
	public void initValidateTask(Assessment assessment, RuleSet ruleSet);

	/**
	 * La funci�n encargada de comenzar la ejecuci�n de la validaci�n de una evaluaci�n.
	 * 
	 * @param stringBuffer
	 *            El buffer donde vamos a cargar los mensajes de salida de la ejecuci�n de la validaci�n de la evaluaci�n.
	 */
	public void executeTask(final StringBuffer stringBuffer);
}