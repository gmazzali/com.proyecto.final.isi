package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.ontology.rdf.material.MaterialRdf;

/**
 * La interfaz que define el comportamiento de los instrumentos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos que vamos a manejar dentro de la ontología.
 */
public interface InstrumentRdf<I extends Instrument> extends MaterialRdf<I> {
}