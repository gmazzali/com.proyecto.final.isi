package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.Individual;
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

	/**
	 * La clase de una opción verdadera.
	 */
	private OntClass trueOptionClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		if (this.trueOptionClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String trueOptionClassName = ConstantsOntology.NAMESPACE + TrueOption.class.getSimpleName();
			this.trueOptionClass = ontology.getOntClass(trueOptionClassName);

			if (this.trueOptionClass == null) {
				this.trueOptionClass = ontology.createClass(trueOptionClassName);
			}

			superClass.addSubClass(this.trueOptionClass);
		}
		return this.trueOptionClass;
	}
	
	@Override
	public Individual createIndividual(OntModel ontology, TrueOption entity) {
		return super.createIndividual(ontology, entity);
	}
}