package com.proyecto.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.activity.Activity;

/**
 * La interfaz que define el comportamiento definido para cargar una ontolog�a con una actividad.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ActivityOntology {

	/**
	 * La funci�n encargada de cargar una actividad dentro de una ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a almacenar la actividad.
	 * @param assessment
	 *            El individuo que reprensenta la evaluaci�n a la que permtenece la actividad.
	 * @param activity
	 *            La actividad que vamos a almacenar.
	 * @return El individuo que acabamos de crear y cargar dentro de la ontolog�a.
	 */
	public <A extends Activity> Individual loadActivityToOntology(OntModel ontology, Individual assessment, A activity);
}
