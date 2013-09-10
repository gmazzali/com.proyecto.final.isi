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

	/**
	 * La clase de respuesta booleana.
	 */
	private OntClass relationAnswerClass;
	/**
	 * Las relaciones de las respuestas booleanas.
	 */
	private DatatypeProperty haveValue;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.relationAnswerClass == null) {
			
			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String relationAnswerClassName = ConstantsOntology.NAMESPACE + TrueFalseAnswer.class.getSimpleName();
			this.relationAnswerClass = ontology.getOntClass(relationAnswerClassName);

			if (this.relationAnswerClass == null) {
				this.relationAnswerClass = ontology.createClass(relationAnswerClassName);
			}

			superClass.addSubClass(this.relationAnswerClass);
		}

		// Creamos las relaciones.
		if (this.haveValue == null) {
			this.haveValue = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);
			if (this.haveValue == null) {
				this.haveValue = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);
			}
		}

		return this.relationAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, TrueFalseAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos los literales.
		Literal value = ontology.createTypedLiteral(answer.getValue(), XSDDatatype.XSDboolean);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveValue, value));

		ontology.add(statements);

		return individual;
	}
}
