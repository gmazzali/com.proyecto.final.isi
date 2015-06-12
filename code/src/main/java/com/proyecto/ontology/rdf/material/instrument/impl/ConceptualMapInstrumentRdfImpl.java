package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.ConceptualMapInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples de mapas conceptuales dentro de la
 * ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ConceptualMapInstrumentRdfImpl extends SimpleInstrumentRdfImpl<ConceptualMapInstrument> implements ConceptualMapInstrumentRdf {

	private static final long serialVersionUID = 6629093865869793170L;

	/**
	 * La clase de un instrumento semiformal simple de mapa conceptual.
	 */
	private OntClass conceptualMapInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String conceptualMapInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_CONCEPTUAL_MAP;
		this.conceptualMapInstrumentClass = ontology.getOntClass(conceptualMapInstrumentClassName);
		if (this.conceptualMapInstrumentClass == null) {
			this.conceptualMapInstrumentClass = ontology.createClass(conceptualMapInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.conceptualMapInstrumentClass);

		return this.conceptualMapInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, ConceptualMapInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}