package com.proyecto.dao.material.instrument;

import com.proyecto.model.material.instrument.CompositeInstrument;

/**
 * La interfaz que define el DAO de los instrumentos semiformales compuestos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal compuestos que vamos a manipular dentro del DAO.
 */
public interface CompositeInstrumentDao<E extends CompositeInstrument> extends SemiFormalInstrumentDao<E> {
}