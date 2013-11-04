package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.ExerciseInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.ExerciseInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples de ejercicios dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ExerciseInstrumentRdfImpl extends SimpleInstrumentRdfImpl<ExerciseInstrument> implements ExerciseInstrumentRdf {

	private static final long serialVersionUID = -38293121719559319L;

	/**
	 * La clase de un instrumento semiformal simple de ejercicios.
	 */
	private OntClass exerciseInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String exerciseInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_EXERCISE;
		this.exerciseInstrumentClass = ontology.getOntClass(exerciseInstrumentClassName);
		if (this.exerciseInstrumentClass == null) {
			this.exerciseInstrumentClass = ontology.createClass(exerciseInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.exerciseInstrumentClass);

		return this.exerciseInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, ExerciseInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}