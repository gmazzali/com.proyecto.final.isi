package com.proyecto.ontology.material.instrument;

import com.proyecto.model.material.instrument.FormalInstrument;

/**
 * La interfaz que define el comportamiento definido para cargar una ontología con un instrumento formal.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumento formal que vamos a manipular dentro de la ontología.
 */
public interface FormalInstrumentOntology<I extends FormalInstrument> extends InstrumentOntology<I> {
}