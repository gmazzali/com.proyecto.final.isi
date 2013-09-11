package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.SimpleInstrument;
import com.proyecto.ontology.rdf.material.instrument.SimpleInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales simples que vamos a manejar dentro de la ontología.
 */
public abstract class SimpleInstrumentRdfImpl<I extends SimpleInstrument> extends SemiFormalInstrumentRdfImpl<I> implements SimpleInstrumentRdf<I> {

	private static final long serialVersionUID = 2304551219800176396L;

	/**
	 * La clase de un instrumento semiformal simple.
	 */
	private OntClass simpleInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.simpleInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String simpleInstrumentClassName = Constants.NAMESPACE + SimpleInstrument.class.getSimpleName();
			this.simpleInstrumentClass = ontology.getOntClass(simpleInstrumentClassName);

			if (this.simpleInstrumentClass == null) {
				this.simpleInstrumentClass = ontology.createClass(simpleInstrumentClassName);
			}

			superClass.addSubClass(this.simpleInstrumentClass);
		}

		return this.simpleInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}