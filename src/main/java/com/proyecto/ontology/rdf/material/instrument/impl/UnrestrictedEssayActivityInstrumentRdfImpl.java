package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.UnrestrictedEssayActivityInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales de ensayos no restringidos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class UnrestrictedEssayActivityInstrumentRdfImpl extends EssayActivityInstrumentRdfImpl<UnrestrictedEssayActivityInstrument> implements
		UnrestrictedEssayActivityInstrumentRdf {

	private static final long serialVersionUID = -2550089483112812514L;

	/**
	 * La clase del instrumento formal de ensayo no restringido.
	 */
	private OntClass unrestrictedEssayActivityInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String unrestrictedEssayActivityInstrumentClassName = this.namespace + UnrestrictedEssayActivityInstrument.class.getSimpleName();
		this.unrestrictedEssayActivityInstrumentClass = ontology.getOntClass(unrestrictedEssayActivityInstrumentClassName);
		if (this.unrestrictedEssayActivityInstrumentClass == null) {
			this.unrestrictedEssayActivityInstrumentClass = ontology.createClass(unrestrictedEssayActivityInstrumentClassName);

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.unrestrictedEssayActivityInstrumentClass);
		}

		return this.unrestrictedEssayActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, UnrestrictedEssayActivityInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}