package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.ObjectiveActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.ObjectiveActivityInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos que vamos a manejar dentro de la ontología.
 */
public abstract class ObjectiveActivityInstrumentRdfImpl<I extends ObjectiveActivityInstrument> extends FormalInstrumentRdfImpl<I> implements
		ObjectiveActivityInstrumentRdf<I> {

	private static final long serialVersionUID = 6932122018155450815L;

	/**
	 * La clase del instrumento formal de ensayo.
	 */
	private OntClass objectiveActivityInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String objectiveActivityInstrumentClassName = this.namespace + ObjectiveActivityInstrument.class.getSimpleName();
		this.objectiveActivityInstrumentClass = ontology.getOntClass(objectiveActivityInstrumentClassName);
		if (this.objectiveActivityInstrumentClass == null) {
			this.objectiveActivityInstrumentClass = ontology.createClass(objectiveActivityInstrumentClassName);

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.objectiveActivityInstrumentClass);
		}

		return this.objectiveActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}