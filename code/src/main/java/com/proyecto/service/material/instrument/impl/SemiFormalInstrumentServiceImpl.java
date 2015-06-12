package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.SemiFormalInstrument;
import com.proyecto.service.material.instrument.SemiFormalInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal que vamos a manipular dentro del servicio.
 */
public abstract class SemiFormalInstrumentServiceImpl<E extends SemiFormalInstrument> extends InstrumentServiceImpl<E> implements
		SemiFormalInstrumentService<E> {
}