package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.ObjectiveActivityInstrument;
import com.proyecto.service.material.instrument.ObjectiveActivityInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo que vamos a manipular dentro del servicio.
 */
public abstract class ObjectiveActivityInstrumentServiceImpl<E extends ObjectiveActivityInstrument> extends FormalInstrumentServiceImpl<E> implements
		ObjectiveActivityInstrumentService<E> {
}