package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.ObjectiveActivityInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos formales objetivos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos que vamos a manejar dentro de la ontología.
 */
public interface ObjectiveActivityInstrumentRdf<I extends ObjectiveActivityInstrument> extends FormalInstrumentRdf<I> {
}