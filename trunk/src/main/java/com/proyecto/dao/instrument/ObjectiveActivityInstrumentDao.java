package com.proyecto.dao.instrument;

import com.proyecto.model.instrument.ObjectiveActivityInstrument;

/**
 * La interfaz que define el DAO de los instrumentos formales objetivos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo que vamos a manipular dentro del DAO.
 */
public interface ObjectiveActivityInstrumentDao<E extends ObjectiveActivityInstrument> extends FormalInstrumentDao<E> {
}