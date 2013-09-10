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
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas para las relaciones dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class RelationAnswerRdfImpl extends AnswerRdfImpl<RelationAnswer> implements RelationAnswerRdf {

	/**
	 * La clase de respuesta para correspondencias.
	 */
	private OntClass relationAnswerClass;
	/**
	 * Las relaciones de las respuestas para correspondencias.
	 */
	private DatatypeProperty haveLeftSide;
	private DatatypeProperty haveRightSide;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.relationAnswerClass == null) {
			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String relationAnswerClassName = ConstantsOntology.NAMESPACE + RelationAnswer.class.getSimpleName();
			relationAnswerClass = ontology.getOntClass(relationAnswerClassName);

			if (relationAnswerClass == null) {
				relationAnswerClass = ontology.createClass(relationAnswerClassName);
			}

			superClass.addSubClass(relationAnswerClass);
		}

		// Creamos las realaciones.
		if (this.haveLeftSide == null) {
			this.haveLeftSide = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_LEFT_SIDE);
			if (this.haveLeftSide == null) {
				this.haveLeftSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_LEFT_SIDE);

			}
		}
		if (this.haveRightSide == null) {
			this.haveRightSide = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_RIGHT_SIDE);
			if (this.haveRightSide == null) {
				this.haveRightSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_RIGHT_SIDE);

			}
		}

		return relationAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, RelationAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos los literales y los cargamos.
		List<Statement> statements = new ArrayList<Statement>();
		Literal leftSide = null;
		Literal rightSide = null;
		if (answer.getLeftSide() != null) {
			leftSide = ontology.createTypedLiteral(answer.getLeftSide(), XSDDatatype.XSDstring);
			statements.add(ontology.createLiteralStatement(individual, this.haveLeftSide, leftSide));
		}
		if (answer.getRightSide() != null) {
			rightSide = ontology.createTypedLiteral(answer.getRightSide(), XSDDatatype.XSDstring);
			statements.add(ontology.createLiteralStatement(individual, this.haveRightSide, rightSide));
		}

		ontology.add(statements);

		return individual;
	}
}