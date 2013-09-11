package com.proyecto.ontology.rdf.material.instrument.factory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.Instrument;

/**
 * La interfaz que define el comportamiento de la manipulaci�n de los instrumentos dentro de una ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface InstrumentFactoryRdf {

	/**
	 * La funci�n encargada de cargar el instrumento recibido dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar el material.
	 * @param instrument
	 *            El instrumento que vamos a cargar dentro de la ontolog�a.
	 * @return El instrumento que acabamos de cargar dentro de la ontolog�a con los datos propios del material recibido.
	 */
	public Individual loadInstrumentToOntology(OntModel ontology, Instrument instrument);
}