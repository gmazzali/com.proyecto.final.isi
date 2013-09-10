package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.Distractor;
import com.proyecto.ontology.rdf.option.DistractorRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones falsas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class DistractorRdfImpl extends OptionRdfImpl<Distractor> implements DistractorRdf {

	/**
	 * La clase de una opción falsa.
	 */
	private OntClass distractorOptionClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.distractorOptionClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String distractorOptionClassName = ConstantsOntology.NAMESPACE + Distractor.class.getSimpleName();
			this.distractorOptionClass = ontology.getOntClass(distractorOptionClassName);

			if (this.distractorOptionClass == null) {
				this.distractorOptionClass = ontology.createClass(distractorOptionClassName);
			}

			superClass.addSubClass(this.distractorOptionClass);
		}
		return this.distractorOptionClass;
	}
	
	@Override
	public Individual createIndividual(OntModel ontology, Distractor entity) {
		return super.createIndividual(ontology, entity);
	}
}