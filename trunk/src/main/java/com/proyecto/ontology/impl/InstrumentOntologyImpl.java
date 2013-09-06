package com.proyecto.ontology.impl;

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
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.EssayActivityInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.option.Option;
import com.proyecto.ontology.InstrumentOntology;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de instrumentos dentro de una ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class InstrumentOntologyImpl implements InstrumentOntology {

	@Override
	public <I extends Instrument> Individual loadInstrumentToOntology(OntModel ontology, Reactive reactive, I instrument) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + instrument.getClass().getSimpleName();
		String individualName = className + "_" + instrument.getId();

		// Creamos la clase y la instancia del instrumento.
		OntClass instrumentClass = ontology.createClass(className);
		Individual instrumentIndividual = instrumentClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(instrument.getDescription(), XSDDatatype.XSDstring);

		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(instrumentIndividual, haveDescription, description));

		ontology.add(statements);

		// Corroboramos las instancia del instrumento.
		if (instrument instanceof EssayActivityInstrument) {
			this.loadEssayInstrumentToOntology(ontology, instrumentIndividual, (EssayActivityInstrument) instrument);
		} else if (instrument instanceof ChoiceInstrument) {
			this.loadChoiceInstrumentToOntology(ontology, instrumentIndividual, (ChoiceInstrument) instrument);
		} else if (instrument instanceof CorrespondenceInstrument) {
			this.loadCorrespondenceInstrumentToOntology(ontology, instrumentIndividual, (CorrespondenceInstrument) instrument);
		} else if (instrument instanceof CompletionInstrument) {
			this.loadCompletionInstrumentToOntology(ontology, instrumentIndividual, (CompletionInstrument) instrument);
		}

		return instrumentIndividual;
	}

	/**
	 * La funci�n encargada de cargar un instrumento de ensayo dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a a la que vamos a cargar el instrumento.
	 * @param instrumentIndividual
	 *            El individuo que corresponde con el instrumento.
	 * @param instrument
	 *            El instrumento que vamos a cargar.
	 * @return El individuo de la ontolog�a que cargamos con los datos del instrumento.
	 */
	private Individual loadEssayInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, EssayActivityInstrument instrument) {
		// Las propiedades de los instrumentos de ensayo.
		DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(instrumentIndividual, haveAnswer,
				this.loadEssayAnswerToOntology(ontology, instrument, instrument.getAnswer())));
		ontology.add(statements);

		return instrumentIndividual;
	}

	/**
	 * La funci�n encargada de cargar una respuesta a un isntrumento de ensayo dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la respuesta.
	 * @param instrument
	 *            El instrumento que contiene la respuesta.
	 * @param essayAnswer
	 *            La respuesta que pertenece al instrumento.
	 * @return El individuo que creamos para guardar dentro de la ontolog�a.
	 */
	private Individual loadEssayAnswerToOntology(OntModel ontology, EssayActivityInstrument instrument, EssayActivityAnswer essayAnswer) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + essayAnswer.getClass().getSimpleName();
		String individualName = className + "_" + essayAnswer.getId();

		// Creamos la clase y la instancia de la respuesta.
		OntClass answerClass = ontology.createClass(className);
		Individual answerIndividual = answerClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION);

		// Creamos los literales.
		Literal answer = ontology.createTypedLiteral(essayAnswer.getAnswer(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(answerIndividual, haveAnswer, answer));

		ontology.add(statements);

		return answerIndividual;
	}

	/**
	 * La funci�n encargada de cargar un instrumento de selecci�n dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a a la que vamos a cargar el instrumento.
	 * @param instrumentIndividual
	 *            El individuo que corresponde con el instrumento.
	 * @param instrument
	 *            El instrumento que vamos a cargar.
	 * @return El individuo de la ontolog�a que cargamos con los datos del instrumento.
	 */
	private Individual loadChoiceInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, ChoiceInstrument instrument) {
		// Las propiedades de los instrumentos de selecci�n.
		DatatypeProperty haveOption = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (Option option : instrument.getOptions()) {
			statements
					.add(ontology.createLiteralStatement(instrumentIndividual, haveOption, this.loadOptionToOntology(ontology, instrument, option)));
		}
		ontology.add(statements);

		return instrumentIndividual;
	}

	/**
	 * La funci�n encargada de cargar las opciones dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la opci�n.
	 * @param instrument
	 *            El instrumento al que pertenece la opci�n.
	 * @param option
	 *            La opci�n que vamos a cargar.
	 * @return El individuo que contiene la opci�n que almacenamos dentro de la ontolog�a.
	 */
	private Individual loadOptionToOntology(OntModel ontology, ChoiceInstrument instrument, Option option) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + option.getClass().getSimpleName();
		String individualName = className + "_" + option.getId();

		// Creamos la clase y la instancia de la opci�n.
		OntClass optionClass = ontology.createClass(className);
		Individual optionIndividual = optionClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_DESCRIPTION);
		DatatypeProperty haveAnswer = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_OPTION_HAVE_ANSWER);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(option.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(optionIndividual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(optionIndividual, haveAnswer,
				this.loadTrueFalseAnswerToOntology(ontology, option, option.getTrueFalseAnswer())));

		ontology.add(statements);

		return optionIndividual;
	}

	/**
	 * La funci�n encargada de cargar una respuesta booleana dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a almacenar la respuesta.
	 * @param option
	 *            La opci�n a la que pertenece la respuesta booleana.
	 * @param trueFalseAnswer
	 *            La respuesta booleana a almacenar.
	 * @return El individuo que almacenamos dentro de la ontolog�a.
	 */
	private Individual loadTrueFalseAnswerToOntology(OntModel ontology, Option option, TrueFalseAnswer trueFalseAnswer) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + trueFalseAnswer.getClass().getSimpleName();
		String individualName = className + "_" + trueFalseAnswer.getId();

		// Creamos la clase y la instancia de la respuesta.
		OntClass answerClass = ontology.createClass(className);
		Individual answerIndividual = answerClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveValue = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);

		// Creamos los literales.
		Literal value = ontology.createTypedLiteral(trueFalseAnswer.getValue(), XSDDatatype.XSDboolean);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(answerIndividual, haveValue, value));

		ontology.add(statements);

		return answerIndividual;
	}

	/**
	 * La funci�n encargada de cargar un instrumento de correspondencia dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a a la que vamos a cargar el instrumento.
	 * @param instrumentIndividual
	 *            El individuo que corresponde con el instrumento.
	 * @param instrument
	 *            El instrumento que vamos a cargar.
	 * @return El individuo de la ontolog�a que cargamos con los datos del instrumento.
	 */
	private Individual loadCorrespondenceInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, CorrespondenceInstrument instrument) {
		// Las propiedades de los instrumentos de correspondencia.
		DatatypeProperty haveRelation = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (RelationAnswer answer : instrument.getRelations()) {
			statements.add(ontology.createLiteralStatement(instrumentIndividual, haveRelation,
					this.loadRelationAnswerToOntology(ontology, instrument, answer)));
		}
		ontology.add(statements);

		return instrumentIndividual;
	}

	/**
	 * La funcion encargada de cargar una respuesta de relaci�n dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la relaci�n.
	 * @param instrument
	 *            El instrumento al que pertenece la relaci�n.
	 * @param relationAnswer
	 *            La relaci�n que vamos a cargar.
	 * @return El individuo que cargamos en la ontolog�a con los datos de la relaci�n.
	 */
	private Individual loadRelationAnswerToOntology(OntModel ontology, CorrespondenceInstrument instrument, RelationAnswer relationAnswer) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + relationAnswer.getClass().getSimpleName();
		String individualName = className + "_" + relationAnswer.getId();

		// Creamos la clase y la instancia de la respuesta.
		OntClass answerClass = ontology.createClass(className);
		Individual answerIndividual = answerClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveLeftSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_LEFT_SIDE);
		DatatypeProperty haveRightSide = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_RELATION_RIGHT_SIDE);

		// Creamos los literales.
		Literal leftSide = ontology.createTypedLiteral(relationAnswer.getLeftSide(), XSDDatatype.XSDstring);
		Literal rightSide = ontology.createTypedLiteral(relationAnswer.getRightSide(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(answerIndividual, haveLeftSide, leftSide));
		statements.add(ontology.createLiteralStatement(answerIndividual, haveRightSide, rightSide));

		ontology.add(statements);

		return answerIndividual;
	}

	/**
	 * La funci�n encargada de cargar un instrumento de completar dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a a la que vamos a cargar el instrumento.
	 * @param instrumentIndividual
	 *            El individuo que corresponde con el instrumento.
	 * @param instrument
	 *            El instrumento que vamos a cargar.
	 * @return El individuo de la ontolog�a que cargamos con los datos del instrumento.
	 */
	private Individual loadCompletionInstrumentToOntology(OntModel ontology, Individual instrumentIndividual, CompletionInstrument instrument) {
		// Las propiedades de los instrumentos de completar.
		DatatypeProperty haveCompletion = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (CompletionAnswer answer : instrument.getAnswers()) {
			statements.add(ontology.createLiteralStatement(instrumentIndividual, haveCompletion,
					this.loadCompletionAnswerToOntology(ontology, instrument, answer)));
		}
		ontology.add(statements);

		return instrumentIndividual;
	}

	/**
	 * La funci�n encargada de cargar una respuesta para completar dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la respuesta.
	 * @param instrument
	 *            El instrumento al que pertenece la respuesta.
	 * @param completionAnswer
	 *            La respuesta que vamos a cargar.
	 * @return El individuo que representa la respuesta que agregamos dentro de la ontolog�a.
	 */
	private Individual loadCompletionAnswerToOntology(OntModel ontology, CompletionInstrument instrument, CompletionAnswer completionAnswer) {
		// Creamos el nombre de la clase y del individuo.
		String className = ConstantsOntology.NAMESPACE + completionAnswer.getClass().getSimpleName();
		String individualName = className + "_" + completionAnswer.getId();

		// Creamos la clase y la instancia de la respuesta.
		OntClass answerClass = ontology.createClass(className);
		Individual answerIndividual = answerClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveIndex = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_INDEX);
		DatatypeProperty havePhrase = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE);

		// Creamos los literales.
		Literal index = ontology.createTypedLiteral(completionAnswer.getIndex(), XSDDatatype.XSDstring);
		Literal phrase = ontology.createTypedLiteral(completionAnswer.getPhrase(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(answerIndividual, haveIndex, index));
		statements.add(ontology.createLiteralStatement(answerIndividual, havePhrase, phrase));

		ontology.add(statements);

		return answerIndividual;
	}
}