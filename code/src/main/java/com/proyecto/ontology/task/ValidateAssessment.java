package com.proyecto.ontology.task;

import java.io.Serializable;

import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;

/**
 * La interfaz que nos permite validar la conformación de una evaluación mediante la carga de la misma dentro de una ontología y cargando un conjunto
 * de reglas dentro de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ValidateAssessment extends Serializable {

	/**
	 * La función encargada de cargar dentro del proceso la evaluación y el conjunto de reglas que tenemos para evaluar la evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que va a someterse a su validación.
	 * @param ruleSet
	 *            El conjunto de reglas que vamos a usar para validar la evaluación.
	 */
	public void initValidateTask(Assessment assessment, RuleSet ruleSet);

	/**
	 * La función encargada de comenzar la ejecución de la validación de una evaluación.
	 * 
	 * @param stringBuffer
	 *            El buffer donde vamos a cargar los mensajes de salida de la ejecución de la validación de la evaluación.
	 */
	public void executeTask(final StringBuffer stringBuffer);
}