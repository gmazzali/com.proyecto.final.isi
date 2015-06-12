package com.proyecto.service.material.instrument;

import com.proyecto.model.material.instrument.EssayActivityInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayo que vamos a manipular dentro del servicio.
 */
public interface EssayActivityInstrumentService<E extends EssayActivityInstrument> extends FormalInstrumentService<E> {
}