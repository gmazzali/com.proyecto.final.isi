package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.ontology.rdf.answer.CompletionAnswerRdf;
import com.proyecto.ontology.rdf.material.instrument.CompletionInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de correspondencia dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class CompletionInstrumentRdfImpl extends ObjectiveActivityInstrumentRdfImpl<CompletionInstrument> implements CompletionInstrumentRdf {

	private static final long serialVersionUID = 9078655177679015093L;
	
	/**
	 * El servicio de ontología para las respuestas de completar.
	 */
	@Autowired
	private CompletionAnswerRdf completionAnswerRdf;

	/**
	 * La clase del instrumento objetivo de correspondencia.
	 */
	private OntClass completionInstrumentClass;
	/**
	 * Las relaciones del instrumento objetivo de correspondencia.
	 */
	private ObjectProperty haveComplete;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.completionInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String completionInstrumentClassName = Constants.NAMESPACE + CompletionInstrument.class.getSimpleName();
			this.completionInstrumentClass = ontology.getOntClass(completionInstrumentClassName);

			if (this.completionInstrumentClass == null) {
				this.completionInstrumentClass = ontology.createClass(completionInstrumentClassName);
			}

			superClass.addSubClass(this.completionInstrumentClass);
		}

		// Creamos las relaciones.
		if (this.haveComplete == null) {
			this.haveComplete = ontology.getObjectProperty(Constants.PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE);
			if (this.haveComplete == null) {
				this.haveComplete = ontology.createObjectProperty(Constants.PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE);
				this.haveComplete.addDomain(this.completionInstrumentClass);
				this.haveComplete.addRange(this.completionAnswerRdf.initClass(ontology));
			}
		}

		return this.completionInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, CompletionInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (CompletionAnswer answer : entity.getAnswers()) {
			statements
					.add(ontology.createLiteralStatement(individual, this.haveComplete, this.completionAnswerRdf.createIndividual(ontology, answer)));
		}
		ontology.add(statements);

		return individual;
	}
}