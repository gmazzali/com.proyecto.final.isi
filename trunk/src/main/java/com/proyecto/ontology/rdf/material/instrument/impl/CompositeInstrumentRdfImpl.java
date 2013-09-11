package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.CompositeInstrument;
import com.proyecto.ontology.rdf.material.instrument.CompositeInstrumentRdf;
import com.proyecto.util.Constants;

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

	/**
	 * La clase de un instrumento semiformal compuesto.
	 */
	private OntClass compositeInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.compositeInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String compositeInstrumentClassName = Constants.NAMESPACE + CompositeInstrument.class.getSimpleName();
			this.compositeInstrumentClass = ontology.getOntClass(compositeInstrumentClassName);

			if (this.compositeInstrumentClass == null) {
				this.compositeInstrumentClass = ontology.createClass(compositeInstrumentClassName);
			}

			superClass.addSubClass(this.compositeInstrumentClass);
		}

		return this.compositeInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}
