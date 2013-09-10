package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.CompositeInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos semiformales compuesto dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales compuesto que vamos a manejar dentro de la ontolog�a.
 */
public interface CompositeInstrumentRdf<I extends CompositeInstrument> extends SemiFormalInstrumentRdf<I> {
}