package com.proyecto.service.instrument;

import com.proyecto.model.instrument.ObjectiveActivityInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo que vamos a manipular dentro del servicio.
 */
public interface ObjectiveActivityInstrumentService<E extends ObjectiveActivityInstrument> extends FormalInstrumentService<E> {
}