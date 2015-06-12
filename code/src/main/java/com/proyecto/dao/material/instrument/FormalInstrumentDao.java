package com.proyecto.dao.material.instrument;

import com.proyecto.model.material.instrument.FormalInstrument;

/**
 * La interfaz que define el DAO de los instrumentos formales que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal que vamos a manipular dentro del DAO.
 */
public interface FormalInstrumentDao<E extends FormalInstrument> extends InstrumentDao<E> {
}