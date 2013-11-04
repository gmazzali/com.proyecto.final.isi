package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.SemiFormalInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.SemiFormalInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales que vamos a manejar dentro de la ontología.
 */
public abstract class SemiFormalInstrumentRdfImpl<I extends SemiFormalInstrument> extends InstrumentRdfImpl<I> implements SemiFormalInstrumentRdf<I> {

	private static final long serialVersionUID = 6549137300525586052L;

	/**
	 * La clase de un instrumento semiformal.
	 */
	private OntClass semiformalInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String semiformalInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL;
		this.semiformalInstrumentClass = ontology.getOntClass(semiformalInstrumentClassName);
		if (this.semiformalInstrumentClass == null) {
			this.semiformalInstrumentClass = ontology.createClass(semiformalInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.semiformalInstrumentClass);

		return this.semiformalInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}