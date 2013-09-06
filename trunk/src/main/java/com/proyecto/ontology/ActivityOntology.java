package com.proyecto.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.activity.Activity;

/**
 * La interfaz que define el comportamiento definido para cargar una ontología con una actividad.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ActivityOntology {

	/**
	 * La función encargada de cargar una actividad dentro de una ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a almacenar la actividad.
	 * @param assessment
	 *            El individuo que reprensenta la evaluación a la que permtenece la actividad.
	 * @param activity
	 *            La actividad que vamos a almacenar.
	 * @return El individuo que acabamos de crear y cargar dentro de la ontología.
	 */
	public <A extends Activity> Individual loadActivityToOntology(OntModel ontology, Individual assessment, A activity);
}
