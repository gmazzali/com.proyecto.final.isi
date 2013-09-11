package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.RestrictedEssayActivityInstrumentRdf;
import com.proyecto.util.Constants;

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
		if (this.restrictedEssayActivityInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String restrictedEssayActivityInstrumentClassName = Constants.NAMESPACE + RestrictedEssayActivityInstrument.class.getSimpleName();
			this.restrictedEssayActivityInstrumentClass = ontology.getOntClass(restrictedEssayActivityInstrumentClassName);

			if (this.restrictedEssayActivityInstrumentClass == null) {
				this.restrictedEssayActivityInstrumentClass = ontology.createClass(restrictedEssayActivityInstrumentClassName);
			}

			superClass.addSubClass(this.restrictedEssayActivityInstrumentClass);
		}

		return this.restrictedEssayActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, RestrictedEssayActivityInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}