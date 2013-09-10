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
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;
import com.proyecto.ontology.rdf.material.instrument.CorrespondenceInstrumentRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de correspondencia dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class CorrespondenceInstrumentRdfImpl extends ObjectiveActivityInstrumentRdfImpl<CorrespondenceInstrument> implements
		CorrespondenceInstrumentRdf {

	/**
	 * El servicio de ontología para las respuestas de correspondencia.
	 */
	@Autowired
	private RelationAnswerRdf relationAnswerRdf;

	/**
	 * La clase del instrumento objetivo de correspondencia.
	 */
	private OntClass correspondenceInstrumentClass;
	/**
	 * Las relaciones del instrumento objetivo de correspondencia.
	 */
	private ObjectProperty haveRelation;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.correspondenceInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String correspondenceInstrumentClassName = ConstantsOntology.NAMESPACE + CorrespondenceInstrument.class.getSimpleName();
			this.correspondenceInstrumentClass = ontology.getOntClass(correspondenceInstrumentClassName);

			if (this.correspondenceInstrumentClass == null) {
				this.correspondenceInstrumentClass = ontology.createClass(correspondenceInstrumentClassName);
			}

			superClass.addSubClass(this.correspondenceInstrumentClass);
		}

		// Creamos las relaciones.
		if (this.haveRelation == null) {
			this.haveRelation = ontology.getObjectProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION);
			if (this.haveRelation == null) {
				this.haveRelation = ontology.createObjectProperty(ConstantsOntology.PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION);
			}
		}

		return this.correspondenceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, CorrespondenceInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (RelationAnswer answer : entity.getRelations()) {
			statements.add(ontology.createLiteralStatement(individual, this.haveRelation, this.relationAnswerRdf.createIndividual(ontology, answer)));
		}
		ontology.add(statements);

		return individual;
	}
}