package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.ontology.rdf.material.instrument.SingleChoiceInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de selección simple dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class SingleChoiceInstrumentRdfImpl extends ChoiceInstrumentRdfImpl<SingleChoiceInstrument> implements SingleChoiceInstrumentRdf {

	private static final long serialVersionUID = -6547404846642123076L;
	
	/**
	 * La clase del instrumento formal objetivo de selección.
	 */
	private OntClass singleChoiceInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.singleChoiceInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String singleChoiceInstrumentClassName = Constants.Ontology.NAMESPACE + SingleChoiceInstrument.class.getSimpleName();
			this.singleChoiceInstrumentClass = ontology.getOntClass(singleChoiceInstrumentClassName);

			if (this.singleChoiceInstrumentClass == null) {
				this.singleChoiceInstrumentClass = ontology.createClass(singleChoiceInstrumentClassName);
			}

			superClass.addSubClass(this.singleChoiceInstrumentClass);
		}

		return this.singleChoiceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, SingleChoiceInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}