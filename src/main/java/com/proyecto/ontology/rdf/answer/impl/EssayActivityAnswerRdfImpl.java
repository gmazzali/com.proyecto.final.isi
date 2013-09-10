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
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.ontology.rdf.answer.EssayActivityAnswerRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define comportamiento de las respuestas para ensayos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class EssayActivityAnswerRdfImpl extends AnswerRdfImpl<EssayActivityAnswer> implements EssayActivityAnswerRdf {

	/**
	 * La clase de repuesta de ensayo.
	 */
	private OntClass essayActivityAnswerClass;
	/**
	 * Las relaciones de la clase de respuesta de ensayo.
	 */
	private DatatypeProperty haveAnswer;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.essayActivityAnswerClass == null) {
			
			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String essayActivityAnswerClassName = ConstantsOntology.NAMESPACE + EssayActivityAnswer.class.getSimpleName();
			this.essayActivityAnswerClass = ontology.getOntClass(essayActivityAnswerClassName);

			if (this.essayActivityAnswerClass == null) {
				this.essayActivityAnswerClass = ontology.createClass(essayActivityAnswerClassName);
			}

			superClass.addSubClass(this.essayActivityAnswerClass);
		}

		// Creamos las relaciones.
		if (this.haveAnswer == null) {
			this.haveAnswer = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION);
			if (this.haveAnswer == null) {
				this.haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION);
			}
		}

		return essayActivityAnswerClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, EssayActivityAnswer answer) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, answer);

		// Creamos los literales.
		Literal phrase = ontology.createTypedLiteral(answer.getAnswer(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveAnswer, phrase));

		ontology.add(statements);

		return individual;
	}
}