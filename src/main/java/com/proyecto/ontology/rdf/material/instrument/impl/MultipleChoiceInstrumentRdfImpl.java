package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.ontology.rdf.material.instrument.MultipleChoiceInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de selección multiple dentro de la
 * ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class MultipleChoiceInstrumentRdfImpl extends ChoiceInstrumentRdfImpl<MultipleChoiceInstrument> implements MultipleChoiceInstrumentRdf {

	private static final long serialVersionUID = -9028203786061709849L;
	
	/**
	 * La clase del instrumento formal objetivo de selección multiple.
	 */
	private OntClass multipleChoiceInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.multipleChoiceInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String multipleChoiceInstrumentClassName = Constants.Ontology.NAMESPACE + MultipleChoiceInstrument.class.getSimpleName();
			this.multipleChoiceInstrumentClass = ontology.getOntClass(multipleChoiceInstrumentClassName);

			if (this.multipleChoiceInstrumentClass == null) {
				this.multipleChoiceInstrumentClass = ontology.createClass(multipleChoiceInstrumentClassName);
			}

			superClass.addSubClass(this.multipleChoiceInstrumentClass);
		}

		return this.multipleChoiceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, MultipleChoiceInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}