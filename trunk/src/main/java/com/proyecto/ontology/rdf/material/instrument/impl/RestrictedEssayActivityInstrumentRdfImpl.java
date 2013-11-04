package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.RestrictedEssayActivityInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales de ensayos restringidos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class RestrictedEssayActivityInstrumentRdfImpl extends EssayActivityInstrumentRdfImpl<RestrictedEssayActivityInstrument> implements
		RestrictedEssayActivityInstrumentRdf {

	private static final long serialVersionUID = 1748741402462060611L;

	/**
	 * La clase del instrumento formal de ensayo restringido.
	 */
	private OntClass restrictedEssayActivityInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String restrictedEssayActivityInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY_RESTRICTED;
		this.restrictedEssayActivityInstrumentClass = ontology.getOntClass(restrictedEssayActivityInstrumentClassName);
		if (this.restrictedEssayActivityInstrumentClass == null) {
			this.restrictedEssayActivityInstrumentClass = ontology.createClass(restrictedEssayActivityInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.restrictedEssayActivityInstrumentClass);

		return this.restrictedEssayActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, RestrictedEssayActivityInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}