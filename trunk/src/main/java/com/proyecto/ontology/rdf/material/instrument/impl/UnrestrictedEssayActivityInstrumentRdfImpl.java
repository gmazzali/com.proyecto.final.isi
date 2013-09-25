package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.UnrestrictedEssayActivityInstrumentRdf;
import com.proyecto.util.Constants;

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
		if (this.unrestrictedEssayActivityInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String unrestrictedEssayActivityInstrumentClassName = Constants.Ontology.NAMESPACE
					+ UnrestrictedEssayActivityInstrument.class.getSimpleName();
			this.unrestrictedEssayActivityInstrumentClass = ontology.getOntClass(unrestrictedEssayActivityInstrumentClassName);

			if (this.unrestrictedEssayActivityInstrumentClass == null) {
				this.unrestrictedEssayActivityInstrumentClass = ontology.createClass(unrestrictedEssayActivityInstrumentClassName);
			}

			superClass.addSubClass(this.unrestrictedEssayActivityInstrumentClass);
		}

		return this.unrestrictedEssayActivityInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, UnrestrictedEssayActivityInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}