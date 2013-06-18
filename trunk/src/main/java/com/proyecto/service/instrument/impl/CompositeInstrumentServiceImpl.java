package com.proyecto.service.instrument.impl;

import com.proyecto.model.instrument.CompositeInstrument;
import com.proyecto.service.instrument.CompositeInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales compuestos que tenemos en el
 * sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal compuesto que vamos a manipular dentro del servicio.
 */
public abstract class CompositeInstrumentServiceImpl<E extends CompositeInstrument> extends SemiFormalInstrumentServiceImpl<E> implements
		CompositeInstrumentService<E> {
}