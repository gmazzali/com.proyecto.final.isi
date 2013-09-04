package com.proyecto.ontology.impl;

import com.common.util.annotations.Service;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.ontology.AssessmentOntology;

/**
 * La clase que va a crear y mantener la ontologia de una evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AssessmentOntologyImpl implements AssessmentOntology {

	private static final long serialVersionUID = 4074693337639314224L;

	/**
	 * Las cadenas que vamos a utilizar.
	 */
	protected static final String NAMESPACE = "http:/www.assessments.com/#";

	protected static final String HAVE_DESCRIPTION = "have_description";
	protected static final String HAVE_MOMENT = "have_moment";
	protected static final String HAVE_DATE = "have_date";
	protected static final String HAVE_ACTIVITIES = "have_activities";
	
	protected static final String HAVE_REACTIVES = "have_reactives";
	protected static final String HAVE_INSTRUMENT = "have_instrument";
	protected static final String HAVE_OPTION = "have_option";
	protected static final String HAVE_ANSWER = "have_answer";
	
	/**
	 * La ontología que vamos a mantener en memoria.
	 */
	protected OntModel assessmentOntology = null;

	@Override
	public void initAssessmentOntology(Assessment assessment) {
		// Cerramos la ontología anterior.
		if(this.assessmentOntology != null) {
			this.assessmentOntology.close();
		}
		
		// Creamos una ontología vacia.
		this.assessmentOntology = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
		this.assessmentOntology.prepare();
		
		// TODO gmazzali Hacer lo de la carga de la evaluación dentro de la ontologia.
	}

	@Override
	public OntModel getAssessmentOntology() {
		return assessmentOntology;
	}
}