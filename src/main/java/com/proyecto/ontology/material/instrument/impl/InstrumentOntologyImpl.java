package com.proyecto.ontology.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import com.common.util.annotations.Service;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.ontology.material.MaterialOntologyImpl;
import com.proyecto.ontology.material.instrument.InstrumentOntology;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de instrumentos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class InstrumentOntologyImpl<I extends Instrument> extends MaterialOntologyImpl<I> implements InstrumentOntology<I> {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String instrumentClassName = ConstantsOntology.NAMESPACE + Instrument.class.getSimpleName();
		OntClass instrumentClass = ontology.getOntClass(instrumentClassName);

		if (instrumentClass == null) {
			instrumentClass = ontology.createClass(instrumentClassName);
		}

		superClass.addSubClass(instrumentClass);

		return instrumentClass;
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

	@Override
	public Individual loadInstrumentToOntology(OntModel ontology, Individual reactive, I instrument) {
		// Creamos la super clase de Instrumento.
		String instrumentClassName = ConstantsOntology.NAMESPACE + Instrument.class.getSimpleName();
		String instrumentIndividualName = instrumentClassName + "_" + instrument.getId();

		// Creamos la clase y la instancia del instrumento.
		OntClass instrumentClass = ontology.createClass(instrumentClassName);
		Individual instrumentIndividual = instrumentClass.createIndividual(instrumentIndividualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(instrument.getDescription(), XSDDatatype.XSDstring);

		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(instrumentIndividual, haveDescription, description));

		ontology.add(statements);

		return instrumentIndividual;
	}

	//
	// /**
	// * La función encargada de cargar un instrumento de selección dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología a la que vamos a cargar el instrumento.
	// * @param instrumentIndividual
	// * El individuo que corresponde con el instrumento.
	// * @param instrument
	// * El instrumento que vamos a cargar.
	// * @return El individuo de la ontología que cargamos con los datos del instrumento.
	// */
	// private Individual loadChoiceInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, ChoiceInstrument instrument) {
	// // Las propiedades de los instrumentos de selección.
	// DatatypeProperty haveOption = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// for (Option option : instrument.getOptions()) {
	// statements
	// .add(ontology.createLiteralStatement(instrumentIndividual, haveOption, this.loadOptionToOntology(ontology, instrument, option)));
	// }
	// ontology.add(statements);
	//
	// return instrumentIndividual;
	// }
	//
	// /**
	// * La función encargada de cargar las opciones dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología donde vamos a cargar la opción.
	// * @param instrument
	// * El instrumento al que pertenece la opción.
	// * @param option
	// * La opción que vamos a cargar.
	// * @return El individuo que contiene la opción que almacenamos dentro de la ontología.
	// */
	// private Individual loadOptionToOntology(OntModel ontology, ChoiceInstrument instrument, Option option) {
	// // Creamos el nombre de la clase y del individuo.
	// String className = ConstantsOntology.NAMESPACE + option.getClass().getSimpleName();
	// String individualName = className + "_" + option.getId();
	//
	// // Creamos la clase y la instancia de la opción.
	// OntClass optionClass = ontology.createClass(className);
	// Individual optionIndividual = optionClass.createIndividual(individualName);
	//
	// // Creamos las relaciones.
	// DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_DESCRIPTION);
	// DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_ANSWER);
	//
	// // Creamos los literales.
	// Literal description = ontology.createTypedLiteral(option.getDescription(), XSDDatatype.XSDstring);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// statements.add(ontology.createLiteralStatement(optionIndividual, haveDescription, description));
	// statements.add(ontology.createLiteralStatement(optionIndividual, haveAnswer,
	// this.loadTrueFalseAnswerToOntology(ontology, option, option.getTrueFalseAnswer())));
	//
	// ontology.add(statements);
	//
	// return optionIndividual;
	// }
	//
	// /**
	// * La función encargada de cargar una respuesta booleana dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología donde vamos a almacenar la respuesta.
	// * @param option
	// * La opción a la que pertenece la respuesta booleana.
	// * @param trueFalseAnswer
	// * La respuesta booleana a almacenar.
	// * @return El individuo que almacenamos dentro de la ontología.
	// */
	// private Individual loadTrueFalseAnswerToOntology(OntModel ontology, Option option, TrueFalseAnswer trueFalseAnswer) {
	// // Creamos el nombre de la clase y del individuo.
	// String className = ConstantsOntology.NAMESPACE + trueFalseAnswer.getClass().getSimpleName();
	// String individualName = className + "_" + trueFalseAnswer.getId();
	//
	// // Creamos la clase y la instancia de la respuesta.
	// OntClass answerClass = ontology.createClass(className);
	// Individual answerIndividual = answerClass.createIndividual(individualName);
	//
	// // Creamos las relaciones.
	// DatatypeProperty haveValue = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);
	//
	// // Creamos los literales.
	// Literal value = ontology.createTypedLiteral(trueFalseAnswer.getValue(), XSDDatatype.XSDboolean);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// statements.add(ontology.createLiteralStatement(answerIndividual, haveValue, value));
	//
	// ontology.add(statements);
	//
	// return answerIndividual;
	// }
	//
	// /**
	// * La función encargada de cargar un instrumento de correspondencia dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología a la que vamos a cargar el instrumento.
	// * @param instrumentIndividual
	// * El individuo que corresponde con el instrumento.
	// * @param instrument
	// * El instrumento que vamos a cargar.
	// * @return El individuo de la ontología que cargamos con los datos del instrumento.
	// */
	// private Individual loadCorrespondenceInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, CorrespondenceInstrument
	// instrument) {
	// // Las propiedades de los instrumentos de correspondencia.
	// DatatypeProperty haveRelation = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// for (RelationAnswer answer : instrument.getRelations()) {
	// statements.add(ontology.createLiteralStatement(instrumentIndividual, haveRelation,
	// this.loadRelationAnswerToOntology(ontology, instrument, answer)));
	// }
	// ontology.add(statements);
	//
	// return instrumentIndividual;
	// }
	//
	// /**
	// * La funcion encargada de cargar una respuesta de relación dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología donde vamos a cargar la relación.
	// * @param instrument
	// * El instrumento al que pertenece la relación.
	// * @param relationAnswer
	// * La relación que vamos a cargar.
	// * @return El individuo que cargamos en la ontología con los datos de la relación.
	// */
	// private Individual loadRelationAnswerToOntology(OntModel ontology, CorrespondenceInstrument instrument, RelationAnswer relationAnswer) {
	// // Creamos el nombre de la clase y del individuo.
	// String className = ConstantsOntology.NAMESPACE + relationAnswer.getClass().getSimpleName();
	// String individualName = className + "_" + relationAnswer.getId();
	//
	// // Creamos la clase y la instancia de la respuesta.
	// OntClass answerClass = ontology.createClass(className);
	// Individual answerIndividual = answerClass.createIndividual(individualName);
	//
	// // Creamos las relaciones.
	// DatatypeProperty haveLeftSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_LEFT_SIDE);
	// DatatypeProperty haveRightSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_RIGHT_SIDE);
	//
	// // Creamos los literales y los cargamos.
	// List<Statement> statements = new ArrayList<Statement>();
	// Literal leftSide = null;
	// Literal rightSide = null;
	// if (relationAnswer.getLeftSide() != null) {
	// leftSide = ontology.createTypedLiteral(relationAnswer.getLeftSide(), XSDDatatype.XSDstring);
	// statements.add(ontology.createLiteralStatement(answerIndividual, haveLeftSide, leftSide));
	// }
	// if (relationAnswer.getRightSide() != null) {
	// rightSide = ontology.createTypedLiteral(relationAnswer.getRightSide(), XSDDatatype.XSDstring);
	// statements.add(ontology.createLiteralStatement(answerIndividual, haveRightSide, rightSide));
	// }
	//
	// ontology.add(statements);
	//
	// return answerIndividual;
	// }
	//
	// /**
	// * La función encargada de cargar un instrumento de completar dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología a la que vamos a cargar el instrumento.
	// * @param instrumentIndividual
	// * El individuo que corresponde con el instrumento.
	// * @param instrument
	// * El instrumento que vamos a cargar.
	// * @return El individuo de la ontología que cargamos con los datos del instrumento.
	// */
	// private Individual loadCompletionInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, CompletionInstrument instrument) {
	// // Las propiedades de los instrumentos de completar.
	// DatatypeProperty haveCompletion = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// for (CompletionAnswer answer : instrument.getAnswers()) {
	// statements.add(ontology.createLiteralStatement(instrumentIndividual, haveCompletion,
	// this.loadCompletionAnswerToOntology(ontology, instrument, answer)));
	// }
	// ontology.add(statements);
	//
	// return instrumentIndividual;
	// }
	//
	// /**
	// * La función encargada de cargar una respuesta para completar dentro de la ontología.
	// *
	// * @param ontology
	// * La ontología donde vamos a cargar la respuesta.
	// * @param instrument
	// * El instrumento al que pertenece la respuesta.
	// * @param completionAnswer
	// * La respuesta que vamos a cargar.
	// * @return El individuo que representa la respuesta que agregamos dentro de la ontología.
	// */
	// private Individual loadCompletionAnswerToOntology(OntModel ontology, CompletionInstrument instrument, CompletionAnswer completionAnswer) {
	// // Creamos el nombre de la clase y del individuo.
	// String className = ConstantsOntology.NAMESPACE + completionAnswer.getClass().getSimpleName();
	// String individualName = className + "_" + completionAnswer.getId();
	//
	// // Creamos la clase y la instancia de la respuesta.
	// OntClass answerClass = ontology.createClass(className);
	// Individual answerIndividual = answerClass.createIndividual(individualName);
	//
	// // Creamos las relaciones.
	// DatatypeProperty haveIndex = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_INDEX);
	// DatatypeProperty havePhrase = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE);
	//
	// // Creamos los literales.
	// Literal index = ontology.createTypedLiteral(completionAnswer.getIndex(), XSDDatatype.XSDstring);
	// Literal phrase = ontology.createTypedLiteral(completionAnswer.getPhrase(), XSDDatatype.XSDstring);
	//
	// // Creamos las carga de los datos.
	// List<Statement> statements = new ArrayList<Statement>();
	// statements.add(ontology.createLiteralStatement(answerIndividual, haveIndex, index));
	// statements.add(ontology.createLiteralStatement(answerIndividual, havePhrase, phrase));
	//
	// ontology.add(statements);
	//
	// return answerIndividual;
	// }
}