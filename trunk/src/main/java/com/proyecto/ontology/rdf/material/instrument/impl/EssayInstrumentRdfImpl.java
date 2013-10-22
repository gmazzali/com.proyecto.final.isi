package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.EssayInstrument;
import com.proyecto.ontology.rdf.material.instrument.EssayInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples de ensayos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class EssayInstrumentRdfImpl extends SimpleInstrumentRdfImpl<EssayInstrument> implements EssayInstrumentRdf {

	private static final long serialVersionUID = -6286660102531768307L;

	/**
	 * La clase de un instrumento semiformal simple de ensayo.
	 */
	private OntClass essayInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String essayInstrumentClassName = this.namespace + EssayInstrument.class.getSimpleName();
		if (this.essayInstrumentClass == null) {
			this.essayInstrumentClass = ontology.getOntClass(essayInstrumentClassName);
			if (this.essayInstrumentClass == null) {
				this.essayInstrumentClass = ontology.createClass(essayInstrumentClassName);
			}

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.essayInstrumentClass);
		}

		return this.essayInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, EssayInstrument entity) {
		// Cargamos el padre.
		return super.loadEntityData(ontology, individual, entity);
	}
}