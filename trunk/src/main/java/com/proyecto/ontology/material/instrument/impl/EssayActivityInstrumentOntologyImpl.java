package com.proyecto.ontology.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.EssayActivityInstrument;
import com.proyecto.ontology.material.instrument.EssayActivityInstrumentOntology;
import com.proyecto.util.ConstantsOntology;

public class EssayActivityInstrumentOntologyImpl<I extends EssayActivityInstrument> extends FormalInstrumentOntologyImpl<I> implements
		EssayActivityInstrumentOntology<I> {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String essayFormalInstrumentClassName = ConstantsOntology.NAMESPACE + EssayActivityInstrument.class.getSimpleName();
		OntClass essayFormalInstrumentClass = ontology.getOntClass(essayFormalInstrumentClassName);

		if (essayFormalInstrumentClass == null) {
			essayFormalInstrumentClass = ontology.createClass(essayFormalInstrumentClassName);
		}

		superClass.addSubClass(essayFormalInstrumentClass);

		return essayFormalInstrumentClass;
	}

	@Override
	public Individual loadMaterialData(OntModel ontology, Individual individual, I material) {

		DatatypeProperty haveDescription = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);
		if (haveDescription == null) {
			haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);
		}

		Literal description = ontology.createTypedLiteral(material.getDescription(), XSDDatatype.XSDstring);

		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, haveDescription, description));

		ontology.add(statements);

		return individual;
	}

	/**
	 * La función encargada de cargar un instrumento de ensayo dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología a la que vamos a cargar el instrumento.
	 * @param instrumentClass
	 *            La clase padre del instrumento que vamos a cargar.
	 * @param instrumentIndividual
	 *            El individuo que corresponde con el instrumento.
	 * @param instrument
	 *            El instrumento que vamos a cargar.
	 * @return El individuo de la ontología que cargamos con los datos del instrumento.
	 */
	private Individual loadEssayInstrumentToOntology(OntModel ontology, OntClass instrumentClass, Individual instrumentIndividual,
			EssayActivityInstrument instrument) {
		// // Obtenemos la clase del instrumento que vamos a cargar.
		// OntClass instrumentClass = this.createClass(ontology);
		//
		// // Creamos la super clase de Instrumento.
		// String essayInstrumentClassName = ConstantsOntology.NAMESPACE + EssayInstrument.class.getSimpleName();
		// String essayInstrumentIndividualName = instrumentClassName + "_" + instrument.getId();
		//
		// // Creamos la clase y la instancia del instrumento.
		// OntClass instrumentClass = ontology.createClass(instrumentClassName);
		// Individual instrumentIndividual = instrumentClass.createIndividual(instrumentIndividualName);
		// // Las propiedades de los instrumentos de ensayo.
		// DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER);
		//
		// // Creamos las carga de los datos.
		// List<Statement> statements = new ArrayList<Statement>();
		// statements.add(ontology.createLiteralStatement(instrumentIndividual, haveAnswer,
		// this.loadEssayAnswerToOntology(ontology, instrument, instrument.getAnswer())));
		// ontology.add(statements);

		return instrumentIndividual;
	}
	//
	// /**
	// * La función encargada de cargar una respuesta a un instrumento de ensayo dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología donde vamos a cargar la respuesta.
	// * @param instrument
	// * El instrumento que contiene la respuesta.
	// * @param essayAnswer
	// * La respuesta que pertenece al instrumento.
	// * @return El individuo que creamos para guardar dentro de la ontología.
	// */
	// private Individual loadEssayAnswerToOntology(OntModel ontology, EssayActivityInstrument instrument, EssayActivityAnswer essayAnswer) {
	// // Creamos el nombre de la clase y del individuo.
	// String className = ConstantsOntology.NAMESPACE + essayAnswer.getClass().getSimpleName();
	// String individualName = className + "_" + essayAnswer.getId();
	//
	// // Creamos la clase y la instancia de la respuesta.
	// OntClass answerClass = ontology.createClass(className);
	// Individual answerIndividual = answerClass.createIndividual(individualName);
	//
	// // Creamos las relaciones.
	// DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION);
	//
	// // Creamos los literales.
	// Literal answer = ontology.createTypedLiteral(essayAnswer.getAnswer(), XSDDatatype.XSDstring);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// statements.add(ontology.createLiteralStatement(answerIndividual, haveAnswer, answer));
	//
	// ontology.add(statements);
	//
	// return answerIndividual;
	// }
}