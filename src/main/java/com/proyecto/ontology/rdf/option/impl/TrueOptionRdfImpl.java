package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.TrueOption;
import com.proyecto.ontology.rdf.option.TrueOptionRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones verdaderas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class TrueOptionRdfImpl extends OptionRdfImpl<TrueOption> implements TrueOptionRdf {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String trueOptionClassName = ConstantsOntology.NAMESPACE + TrueOption.class.getSimpleName();
		OntClass trueOptionClass = ontology.getOntClass(trueOptionClassName);

		if (trueOptionClass == null) {
			trueOptionClass = ontology.createClass(trueOptionClassName);
		}

		superClass.addSubClass(trueOptionClass);

		return trueOptionClass;
	}
}