package com.proyecto.ontology;

import java.io.Serializable;

import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.assessment.Assessment;

/**
 * La interfaz que define el comportamiento del servicio para crear una ontología en base a una evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentOntology extends Serializable {
	/**
	 * La función encargada de cargar el contenido de la ontología en base a la evaluación que recibimos.
	 * 
	 * @param assessment
	 *            La evaluación que vamos a cargar dentro de la ontología.
	 * @return La ontología que creamos con la evaluación.
	 */
	public OntModel loadAssessmentToOntology(Assessment assessment);
}
