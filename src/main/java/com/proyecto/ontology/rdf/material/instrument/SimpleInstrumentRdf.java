package com.proyecto.ontology.rdf.material.instrument;

import com.proyecto.model.material.instrument.SimpleInstrument;

/**
 * La interfaz que define el comportamiento de los instrumentos semiformales simples dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos semiformales simples que vamos a manejar dentro de la ontología.
 */
public interface SimpleInstrumentRdf<I extends SimpleInstrument> extends SemiFormalInstrumentRdf<I> {
}