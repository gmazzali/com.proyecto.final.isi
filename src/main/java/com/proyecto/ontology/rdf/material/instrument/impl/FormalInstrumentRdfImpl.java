package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.FormalInstrument;
import com.proyecto.ontology.rdf.material.instrument.FormalInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales que vamos a manejar dentro de la ontología.
 */
public abstract class FormalInstrumentRdfImpl<I extends FormalInstrument> extends InstrumentRdfImpl<I> implements FormalInstrumentRdf<I> {

	private static final long serialVersionUID = 5874218485643129270L;

	/**
	 * La clase de un instrumento formal.
	 */
	private OntClass formalInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String formalInstrumentClassName = this.namespace + FormalInstrument.class.getSimpleName();
		this.formalInstrumentClass = ontology.getOntClass(formalInstrumentClassName);
		if (this.formalInstrumentClass == null) {
			this.formalInstrumentClass = ontology.createClass(formalInstrumentClassName);

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.formalInstrumentClass);
		}

		return this.formalInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}