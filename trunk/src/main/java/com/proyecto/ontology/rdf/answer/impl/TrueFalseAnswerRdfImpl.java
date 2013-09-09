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
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.ontology.rdf.answer.TrueFalseAnswerRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas booleanas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class TrueFalseAnswerRdfImpl extends AnswerRdfImpl<TrueFalseAnswer> implements TrueFalseAnswerRdf {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String relationAnswerClassName = ConstantsOntology.NAMESPACE + TrueFalseAnswer.class.getSimpleName();
		OntClass relationAnswerClass = ontology.getOntClass(relationAnswerClassName);

		if (relationAnswerClass == null) {
			relationAnswerClass = ontology.createClass(relationAnswerClassName);
		}

		superClass.addSubClass(relationAnswerClass);

		return relationAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, TrueFalseAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos las relaciones.
		DatatypeProperty haveValue = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);

		// Creamos los literales.
		Literal value = ontology.createTypedLiteral(answer.getValue(), XSDDatatype.XSDboolean);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, haveValue, value));

		ontology.add(statements);

		return individual;
	}
}
