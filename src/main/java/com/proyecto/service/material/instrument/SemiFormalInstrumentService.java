package com.proyecto.service.material.instrument;

import com.proyecto.model.material.instrument.SemiFormalInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal que vamos a manipular dentro del servicio.
 */
public interface SemiFormalInstrumentService<E extends SemiFormalInstrument> extends InstrumentService<E> {
}