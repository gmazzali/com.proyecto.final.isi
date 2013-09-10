package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.FormalInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos formales dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales que vamos a manejar dentro de la ontolog�a.
 */
public interface FormalInstrumentRdf<I extends FormalInstrument> extends InstrumentRdf<I> {
}