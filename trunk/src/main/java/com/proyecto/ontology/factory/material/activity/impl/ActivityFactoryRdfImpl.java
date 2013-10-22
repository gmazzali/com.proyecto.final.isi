package com.proyecto.ontology.factory.material.activity.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.ontology.factory.material.activity.ActivityFactoryRdf;
import com.proyecto.ontology.rdf.material.activity.ActivityRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de las actividades dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ActivityFactoryRdfImpl implements ActivityFactoryRdf {

	private static final long serialVersionUID = -2080458796372649799L;

	/**
	 * El servicios para las actividades.
	 */
	@Autowired
	private ActivityRdf activityRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.activityRdf.initClass(ontology);
	}

	@Override
	public Individual loadEntityToOntology(OntModel ontology, Activity entity) {
		return this.activityRdf.createIndividual(ontology, entity);
	}
}