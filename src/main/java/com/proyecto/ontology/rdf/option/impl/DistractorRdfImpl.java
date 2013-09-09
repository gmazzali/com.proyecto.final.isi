package com.proyecto.ontology.rdf.option.impl;

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

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String distractorOptionClassName = ConstantsOntology.NAMESPACE + Distractor.class.getSimpleName();
		OntClass distractorOptionClass = ontology.getOntClass(distractorOptionClassName);

		if (distractorOptionClass == null) {
			distractorOptionClass = ontology.createClass(distractorOptionClassName);
		}

		superClass.addSubClass(distractorOptionClass);

		return distractorOptionClass;
	}
}