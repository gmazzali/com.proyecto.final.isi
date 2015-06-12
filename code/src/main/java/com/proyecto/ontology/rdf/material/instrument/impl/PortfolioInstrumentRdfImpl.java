package com.proyecto.ontology.rdf.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.PortfolioInstrument;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.rdf.material.instrument.PortfolioInstrumentRdf;

/**
 * La interfaz que define el comportamiento de los instrumentos semiformales compuesto de portfolio dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class PortfolioInstrumentRdfImpl extends CompositeInstrumentRdfImpl<PortfolioInstrument> implements PortfolioInstrumentRdf {

	private static final long serialVersionUID = -1461483353780944367L;

	/**
	 * La clase de un instrumento semiformal compuesto de portfolio.
	 */
	private OntClass portfolioInstrumentClass;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String portfolioInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_COMPOSITE_PORTFOLIO;
		this.portfolioInstrumentClass = ontology.getOntClass(portfolioInstrumentClassName);
		if (this.portfolioInstrumentClass == null) {
			this.portfolioInstrumentClass = ontology.createClass(portfolioInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.portfolioInstrumentClass);

		return this.portfolioInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, PortfolioInstrument entity) {
		return super.loadEntityData(ontology, individual, entity);
	}
}