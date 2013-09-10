package com.proyecto.ontology.material.instrument;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.ontology.material.MaterialOntology;

/**
 * La interfaz que define el comportamiento definido para cargar una ontolog�a con un instrumento.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumento que vamos a manipular dentro de la ontolog�a.
 */
public interface InstrumentOntology<I extends Instrument> extends MaterialOntology<I> {

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
	public Individual loadInstrumentToOntology(OntModel ontology, Individual reactive, I instrument);
}
