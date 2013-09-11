package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.EssayInstrument;
import com.proyecto.ontology.rdf.material.instrument.EssayInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos semiformales simples de ensayos dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class EssayInstrumentRdfImpl extends SimpleInstrumentRdfImpl<EssayInstrument> implements EssayInstrumentRdf {

	/**
	 * La clase de un instrumento semiformal simple de ensayo.
	 */
	private OntClass essayInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.essayInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String essayInstrumentClassName = Constants.NAMESPACE + EssayInstrument.class.getSimpleName();
			this.essayInstrumentClass = ontology.getOntClass(essayInstrumentClassName);

			if (this.essayInstrumentClass == null) {
				this.essayInstrumentClass = ontology.createClass(essayInstrumentClassName);
			}

			superClass.addSubClass(this.essayInstrumentClass);
		}

		return this.essayInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, EssayInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}