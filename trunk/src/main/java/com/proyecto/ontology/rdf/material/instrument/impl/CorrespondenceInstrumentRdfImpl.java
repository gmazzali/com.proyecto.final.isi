package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;
import com.proyecto.ontology.rdf.material.instrument.CorrespondenceInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de correspondencia dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class CorrespondenceInstrumentRdfImpl extends ObjectiveActivityInstrumentRdfImpl<CorrespondenceInstrument> implements
		CorrespondenceInstrumentRdf {

	private static final long serialVersionUID = -1498928921943169924L;

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
		String correspondenceInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CORRESPONDENCE;
		this.correspondenceInstrumentClass = ontology.getOntClass(correspondenceInstrumentClassName);
		if (this.correspondenceInstrumentClass == null) {
			this.correspondenceInstrumentClass = ontology.createClass(correspondenceInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.correspondenceInstrumentClass);

		// Creamos las relaciones.
		String relations = this.namespace + OntologyConstants.PropertyName.INSTRUMENT_CORRESPONDENCE_HAS_RELATION;
		this.haveRelation = ontology.getObjectProperty(relations);
		if (this.haveRelation == null) {
			this.haveRelation = ontology.createObjectProperty(relations);
			this.haveRelation.addDomain(this.correspondenceInstrumentClass);
			this.haveRelation.addRange(this.relationAnswerRdf.initClass(ontology));
		}

		return this.correspondenceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, CorrespondenceInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();

		if (entity.getRelations() != null && !entity.getRelations().isEmpty()) {
			RDFNode[] completesNodes = new RDFNode[entity.getRelations().size()];
			int index = 0;
			for (RelationAnswer answer : entity.getRelations()) {
				completesNodes[index++] = this.relationAnswerRdf.createIndividual(ontology, answer);
			}
			statements.add(ontology.createLiteralStatement(individual, this.haveRelation, ontology.createList(completesNodes)));
		}

		ontology.add(statements);

		return individual;
	}
}