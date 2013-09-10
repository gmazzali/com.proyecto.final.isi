package com.proyecto.ontology.rdf.option.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.option.Option;
import com.proyecto.ontology.rdf.ProyectoRdfImpl;
import com.proyecto.ontology.rdf.answer.TrueFalseAnswerRdf;
import com.proyecto.ontology.rdf.option.OptionRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <O>
 *            La clase de opción que vamos a manejar dentro de la ontología.
 */
public class OptionRdfImpl<O extends Option> extends ProyectoRdfImpl<O> implements OptionRdf<O> {

	/**
	 * El sevicio para las respuestas booleanas.
	 */
	@Autowired
	private TrueFalseAnswerRdf trueFalseAnswerRdf;

	/**
	 * La clase de opción.
	 */
	private OntClass optionClass;
	/**
	 * Las relaciones de la clase de opciones.
	 */
	private DatatypeProperty haveDescription;
	private ObjectProperty haveAnswer;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.optionClass == null) {

			String optionClassName = ConstantsOntology.NAMESPACE + Option.class.getSimpleName();
			this.optionClass = ontology.getOntClass(optionClassName);

			if (this.optionClass == null) {
				this.optionClass = ontology.createClass(optionClassName);
			}
		}

		// Creamos las realaciones.
		if (this.haveDescription == null) {
			this.haveDescription = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_DESCRIPTION);
			if (this.haveDescription == null) {
				this.haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_DESCRIPTION);
			}
		}

		if (this.haveAnswer == null) {
			this.haveAnswer = ontology.getObjectProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_ANSWER);
			if (this.haveAnswer == null) {
				this.haveAnswer = ontology.createObjectProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_ANSWER);
			}
		}

		return optionClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, O entity) {
		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveDescription, description));
		statements.add(ontology.createLiteralStatement(individual, this.haveAnswer,
				this.trueFalseAnswerRdf.createIndividual(ontology, entity.getTrueFalseAnswer())));

		ontology.add(statements);

		return individual;
	}
}