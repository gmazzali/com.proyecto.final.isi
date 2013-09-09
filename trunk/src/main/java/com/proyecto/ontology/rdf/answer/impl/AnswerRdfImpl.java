package com.proyecto.ontology.rdf.answer.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.answer.Answer;
import com.proyecto.ontology.rdf.ProyectoRdfImpl;
import com.proyecto.ontology.rdf.answer.AnswerRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <A>
 *            La clase de respuesta que vamos a cargar.
 */
public abstract class AnswerRdfImpl<A extends Answer> extends ProyectoRdfImpl<A> implements AnswerRdf<A> {

	@Override
	public OntClass createClass(OntModel ontology) {
		String answerClassName = ConstantsOntology.NAMESPACE + Answer.class.getSimpleName();
		OntClass answerClass = ontology.getOntClass(answerClassName);

		if (answerClass == null) {
			answerClass = ontology.createClass(answerClassName);
		}

		return answerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, A answer) {
		return individual;
	}
}