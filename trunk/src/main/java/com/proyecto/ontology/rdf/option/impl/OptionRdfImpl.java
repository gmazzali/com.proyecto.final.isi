package com.proyecto.ontology.rdf.option.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
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

	@Autowired
	private TrueFalseAnswerRdf trueFalseAnswerRdf;

	@Override
	public OntClass createClass(OntModel ontology) {
		String optionClassName = ConstantsOntology.NAMESPACE + Option.class.getSimpleName();
		OntClass optionClass = ontology.getOntClass(optionClassName);

		if (optionClass == null) {
			optionClass = ontology.createClass(optionClassName);
		}

		return optionClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, O entity) {
		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_DESCRIPTION);
		DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_ANSWER);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(individual, haveAnswer,
				this.trueFalseAnswerRdf.createIndividual(ontology, entity.getTrueFalseAnswer())));

		ontology.add(statements);

		return individual;
	}
}