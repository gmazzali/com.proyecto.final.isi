package com.proyecto.ontology.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.ReactiveOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de reactivos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveOntologyImpl implements ReactiveOntology {

	/**
	 * El servicio de carga de los instrumentos a la ontología.
	 */
	@Autowired
	// private InstrumentOntology instrumentOntology;
	@Override
	public <R extends Reactive> Individual loadReactiveToOntology(OntModel ontology, Individual activity, R reactive) {
		// String className = Constants.NAMESPACE + reactive.getClass().getSimpleName();
		// String individualName = className + "_" + reactive.getId();
		//
		// // Creamos la clase y la instancia del reactivo.
		// OntClass reactiveClass = ontology.createClass(className);
		// Individual reactiveIndividual = reactiveClass.createIndividual(individualName);
		//
		// // Creamos las relaciones.
		// DatatypeProperty haveDescription = ontology.createDatatypeProperty(Constants.PROPERTY_REACTIVE_HAVE_DESCRIPTION);
		// // DatatypeProperty haveInstrument = ontology.createDatatypeProperty(Constants.PROPERTY_REACTIVE_HAVE_INSTRUMENT);
		//
		// // Creamos los literales.
		// Literal description = ontology.createTypedLiteral(reactive.getDescription(), XSDDatatype.XSDstring);
		//
		// // Creamos las carga de los datos.
		// List<Statement> statements = new ArrayList<Statement>();
		// statements.add(ontology.createLiteralStatement(reactiveIndividual, haveDescription, description));
		//
		// RDFList list = ontology.createList();
		//
		// statements.add(ontology.createLiteralStatement(reactiveIndividual, haveDescription, list));
		//
		// statements.add(ontology.createLiteralStatement(reactiveIndividual, haveInstrument,
		// this.instrumentOntology.loadInstrumentToOntology(ontology, reactiveIndividual, reactive.getInstrument())));
		//
		// ontology.add(statements);

		return activity;
	}
}