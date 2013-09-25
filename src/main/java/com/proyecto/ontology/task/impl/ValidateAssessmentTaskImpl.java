package com.proyecto.ontology.task.impl;

import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.ontology.task.ValidateAssessmentTask;

/**
 * La clase que implementa la interfaz que define el comportamiento del validador de una evaluación a partir de un conjunto de reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ValidateAssessmentTaskImpl implements ValidateAssessmentTask {

	private static final long serialVersionUID = -322789772715142375L;

	@Override
	public void initValidateTask(Assessment assessement, RuleSet ruleSet) {
		// TODO gmazzali Hacer lo de la carga de la evaluación y el conjunto de reglas dentro de la ontología.
	}

	@Override
	public void startTask() {
		// TODO gmazzali Hacer lo de la ejecución de la validación de la evaluación y el conjunto de reglas dentro de la ontología.
	}
}