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
	 * La función encargada de vaciar el contenido de la ontología anterior para cargarla con la nueva evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que vamos a cargar dentro de la ontología.
	 */
	public void initAssessmentOntology(Assessment assessment);

	/**
	 * La función encargada de retornar la ontologia de la evaluación que creamos.
	 * 
	 * @return La ontologia con la evaluación creada.
	 */
	public OntModel getAssessmentOntology();
}
