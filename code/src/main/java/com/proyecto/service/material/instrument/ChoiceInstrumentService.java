package com.proyecto.service.material.instrument;

import com.proyecto.model.material.instrument.ChoiceInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selección que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo de selección que vamos a manipular dentro del servicio.
 */
public interface ChoiceInstrumentService<E extends ChoiceInstrument> extends ObjectiveActivityInstrumentService<E> {
}