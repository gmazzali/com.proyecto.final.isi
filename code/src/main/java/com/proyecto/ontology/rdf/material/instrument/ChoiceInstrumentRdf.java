package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.ChoiceInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos formales objetivos de selección dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos de selección que vamos a manejar dentro de la ontología.
 */
public interface ChoiceInstrumentRdf<I extends ChoiceInstrument> extends ObjectiveActivityInstrumentRdf<I> {
}