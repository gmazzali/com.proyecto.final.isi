package com.proyecto.ontology.rdf.material.instrument.factory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.Instrument;

/**
 * La interfaz que define el comportamiento de la manipulación de los instrumentos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface InstrumentFactoryRdf {

	/**
	 * La función encargada de cargar el instrumento recibido dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a cargar el material.
	 * @param instrument
	 *            El instrumento que vamos a cargar dentro de la ontología.
	 * @return El instrumento que acabamos de cargar dentro de la ontología con los datos propios del material recibido.
	 */
	public Individual loadInstrumentToOntology(OntModel ontology, Instrument instrument);
}