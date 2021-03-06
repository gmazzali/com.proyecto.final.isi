package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.Distractor;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.option.DistractorRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones falsas dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class DistractorRdfImpl extends OptionRdfImpl<Distractor> implements DistractorRdf {

	private static final long serialVersionUID = 4718979750310471461L;

	/**
	 * La clase de una opci�n falsa.
	 */
	private OntClass distractorOptionClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String distractorOptionClassName = this.namespace + OntologyConstants.ClassName.OPTION_DISTRACTOR;
		this.distractorOptionClass = ontology.getOntClass(distractorOptionClassName);
		if (this.distractorOptionClass == null) {
			this.distractorOptionClass = ontology.createClass(distractorOptionClassName);
		}

		// Cargamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.distractorOptionClass);

		return this.distractorOptionClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, Distractor entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}