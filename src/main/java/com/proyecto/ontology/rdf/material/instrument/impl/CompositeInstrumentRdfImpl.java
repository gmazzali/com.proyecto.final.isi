package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.CompositeInstrument;
import com.proyecto.ontology.rdf.material.instrument.CompositeInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales compuesto dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales compuesto que vamos a manejar dentro de la ontología.
 */
public abstract class CompositeInstrumentRdfImpl<I extends CompositeInstrument> extends SemiFormalInstrumentRdfImpl<I> implements
		CompositeInstrumentRdf<I> {

	private static final long serialVersionUID = 4614351162794504739L;

	/**
	 * La clase de un instrumento semiformal compuesto.
	 */
	private OntClass compositeInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String compositeInstrumentClassName = this.namespace + CompositeInstrument.class.getSimpleName();
		if (this.compositeInstrumentClass == null) {
			this.compositeInstrumentClass = ontology.getOntClass(compositeInstrumentClassName);
			if (this.compositeInstrumentClass == null) {
				this.compositeInstrumentClass = ontology.createClass(compositeInstrumentClassName);
			}

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.compositeInstrumentClass);
		}

		return this.compositeInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}
