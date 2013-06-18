package com.proyecto.service.instrument;

import com.proyecto.model.instrument.SimpleInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simple que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal simple que vamos a manipular dentro del servicio.
 */
public interface SimpleInstrumentService<E extends SimpleInstrument> extends SemiFormalInstrumentService<E> {
}