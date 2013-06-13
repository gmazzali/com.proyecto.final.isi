package com.proyecto.service.instrument;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del servicio.
 */
public interface InstrumentService<E extends Instrument> extends ProyectoService<E, Integer> {
}