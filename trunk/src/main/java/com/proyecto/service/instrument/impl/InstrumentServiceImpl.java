package com.proyecto.service.instrument.impl;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.instrument.InstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del servicio.
 */
public abstract class InstrumentServiceImpl<E extends Instrument> extends ProyectoServiceImpl<E, Integer> implements InstrumentService<E> {
}