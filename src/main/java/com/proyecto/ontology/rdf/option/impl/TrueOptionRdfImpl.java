package com.proyecto.ontology.rdf.option.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.TrueOption;
import com.proyecto.ontology.rdf.option.TrueOptionRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de las opciones verdaderas dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class TrueOptionRdfImpl extends OptionRdfImpl<TrueOption> implements TrueOptionRdf {

	private static final long serialVersionUID = -1333807761651940671L;
	
	/**
	 * La clase de una opci�n verdadera.
	 */
	private OntClass trueOptionClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase solo si es nula.
		if (this.trueOptionClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String trueOptionClassName = Constants.Ontology.NAMESPACE + TrueOption.class.getSimpleName();
			this.trueOptionClass = ontology.getOntClass(trueOptionClassName);

			if (this.trueOptionClass == null) {
				this.trueOptionClass = ontology.createClass(trueOptionClassName);
			}

			superClass.addSubClass(this.trueOptionClass);
		}
		return this.trueOptionClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, TrueOption entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}