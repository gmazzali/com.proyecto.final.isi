package com.proyecto.ontology.factory.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.answer.Answer;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.ontology.factory.answer.AnswerFactoryRdf;
import com.proyecto.ontology.rdf.answer.AnswerRdf;
import com.proyecto.ontology.rdf.answer.CompletionAnswerRdf;
import com.proyecto.ontology.rdf.answer.EssayActivityAnswerRdf;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;
import com.proyecto.ontology.rdf.answer.TrueFalseAnswerRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de las respuestas dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class AnswerFactoryRdfImpl implements AnswerFactoryRdf {

	private static final long serialVersionUID = 8751798481664946895L;

	/**
	 * El servicio para la cima de la jerarquía.
	 */
	@Autowired
	@Qualifier("AnswerRdf")
	private AnswerRdf<Answer> answerRdf;

	/**
	 * Los servicios para las clases hijas.
	 */
	@Autowired
	private CompletionAnswerRdf completionAnswerRdf;

	@Autowired
	private EssayActivityAnswerRdf essayActivityAnswerRdf;

	@Autowired
	private RelationAnswerRdf relationAnswerRdf;

	@Autowired
	private TrueFalseAnswerRdf trueFalseAnswerRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.answerRdf.initClass(ontology);
	}

	@Override
	public Individual loadEntityToOntology(OntModel ontology, Answer answer) {

		if (answer instanceof CompletionAnswer) {
			CompletionAnswer entity = (CompletionAnswer) answer;
			return this.completionAnswerRdf.createIndividual(ontology, entity);
		}

		if (answer instanceof EssayActivityAnswer) {
			EssayActivityAnswer entity = (EssayActivityAnswer) answer;
			return this.essayActivityAnswerRdf.createIndividual(ontology, entity);
		}

		if (answer instanceof RelationAnswer) {
			RelationAnswer entity = (RelationAnswer) answer;
			return this.relationAnswerRdf.createIndividual(ontology, entity);
		}

		if (answer instanceof TrueFalseAnswer) {
			TrueFalseAnswer entity = (TrueFalseAnswer) answer;
			return this.trueFalseAnswerRdf.createIndividual(ontology, entity);
		}

		return null;
	}
}