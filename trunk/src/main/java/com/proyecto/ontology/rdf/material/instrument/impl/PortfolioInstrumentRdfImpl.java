package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.PortfolioInstrument;
import com.proyecto.ontology.rdf.material.instrument.PortfolioInstrumentRdf;
import com.proyecto.util.Constants;

/**
 * La interfaz que define el comportamiento de los instrumentos semiformales compuesto de portfolio dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class PortfolioInstrumentRdfImpl extends CompositeInstrumentRdfImpl<PortfolioInstrument> implements PortfolioInstrumentRdf {

	/**
	 * La clase de un instrumento semiformal compuesto de portfolio.
	 */
	private OntClass portfolioInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.portfolioInstrumentClass == null) {

			// Creamos u obtenemos la clase superior.
			OntClass superClass = super.initClass(ontology);

			// Creamos u obtenemos la clase hija.
			String portfolioInstrumentClassName = Constants.NAMESPACE + PortfolioInstrument.class.getSimpleName();
			this.portfolioInstrumentClass = ontology.getOntClass(portfolioInstrumentClassName);

			if (this.portfolioInstrumentClass == null) {
				this.portfolioInstrumentClass = ontology.createClass(portfolioInstrumentClassName);
			}

			superClass.addSubClass(this.portfolioInstrumentClass);
		}

		return this.portfolioInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, PortfolioInstrument entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		return individual;
	}
}
