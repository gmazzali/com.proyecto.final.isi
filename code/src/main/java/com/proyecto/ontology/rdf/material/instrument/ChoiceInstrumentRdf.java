package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.ChoiceInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos formales objetivos de selecci�n dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos de selecci�n que vamos a manejar dentro de la ontolog�a.
 */
public interface ChoiceInstrumentRdf<I extends ChoiceInstrument> extends ObjectiveActivityInstrumentRdf<I> {
}