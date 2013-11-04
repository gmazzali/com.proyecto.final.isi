package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.SingleChoiceInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de selección simple dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class SingleChoiceInstrumentRdfImpl extends ChoiceInstrumentRdfImpl<SingleChoiceInstrument> implements SingleChoiceInstrumentRdf {

	private static final long serialVersionUID = -6547404846642123076L;

	/**
	 * La clase del instrumento formal objetivo de selección.
	 */
	private OntClass singleChoiceInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String singleChoiceInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_SINGLE;
		this.singleChoiceInstrumentClass = ontology.getOntClass(singleChoiceInstrumentClassName);
		if (this.singleChoiceInstrumentClass == null) {
			this.singleChoiceInstrumentClass = ontology.createClass(singleChoiceInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.singleChoiceInstrumentClass);

		return this.singleChoiceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, SingleChoiceInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}