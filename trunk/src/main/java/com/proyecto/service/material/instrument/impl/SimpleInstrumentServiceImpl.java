package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.SimpleInstrument;
import com.proyecto.service.material.instrument.SimpleInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simples que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal simple que vamos a manipular dentro del servicio.
 */
public abstract class SimpleInstrumentServiceImpl<E extends SimpleInstrument> extends SemiFormalInstrumentServiceImpl<E> implements
		SimpleInstrumentService<E> {
}