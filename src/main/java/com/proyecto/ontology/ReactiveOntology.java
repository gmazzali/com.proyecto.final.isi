package com.proyecto.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La interfaz que define el comportamiento definido para cargar una ontología con un reactivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ReactiveOntology {

	/**
	 * La función encargada de cargar un reactivo dentro de una ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a almacenar el reactivo.
	 * @param activity
	 *            La actividad a la que permtenece el reactivo.
	 * @param reactive
	 *            El reactivo que vamos a almacenar.
	 * @return El individuo que acabamos de crear y cargar dentro de la ontología.
	 */
	public <R extends Reactive> Individual loadReactiveToOntology(OntModel ontology, Activity activity, R reactive);
}
