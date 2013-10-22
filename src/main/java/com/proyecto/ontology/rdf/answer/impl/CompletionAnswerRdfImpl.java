package com.proyecto.ontology.rdf.answer.impl;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.ontology.rdf.answer.CompletionAnswerRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas para completar dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class CompletionAnswerRdfImpl extends AnswerRdfImpl<CompletionAnswer> implements CompletionAnswerRdf {

	private static final long serialVersionUID = -405924855045939575L;

	/**
	 * La clase de respuesta para completar.
	 */
	private OntClass completionAnswerClass;
	/**
	 * Las relaciones de la clase de respuesta para completar.
	 */
	private DatatypeProperty haveIndex;
	private DatatypeProperty havePhrase;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		String completionAnswerClassName = this.namespace + CompletionAnswer.class.getSimpleName();
		if (this.completionAnswerClass == null) {
			this.completionAnswerClass = ontology.getOntClass(completionAnswerClassName);
			if (completionAnswerClass == null) {
				this.completionAnswerClass = ontology.createClass(completionAnswerClassName);
			}

			// Cargamos el padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.completionAnswerClass);
		}

		// Creamos las relaciones si son nulas.
		String index = this.namespace + Constants.Ontology.PROPERTY_ANSWER_COMPLETE_HAVE_INDEX;
		if (this.haveIndex == null) {
			this.haveIndex = ontology.getDatatypeProperty(index);
			if (this.haveIndex == null) {
				this.haveIndex = ontology.createDatatypeProperty(index);
			}
		}

		String phrase = this.namespace + Constants.Ontology.PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE;
		if (this.havePhrase == null) {
			this.havePhrase = ontology.getDatatypeProperty(phrase);
			if (this.havePhrase == null) {
				this.havePhrase = ontology.createDatatypeProperty(phrase);
			}
		}

		return this.completionAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, CompletionAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos los literales.
		Literal index = ontology.createTypedLiteral(answer.getIndex(), XSDDatatype.XSDstring);
		Literal phrase = ontology.createTypedLiteral(answer.getPhrase(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveIndex, index));
		statements.add(ontology.createLiteralStatement(individual, this.havePhrase, phrase));

		ontology.add(statements);

		return individual;
	}
}