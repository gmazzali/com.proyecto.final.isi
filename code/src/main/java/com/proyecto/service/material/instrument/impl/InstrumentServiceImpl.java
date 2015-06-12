package com.proyecto.service.material.instrument.impl;

import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.service.material.MaterialServiceImpl;
import com.proyecto.service.material.instrument.InstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del servicio.
 */
public abstract class InstrumentServiceImpl<E extends Instrument> extends MaterialServiceImpl<E, Integer> implements InstrumentService<E> {
}