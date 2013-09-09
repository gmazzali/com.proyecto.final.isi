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
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas para completar dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class CompletionAnswerRdfImpl extends AnswerRdfImpl<CompletionAnswer> implements CompletionAnswerRdf {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String completionAnswerClassName = ConstantsOntology.NAMESPACE + CompletionAnswer.class.getSimpleName();
		OntClass completionAnswerClass = ontology.getOntClass(completionAnswerClassName);

		if (completionAnswerClass == null) {
			completionAnswerClass = ontology.createClass(completionAnswerClassName);
		}

		superClass.addSubClass(completionAnswerClass);

		return completionAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, CompletionAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos las relaciones.
		DatatypeProperty haveIndex = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_INDEX);
		DatatypeProperty havePhrase = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE);

		// Creamos los literales.
		Literal index = ontology.createTypedLiteral(answer.getIndex(), XSDDatatype.XSDstring);
		Literal phrase = ontology.createTypedLiteral(answer.getPhrase(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, haveIndex, index));
		statements.add(ontology.createLiteralStatement(individual, havePhrase, phrase));

		ontology.add(statements);

		return individual;
	}
}