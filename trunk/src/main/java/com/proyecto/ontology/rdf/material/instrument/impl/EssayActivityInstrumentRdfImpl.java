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
import com.proyecto.ontology.rdf.answer.EssayActivityAnswerRdf;
import com.proyecto.ontology.rdf.material.instrument.EssayActivityInstrumentRdf;
import com.proyecto.util.Constants;

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
		if (this.essayActivityInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String essayActivityInstrumentClassName = Constants.Ontology.NAMESPACE + EssayActivityInstrument.class.getSimpleName();
			this.essayActivityInstrumentClass = ontology.getOntClass(essayActivityInstrumentClassName);

			if (this.essayActivityInstrumentClass == null) {
				this.essayActivityInstrumentClass = ontology.createClass(essayActivityInstrumentClassName);
			}

			superClass.addSubClass(this.essayActivityInstrumentClass);
		}

		// Creamos las relaciones.
		if (this.haveAnswer == null) {
			this.haveAnswer = ontology.getObjectProperty(Constants.Ontology.PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER);
			if (this.haveAnswer == null) {
				this.haveAnswer = ontology.createObjectProperty(Constants.Ontology.PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER);
				this.haveAnswer.setDomain(this.essayActivityInstrumentClass);
				this.haveAnswer.setRange(this.essayActivityAnswerRdf.initClass(ontology));
			}
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