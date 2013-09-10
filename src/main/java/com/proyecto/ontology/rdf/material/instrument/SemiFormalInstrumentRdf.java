package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.SemiFormalInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos semiformales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales que vamos a manejar dentro de la ontología.
 */
public interface SemiFormalInstrumentRdf<I extends SemiFormalInstrument> extends InstrumentRdf<I> {
}