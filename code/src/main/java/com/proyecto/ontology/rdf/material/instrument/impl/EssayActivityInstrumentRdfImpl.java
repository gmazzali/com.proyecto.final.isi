package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.EssayActivityInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.answer.EssayActivityAnswerRdf;
import com.proyecto.ontology.rdf.material.instrument.EssayActivityInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales de ensayos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales de ensayos que vamos a manejar dentro de la ontología.
 */
public abstract class EssayActivityInstrumentRdfImpl<I extends EssayActivityInstrument> extends FormalInstrumentRdfImpl<I> implements
		EssayActivityInstrumentRdf<I> {

	private static final long serialVersionUID = 2092146643897883340L;

	/**
	 * El servicio de las respuestas de ensayos.
	 */
	@Autowired
	private EssayActivityAnswerRdf essayActivityAnswerRdf;

	/**
	 * La clase del instrumento formal de ensayo.
	 */
	private OntClass essayActivityInstrumentClass;
	/**
	 * Las relaciones de la clase.
	 */
	private ObjectProperty haveAnswer;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String essayActivityInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY;
		this.essayActivityInstrumentClass = ontology.getOntClass(essayActivityInstrumentClassName);
		if (this.essayActivityInstrumentClass == null) {
			this.essayActivityInstrumentClass = ontology.createClass(essayActivityInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.essayActivityInstrumentClass);

		// Creamos las relaciones.
		String answer = this.namespace + OntologyConstants.PropertyName.INSTRUMENT_ESSAY_HAS_ANSWER;
		this.haveAnswer = ontology.getObjectProperty(answer);
		if (this.haveAnswer == null) {
			this.haveAnswer = ontology.createObjectProperty(answer);
			this.haveAnswer.setDomain(this.essayActivityInstrumentClass);
			this.haveAnswer.setRange(this.essayActivityAnswerRdf.initClass(ontology));
		}

		return this.essayActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveAnswer,
				this.essayActivityAnswerRdf.createIndividual(ontology, entity.getAnswer())));

		ontology.add(statements);

		return individual;
	}
}