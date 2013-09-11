package com.proyecto.ontology.rdf.material.reactive.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.rdf.material.reactive.ReactiveRdf;
import com.proyecto.ontology.rdf.material.reactive.factory.ReactiveFactoryRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de los reactivos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ReactiveFactoryRdfImpl implements ReactiveFactoryRdf {

	private static final long serialVersionUID = -4828602372414748272L;

	/**
	 * El servicio para la cima de la jerarquia y los hijos.
	 */
	@Autowired
	private ReactiveRdf reactiveRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.reactiveRdf.initClass(ontology);
	}

	@Override
	public Individual loadInstrumentToOntology(OntModel ontology, Reactive entity) {
		return this.reactiveRdf.createIndividual(ontology, entity);
	}
}