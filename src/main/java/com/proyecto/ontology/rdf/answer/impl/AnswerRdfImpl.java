package com.proyecto.ontology.rdf.answer.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.answer.Answer;
import com.proyecto.ontology.rdf.ProyectoRdfImpl;
import com.proyecto.ontology.rdf.answer.AnswerRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <A>
 *            La clase de respuesta que vamos a cargar.
 */
public abstract class AnswerRdfImpl<A extends Answer> extends ProyectoRdfImpl<A> implements AnswerRdf<A> {

	/**
	 * La clase de respuesta.
	 */
	private OntClass answerClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		if (answerClass == null) {
			
			String answerClassName = ConstantsOntology.NAMESPACE + Answer.class.getSimpleName();
			this.answerClass = ontology.getOntClass(answerClassName);

			if (answerClass == null) {
				this.answerClass = ontology.createClass(answerClassName);
			}
		}
		return this.answerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, A answer) {
		return individual;
	}
}