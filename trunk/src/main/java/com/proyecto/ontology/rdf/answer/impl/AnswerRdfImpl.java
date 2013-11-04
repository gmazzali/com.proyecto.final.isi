package com.proyecto.ontology.rdf.answer.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.answer.Answer;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.ProyectoRdfImpl;
import com.proyecto.ontology.rdf.answer.AnswerRdf;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <A>
 *            La clase de respuesta que vamos a cargar.
 */
@RdfService("AnswerRdf")
public class AnswerRdfImpl<A extends Answer> extends ProyectoRdfImpl<A> implements AnswerRdf<A> {

	private static final long serialVersionUID = 7933072370435571965L;

	/**
	 * La clase de respuesta.
	 */
	private OntClass answerClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		String answerClassName = this.namespace + OntologyConstants.ClassName.ANSWER;
		this.answerClass = ontology.getOntClass(answerClassName);
		if (this.answerClass == null) {
			this.answerClass = ontology.createClass(answerClassName);
		}
		return this.answerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, A answer) {
		return individual;
	}
}