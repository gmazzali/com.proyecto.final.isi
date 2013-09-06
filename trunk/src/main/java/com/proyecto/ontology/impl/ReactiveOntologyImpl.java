package com.proyecto.ontology.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.InstrumentOntology;
import com.proyecto.ontology.ReactiveOntology;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de reactivos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class ReactiveOntologyImpl implements ReactiveOntology {

	/**
	 * El servicio de carga de los instrumentos a la ontología.
	 */
	@Autowired
	private InstrumentOntology instrumentOntology;

	@Override
	public <R extends Reactive> Individual loadReactiveToOntology(OntModel ontology, Activity activity, R reactive) {
		String className = ConstantsOntology.NAMESPACE + reactive.getClass().getSimpleName();
		String individualName = className + "_" + reactive.getId();

		// Creamos la clase y la instancia del reactivo.
		OntClass reactiveClass = ontology.createClass(className);
		Individual reactiveIndividual = reactiveClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_REACTIVE_HAVE_DESCRIPTION);
		DatatypeProperty haveReactive = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_REACTIVE_HAVE_INSTRUMENT);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(reactive.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(reactiveIndividual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(reactiveIndividual, haveReactive,
				this.instrumentOntology.loadInstrumentToOntology(ontology, reactive, reactive.getInstrument())));

		ontology.add(statements);

		return reactiveIndividual;
	}
}