package com.proyecto.service.material.instrument;

import com.proyecto.model.material.instrument.FormalInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal que vamos a manipular dentro del servicio.
 */
public interface FormalInstrumentService<E extends FormalInstrument> extends InstrumentService<E> {
}