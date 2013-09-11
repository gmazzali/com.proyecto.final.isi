package com.proyecto.ontology.rdf.material.assessment.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.ontology.rdf.material.assessment.AssessmentRdf;
import com.proyecto.ontology.rdf.material.assessment.factory.AssessmentFactoryRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de las evaluaciones dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class AssessmentFactoryRdfImpl implements AssessmentFactoryRdf {

	private static final long serialVersionUID = -4952358146682531586L;

	/*
	 * El servicio de las evaluaciones dentro de la ontología.
	 */
	@Autowired
	private AssessmentRdf assessmentRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.assessmentRdf.initClass(ontology);
	}

	@Override
	public Individual loadInstrumentToOntology(OntModel ontology, Assessment entity) {
		return this.assessmentRdf.createIndividual(ontology, entity);
	}
}