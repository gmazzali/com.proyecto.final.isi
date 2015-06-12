package com.proyecto.dao.material.instrument;

import com.proyecto.model.material.instrument.SemiFormalInstrument;

/**
 * La interfaz que define el DAO de los instrumentos semiformales que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal que vamos a manipular dentro del DAO.
 */
public interface SemiFormalInstrumentDao<E extends SemiFormalInstrument> extends InstrumentDao<E> {
}