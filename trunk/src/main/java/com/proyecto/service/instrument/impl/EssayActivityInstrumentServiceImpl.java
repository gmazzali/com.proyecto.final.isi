package com.proyecto.service.instrument.impl;

import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.service.instrument.EssayActivityInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayos que vamos a manipular dentro del servicio.
 */
public abstract class EssayActivityInstrumentServiceImpl<E extends EssayActivityInstrument> extends FormalInstrumentServiceImpl<E> implements
		EssayActivityInstrumentService<E> {
}