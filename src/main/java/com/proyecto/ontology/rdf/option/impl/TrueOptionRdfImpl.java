package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.TrueOption;
import com.proyecto.ontology.rdf.option.TrueOptionRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones verdaderas dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class TrueOptionRdfImpl extends OptionRdfImpl<TrueOption> implements TrueOptionRdf {

	private static final long serialVersionUID = -1333807761651940671L;

	/**
	 * La clase de una opción verdadera.
	 */
	private OntClass trueOptionClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		String trueOptionClassName = this.namespace + TrueOption.class.getSimpleName();
		this.trueOptionClass = ontology.getOntClass(trueOptionClassName);
		if (this.trueOptionClass == null) {
			this.trueOptionClass = ontology.createClass(trueOptionClassName);

			// Cargamos el padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.trueOptionClass);
		}

		return this.trueOptionClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, TrueOption entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}