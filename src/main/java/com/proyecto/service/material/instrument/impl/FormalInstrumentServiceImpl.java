package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.FormalInstrument;
import com.proyecto.service.material.instrument.FormalInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal que vamos a manipular dentro del servicio.
 */
public abstract class FormalInstrumentServiceImpl<E extends FormalInstrument> extends InstrumentServiceImpl<E> implements FormalInstrumentService<E> {
}