package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.service.material.instrument.ChoiceInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selección que tenemos en
 * el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo de selección que vamos a manipular dentro del servicio.
 */
public abstract class ChoiceInstrumentServiceImpl<E extends ChoiceInstrument> extends ObjectiveActivityInstrumentServiceImpl<E> implements
		ChoiceInstrumentService<E> {
}