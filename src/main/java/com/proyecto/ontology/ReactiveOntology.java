package com.proyecto.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La interfaz que define el comportamiento definido para cargar una ontolog�a con un reactivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ReactiveOntology {

	/**
	 * La funci�n encargada de cargar un reactivo dentro de una ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a almacenar el reactivo.
	 * @param activity
	 *            El individul que representa la actividad a la que permtenece el reactivo.
	 * @param reactive
	 *            El reactivo que vamos a almacenar.
	 * @return El individuo que acabamos de crear y cargar dentro de la ontolog�a.
	 */
	public <R extends Reactive> Individual loadReactiveToOntology(OntModel ontology, Individual activity, R reactive);
}
