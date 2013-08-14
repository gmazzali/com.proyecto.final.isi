package com.proyecto.service.material.instrument;

import com.proyecto.model.material.instrument.CompositeInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales compuestos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal compuesto que vamos a manipular dentro del servicio.
 */
public interface CompositeInstrumentService<E extends CompositeInstrument> extends SemiFormalInstrumentService<E> {
}