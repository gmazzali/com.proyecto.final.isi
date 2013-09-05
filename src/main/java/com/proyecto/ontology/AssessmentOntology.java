package com.proyecto.ontology;

import java.io.Serializable;

import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.assessment.Assessment;

/**
 * La interfaz que define el comportamiento del servicio para crear una ontolog�a en base a una evaluaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentOntology extends Serializable {
	/**
	 * La funci�n encargada de vaciar el contenido de la ontolog�a anterior para cargarla con la nueva evaluaci�n.
	 * 
	 * @param assessment
	 *            La evaluaci�n que vamos a cargar dentro de la ontolog�a.
	 * @return La ontolog�a que creamos con la evaluaci�n.
	 */
	public OntModel initAssessmentOntology(Assessment assessment);
}
