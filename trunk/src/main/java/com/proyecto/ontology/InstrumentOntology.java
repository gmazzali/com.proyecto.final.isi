package com.proyecto.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.Instrument;

/**
 * La interfaz que define el comportamiento definido para cargar una ontolog�a con un instrumento.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface InstrumentOntology {

	/**
	 * La funci�n encargada de cargar un instrumento dentro de una ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a almacenar el instrumento.
	 * @param reactive
	 *            El individuo que representa el reactivo al que pertenece este instrumento.
	 * @param instrument
	 *            El instrumento que vamos a almacenar.
	 * @return El individuo que acabamos de crear y cargar dentro de la ontolog�a.
	 */
	public <I extends Instrument> Individual loadInstrumentToOntology(OntModel ontology, Individual reactive, I instrument);
}
