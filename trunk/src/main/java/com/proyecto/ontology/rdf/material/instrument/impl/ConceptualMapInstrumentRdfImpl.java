package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;
import com.proyecto.ontology.rdf.material.instrument.ConceptualMapInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples de mapas conceptuales dentro de la
 * ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ConceptualMapInstrumentRdfImpl extends SimpleInstrumentRdfImpl<ConceptualMapInstrument> implements ConceptualMapInstrumentRdf {

	/**
	 * La clase de un instrumento semiformal simple de mapa conceptual.
	 */
	private OntClass conceptualMapInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.conceptualMapInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String conceptualMapInstrumentClassName = Constants.NAMESPACE + ConceptualMapInstrument.class.getSimpleName();
			this.conceptualMapInstrumentClass = ontology.getOntClass(conceptualMapInstrumentClassName);

			if (this.conceptualMapInstrumentClass == null) {
				this.conceptualMapInstrumentClass = ontology.createClass(conceptualMapInstrumentClassName);
			}

			superClass.addSubClass(this.conceptualMapInstrumentClass);
		}

		return this.conceptualMapInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, ConceptualMapInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}