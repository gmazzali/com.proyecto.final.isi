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
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas para las relaciones dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class RelationAnswerRdfImpl extends AnswerRdfImpl<RelationAnswer> implements RelationAnswerRdf {

	private static final long serialVersionUID = -3230778665793275321L;

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
		String relationAnswerClassName = this.namespace + OntologyConstants.ClassName.ANSWER_RELATION;
		this.relationAnswerClass = ontology.getOntClass(relationAnswerClassName);
		if (this.relationAnswerClass == null) {
			this.relationAnswerClass = ontology.createClass(relationAnswerClassName);
		}

		// Cargamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.relationAnswerClass);

		// Creamos las relaciones.
		String leftSide = this.namespace + OntologyConstants.PropertyName.ANSWER_RELATION_HAS_LEFT_SIDE;
		this.haveLeftSide = ontology.getDatatypeProperty(leftSide);
		if (this.haveLeftSide == null) {
			this.haveLeftSide = ontology.createDatatypeProperty(leftSide);
		}

		String rightSide = this.namespace + OntologyConstants.PropertyName.ANSWER_RELATION_HAS_RIGHT_SIDE;
		this.haveRightSide = ontology.getDatatypeProperty(rightSide);
		if (this.haveRightSide == null) {
			this.haveRightSide = ontology.createDatatypeProperty(rightSide);
		}

		return this.relationAnswerClass;
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